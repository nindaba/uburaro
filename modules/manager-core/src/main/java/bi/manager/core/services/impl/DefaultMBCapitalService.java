package bi.manager.core.services.impl;

import bi.manager.core.repositories.MBCapitalEntryRepository;
import bi.manager.core.services.MBCapitalService;
import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.core.types.enums.MBPaymentModeEnum;
import bi.uburaro.core.services.TypeService;
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
        return entryRepository.findAllByCapitalAndDateModifiedBetween(capital,from,to);
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
            invoice.setCapitalEntry(entry);
        } else {
            updateCapital(invoice);
        }

        typeService.save(invoice);
    }

    private void updateCapital(final MBInvoiceType invoice) {
        MBCapitalType capital = invoice.getClient().getFacility().getCapital();
        MBCapitalEntryType entry = invoice.getCapitalEntry();

        capital.setCurrentValue(capital.getCurrentValue() - entry.getAmount() + invoice.getAmount());
        entry.setAmount(invoice.getAmount());
    }
}
