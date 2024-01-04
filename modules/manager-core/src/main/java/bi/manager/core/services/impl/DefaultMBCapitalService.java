package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBCapitalEntryRepository;
import bi.manager.core.services.MBCapitalService;
import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.core.types.enums.MBPaymentModeEnum;
import bi.manager.core.utils.MBPage;
import bi.manager.core.utils.MBPageable;
import bi.uburaro.core.services.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service(value = "mbCapitalService")
public class DefaultMBCapitalService implements MBCapitalService {
    protected final TypeService typeService;
    protected final MBCapitalEntryRepository entryRepository;

    public DefaultMBCapitalService(TypeService typeService, MBCapitalEntryRepository entryRepository) {
        this.typeService = typeService;
        this.entryRepository = entryRepository;
    }

    @Override
    public void addCapital(final String facilityCode, final long value, final MBEntryEnum type) {
        MBFacilityType facility = typeService.findItemByCode(facilityCode, MBFacilityType.class);
        addCapital(value, type, facility);
    }

    @Override
    public void addCapital(long value, MBEntryEnum type, MBFacilityType facility) {
        MBCapitalType capital = facility.getCapital();

        if (capital == null) {
            capital = typeService.create(MBCapitalType.class);
            facility.setCapital(capital);
            typeService.save(facility);
        }

        long currentValue = capital.getCurrentValue();
        currentValue += type == MBEntryEnum.EXPENSE ? -value : value;

        capital.setCurrentValue(currentValue);
        populateEntry(value, type, capital);
    }

    private void populateEntry(long value, MBEntryEnum type, MBCapitalType capital) {
        MBCapitalEntryType entry = typeService.create(MBCapitalEntryType.class);
        entry.setEntryType(type);
        entry.setAmount(value);
        entry.setCapital(capital);
        typeService.save(entry);
    }

    @Override
    public void addCapital(final MBCapitalEntryType entry, final String facilityCode) {
        final MBFacilityType facility = typeService.findItemByCode(facilityCode, MBFacilityType.class);

        MBCapitalType capital = facility.getCapital();
        if (capital == null) {
            capital = typeService.create(MBCapitalType.class);
            facility.setCapital(capital);
            typeService.save(facility);
        }

        long currentValue = capital.getCurrentValue();
        currentValue += entry.getEntryType() == MBEntryEnum.EXPENSE ? -entry.getAmount() : entry.getAmount();

        capital.setCurrentValue(currentValue);
        entry.setCapital(capital);
        typeService.save(entry);
    }

    @Override
    public MBCapitalType getCapitalByFacility(String facilityCode) {
        MBFacilityType facility = typeService.findItemByCode(facilityCode, MBFacilityType.class);
        return facility.getCapital();
    }

    @Override
    public Collection<MBCapitalEntryType> getCapitalEntries(String facilityCode, Date from, Date to) {
        MBCapitalType capital = getCapitalByFacility(facilityCode);
        return entryRepository.findAllByCapitalAndDateModifiedBetween(capital.getFacility().getCode(), from,to);
    }

    @Override
    public MBPage<MBCapitalEntryType> getCapitalEntries(final String facilityCode, final Date from, final Date to,final MBPageable pageable) {
        final MBCapitalType capital = getCapitalByFacility(facilityCode);
        final Page<MBCapitalEntryType> page = entryRepository.findAllByCapitalAndDateModifiedBetween(capital.getFacility().getCode(), from, to,pageable);
        return new MBPage<>(page);
    }

    @Override
    public void addCapital(final MBInvoiceType invoice) {
        MBCapitalType capital = invoice.getClient().getFacility().getCapital();
        long currentValue = capital.getCurrentValue();

        currentValue += invoice.getPaymentMode() == MBPaymentModeEnum.DEBT ? -invoice.getAmount() : invoice.getAmount();

        if (invoice.getCapitalEntry() == null) {
            capital.setCurrentValue(currentValue);
            MBCapitalEntryType entry = typeService.create(MBCapitalEntryType.class);
            entry.setEntryType(MBEntryEnum.INTERNAL);
            entry.setAmount(invoice.getAmount());
            entry.setCapital(capital);
            entry.setInvoice(invoice);
            entry.setDescription(invoice.getPaymentMode() +"-"+invoice.getClient().getName()+" : "+invoice.getDescription());
            invoice.setCapitalEntry(entry);
        } else {
            updateCapital(invoice);
        }

        typeService.save(invoice);
    }

    @Override
    public long getTotalAmount(String facilityCode, Date from, Date to, MBEntryEnum entryType) {
        return entryRepository.findAllByFacilityAndDateModifiedBetween(facilityCode, from,to, entryType).stream()
                .peek(this::negateAmountIfDebt)
                .map(MBCapitalEntryType::getAmount)
                .reduce(0l, Long::sum);
    }

    private void negateAmountIfDebt(final MBCapitalEntryType entryType) {
        if(StringUtils.startsWith(entryType.getDescription(),MBPaymentModeEnum.DEBT.name())){
            entryType.setAmount(-entryType.getAmount());
        }
    }

    private void updateCapital(final MBInvoiceType invoice) {
        MBCapitalType capital = invoice.getClient().getFacility().getCapital();
        MBCapitalEntryType entry = invoice.getCapitalEntry();

        capital.setCurrentValue(capital.getCurrentValue() - entry.getAmount() + invoice.getAmount());
        entry.setAmount(invoice.getAmount());
    }
}
