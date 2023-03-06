package bi.manager.core.services.impl;

import bi.manager.core.services.MBClientService;
import bi.manager.core.services.MBFacilityService;
import bi.manager.core.services.MBRentContractService;
import bi.manager.core.services.MBRentService;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.repositories.GeneratedKeyRepository;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.GeneratedKey;
import bi.uburaro.core.types.ItemType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Service(value = "mBRentContractService")
public class DefaultMBRentContractService extends AbstractMBTypeService<MBRentContractType> implements MBRentContractService {

    public static final String CONTRACT_CODE_PREFIX = "contract.code.prefix";

    protected final MBFacilityService facilityService;
    protected final MBClientService clientService;
    protected final MBRentService rentService;
    protected final Environment environment;
    protected final GeneratedKeyRepository generatedKeyRepository;

    protected DefaultMBRentContractService(TypeService typeService, MBFacilityService facilityService, MBClientService clientService, MBRentService rentService, Environment environment, GeneratedKeyRepository generatedKeyRepository) {
        super(typeService);
        this.facilityService = facilityService;
        this.clientService = clientService;
        this.rentService = rentService;
        this.environment = environment;
        this.generatedKeyRepository = generatedKeyRepository;
    }

    @Override
    public Collection<MBRentContractType> getContractsByFacilityCode(String code) {
        return facilityService.getFacilityByCode(code).getRents().stream()
                .filter(ItemType::isActive)
                .flatMap(rent -> rent.getContracts().stream())
                .filter(ItemType::isActive)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<MBRentContractType> getContractsByRentCode(String code) {
        return typeService.findItemByCode(code, MBRentPropertyType.class).getContracts().stream()
                .filter(ItemType::isActive)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<MBRentContractType> getContractsByClientCode(String code) {
        return clientService.getClientByCode(code).getContracts().stream()
                .filter(ItemType::isActive)
                .collect(Collectors.toSet());
    }

    @Override
    public void updateContract(final MBRentContractType contract) {
        if (contract.getClient() == null || StringUtils.isEmpty(contract.getClient().getCode())) {
            throw new NotFoundException("contract.client.notfound");
        }
        if (contract.getRentProperty() == null || StringUtils.isEmpty(contract.getRentProperty().getCode())) {
            throw new NotFoundException("No rent property was found on the contract");
        }

        MBRentContractType contractType;
        if (StringUtils.isEmpty(contract.getCode())) {
            contractType = createContract(contract);
        } else {
            contractType = typeService.findItemByCode(contract.getCode(), MBRentContractType.class);
            populateDates(contract, contractType);
        }
        populateContract(contract, contractType);
        typeService.save(contractType);
    }

    private void populateDates(MBRentContractType source, MBRentContractType target) {
        if (source.getTo() != null) {
            target.setTo(source.getTo());
        }
        if (source.getFrom() != null) {
            target.setFrom(source.getFrom());
        }

    }

    private static void populateCurrentContract(MBRentContractType target) {
        LocalDate today = LocalDate.now();
        MBRentPropertyType property = target.getRentProperty();
        if ((target.getFrom().isBefore(today) || target.getFrom().equals(today))
                && (target.getTo().isAfter(today) || target.getTo().equals(today))) {

            property.setCurrentContract(target);
        }
    }

    private void populateContract(MBRentContractType source, MBRentContractType target) {
        if (source.getUnit() > 0) {
            target.setUnit(source.getUnit());
        } else {
            target.setUnit(target.getRentProperty().getUnit());
        }
        if (source.getCostPerUnit() > 0) {
            target.setCostPerUnit(source.getCostPerUnit());
        } else {
            target.setCostPerUnit(target.getRentProperty().getCost());
        }
        if(StringUtils.isNotEmpty(source.getContractFileName())){
            target.setContractFileName(source.getContractFileName());
        }
    }

    private void populateRelations(final MBRentContractType source, final MBRentContractType target) {
        MBClientType clientByCode = clientService.getClientByCode(source.getClient().getCode());
        target.setClient(clientByCode);
        MBRentPropertyType rentPropertyType = typeService.findItemByCode(source.getRentProperty().getCode(), MBRentPropertyType.class);
        target.setRentProperty(rentPropertyType);
    }

    private MBRentContractType createContract(MBRentContractType source) {
        MBRentContractType target = typeService.create(MBRentContractType.class);
        GeneratedKey key = generatedKeyRepository.save(new GeneratedKey());
        target.setCode(environment.getProperty(CONTRACT_CODE_PREFIX, "CC-") + key.getGeneratedValue());
        target.setActive(true);

        if (source.getFrom() == null || source.getTo() == null) {
            throw new NotFoundException("No contract period was found on the contract, from and to dates should be provided");
        }
        populateDates(source, target);
        populateRelations(source, target);
        populateCurrentContract(target);
        return target;
    }

    @Override
    public void endContract(String code, LocalDate endDate) {
        MBRentContractType contract = typeService.findItemByCode(code, MBRentContractType.class);
        contract.setTo(endDate);
        MBRentPropertyType rentProperty = contract.getRentProperty();
        rentProperty.setCurrentContract(null);
        typeService.save(contract);
    }

    @Override
    public MBRentContractType getItemByCodeWithTry(String code) {
        try {
            return typeService.findItemByCode(code, MBRentContractType.class);
        } catch (NotFoundException e) {
            return null;
        }
    }
}
