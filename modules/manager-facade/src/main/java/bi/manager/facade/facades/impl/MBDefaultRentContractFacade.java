package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBRentContractService;
import bi.manager.facade.converters.client.RentContractMapper;
import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.facades.MBRentContractFacade;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service(value = "mBRentContractFacade")
public class MBDefaultRentContractFacade implements MBRentContractFacade {

    protected final MBRentContractService service;
    protected final RentContractMapper mapper;

    public MBDefaultRentContractFacade(MBRentContractService service, RentContractMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public Collection<MBRentContractData> getContractsByFacilityCode(String code) {
        return mapper.contractsToData(service.getContractsByFacilityCode(code));
    }

    @Override
    public Collection<MBRentContractData> MBRentContractDataByRentCode(String code) {
        return mapper.contractsToData(service.getContractsByRentCode(code));
    }

    @Override
    public Collection<MBRentContractData> MBRentContractDataByClientCode(String code) {
        return mapper.contractsToData(service.MBRentContractDataByClientCode(code));
    }

    @Override
    public void updateContract(MBRentContractData contract) {
        service.updateContract(mapper.contractToType(contract));
    }

    @Override
    public void deleteContract(Set<String> codes) {
        service.deleteMBItem(codes);
    }
}
