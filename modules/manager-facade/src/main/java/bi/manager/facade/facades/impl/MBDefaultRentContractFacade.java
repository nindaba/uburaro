package bi.manager.facade.facades.impl;

import bi.manager.core.services.MBRentContractService;
import bi.manager.core.strategies.GenerateRentOrdersStrategy;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.utils.MBPage;
import bi.manager.facade.converters.client.RentContractMapper;
import bi.manager.facade.data.MBDateRangeData;
import bi.manager.facade.data.MBPageData;
import bi.manager.facade.data.MBPageableData;
import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.facades.MBRentContractFacade;
import bi.manager.facade.factories.PageFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import static bi.manager.facade.factories.MBDateUtil.getLocalDate;

@Service(value = "mBRentContractFacade")
public class MBDefaultRentContractFacade implements MBRentContractFacade {

    protected final MBRentContractService service;
    protected final RentContractMapper mapper;
    protected final GenerateRentOrdersStrategy ordersStrategy;

    public MBDefaultRentContractFacade(MBRentContractService service, RentContractMapper mapper, GenerateRentOrdersStrategy ordersStrategy) {
        this.service = service;
        this.mapper = mapper;
        this.ordersStrategy = ordersStrategy;
    }

    @Override
    public Collection<MBRentContractData> getContractsByFacilityCode(String code) {
        return mapper.contractsToData(service.getContractsByFacilityCode(code));
    }

    @Override
    public Collection<MBRentContractData> getContractsByRentCode(String code) {
        return mapper.contractsToData(service.getContractsByRentCode(code));
    }

    @Override
    public Collection<MBRentContractData> getContractsByClientCode(String code) {
        return mapper.contractsToData(service.getContractsByClientCode(code));
    }

    @Override
    public void updateContract(MBRentContractData contract) {
        service.updateContract(mapper.contractToType(contract));
    }

    @Override
    public void deleteContract(Set<String> codes) {
        service.deleteMBItem(codes);
    }

    @Override
    public Collection<MBRentContractData> generateOrders() {
        return mapper.contractsToData(ordersStrategy.generateOrders());
    }

    @Override
    public MBPageData<MBRentContractData> getFacilityContracts(final String facility,final MBDateRangeData range, final MBPageableData pageable) {
        LocalDate from = getLocalDate(range.getFrom());
        LocalDate to = getLocalDate(range.getTo());
        MBPage<MBRentContractType> page = service.getContracts(facility, from, to, PageFactory.createPage(pageable));

        MBPageData<MBRentContractData> pageData = new MBPageData<>();
        pageData.setContent(mapper.contractsToData(page.getContent()));
        pageData.setPages(page.getPages());
        return pageData;
    }
}
