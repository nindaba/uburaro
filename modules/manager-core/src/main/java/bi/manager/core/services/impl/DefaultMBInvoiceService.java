package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBInvoiceRepository;
import bi.manager.core.repositories.MBOrderRepository;
import bi.manager.core.services.MBCapitalService;
import bi.manager.core.services.MBInvoiceService;
import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.client.MBOrderType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.repositories.GeneratedKeyRepository;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.GeneratedKey;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "mBInvoiceService")
public class DefaultMBInvoiceService implements MBInvoiceService {

    public static final String INVOICE_NUMBER_PREFIX = "invoice.number.prefix";
    protected final TypeService typeService;
    protected final GeneratedKeyRepository generatedKeyRepository;
    protected final Environment environment;
    protected final MBOrderRepository orderRepository;
    protected final MBInvoiceRepository invoiceRepository;
    protected final MBCapitalService capitalService;

    public DefaultMBInvoiceService(TypeService typeService, GeneratedKeyRepository generatedKeyRepository, Environment environment, MBOrderRepository orderRepository, MBInvoiceRepository invoiceRepository, MBCapitalService capitalService) {
        this.typeService = typeService;
        this.generatedKeyRepository = generatedKeyRepository;
        this.environment = environment;
        this.orderRepository = orderRepository;
        this.invoiceRepository = invoiceRepository;
        this.capitalService = capitalService;
    }

    @Override
    public Collection<MBInvoiceType> getAllInvoicesByFacilityCode(String facility) {
        return typeService.findItemByCode(facility, MBFacilityType.class).getClients().stream()
                .filter(ItemType::isActive)
                .flatMap(client -> client.getInvoices().stream())
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<MBInvoiceType> getAllInvoicesByClientCode(String client) {
        return typeService.findItemByCode(client, MBClientType.class).getInvoices();
    }

    @Override
    public void updateInvoice(MBInvoiceType invoice) {
        MBInvoiceType newInvoice;
        if (StringUtils.isEmpty(invoice.getInvoiceNumber())) {
            newInvoice = createInvoice(invoice);
        } else {
            newInvoice = typeService.findItemByCode(invoice.getCode(), MBInvoiceType.class);
        }
        newInvoice.setDescription(invoice.getDescription());
        populateAmount(invoice, newInvoice);
        removeDebt(newInvoice);
        this.capitalService.addCapital(newInvoice);
        typeService.save(newInvoice);

    }

    private void populateOrders(MBInvoiceType source, MBInvoiceType target) {
        Set<MBOrderType> paidOrders = source.getOrders().stream()
                .map(MBOrderType::getOrderNumber)
                .map(orderRepository::findByOrderNumber)
                .peek(order -> order.setPaid(true))
                .peek(order -> order.setInvoice(target))
                .collect(Collectors.toSet());

        target.setOrders(paidOrders);
    }

    private void removeDebt(MBInvoiceType invoice) {
        MBClientType client = invoice.getClient();

        long newDebt = client.getTotalDebt() + invoice.getAmount();
        if (invoice.getCapitalEntry() != null) {
            newDebt -= invoice.getCapitalEntry().getAmount();
        }
        client.setTotalDebt(newDebt);
    }

    private void populateAmount(MBInvoiceType source, MBInvoiceType target) {
        if (source.getAmount() < 0) {
            throw new NotFoundException("No amount was found on the invoice");
        }
        target.setAmount(source.getAmount());
        target.setPaymentMode(source.getPaymentMode());
    }

    private MBInvoiceType createInvoice(MBInvoiceType source) {
        MBInvoiceType invoice = typeService.create(MBInvoiceType.class);
        GeneratedKey key = generatedKeyRepository.save(new GeneratedKey());
        String invoiceNumber = environment.getProperty(INVOICE_NUMBER_PREFIX, "IN-") + key.getGeneratedValue();
        invoice.setCode(invoiceNumber);
        invoice.setInvoiceNumber(invoiceNumber);
        populateClient(source, invoice);
        populateOrders(source, invoice);
        return invoice;
    }

    private void populateClient(MBInvoiceType source, MBInvoiceType target) {
        MBClientType client = source.getClient();
        if (client != null || StringUtils.isNotEmpty(client.getCode())) {
            MBClientType targetClient = typeService.findItemByCode(client.getCode(), MBClientType.class);
            target.setClient(targetClient);
        } else {
            throw new NotFoundException("No client was found on the invoice");
        }

    }

    @Override
    public void deleteInvoice(Set<String> invoiceNumbers) {
        invoiceNumbers.stream()
                .map(invoiceRepository::findByInvoiceNumber)
                .peek(this::revertClient)
                .peek(this::revertCapital)
                .forEach(typeService::delete);
    }

    private void revertCapital(MBInvoiceType invoice) {
        MBCapitalEntryType capitalEntry = invoice.getCapitalEntry();
        MBCapitalType capital = capitalEntry.getCapital();
        long value = capital.getCurrentValue() - capitalEntry.getAmount();
        capital.setCurrentValue(value);
        capitalEntry.setCapital(null);
        typeService.save(capitalEntry);
        typeService.save(capital);
    }

    private void revertClient(MBInvoiceType invoice) {
        MBClientType client = invoice.getClient();
        MBCapitalEntryType entry = invoice.getCapitalEntry();
        long debt = client.getTotalDebt() - entry.getAmount();
        client.setTotalDebt(debt);
        invoice.setClient(null);
        typeService.save(client);
    }
}
