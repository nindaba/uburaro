package bi.manager.web.controllers;

import bi.manager.facade.data.MBClientData;
import bi.manager.facade.data.MBFacilityData;
import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.facades.MBClientFacade;
import bi.manager.facade.facades.MBRentContractFacade;
import bi.manager.web.ManagerWebConstants;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

import static bi.manager.web.ManagerWebConstants.Controller.Client.client;
import static bi.manager.web.ManagerWebConstants.Controller.Client.rentContract;

@RestController
@RequestMapping(value = ManagerWebConstants.Controller.Client.endpoint)
@CrossOrigin()
public class ClientController {
    @Resource(name = "mBClientFacade")
    protected MBClientFacade facade;
    @Resource(name = "mBRentContractFacade")
    protected MBRentContractFacade rentContractFacade;

    @GetMapping
    public Collection<MBClientData> getCategories(@PathVariable String code, @RequestParam(required = false) boolean allFields) {
        return facade.getClientsByFacilityCode(code, allFields);
    }

    @GetMapping(value = client)
    public MBClientData getClient(@PathVariable String code, @RequestParam(required = false) boolean allFields, @PathVariable String clientCode) {
        return facade.getClientByCode(clientCode, allFields);
    }

    @GetMapping(value = rentContract)
    public Collection<MBRentContractData> getRentContracts(@PathVariable String clientCode, @PathVariable String code){
        return rentContractFacade.getContractsByClientCode(clientCode);
    }

    @DeleteMapping
    public void deleteCategories(@RequestParam Set<String> codes) {
        facade.deleteClients(codes);
    }
    @PatchMapping
    public void updateClient(@RequestBody MBClientData client, @PathVariable String code){
        MBFacilityData facility = new MBFacilityData();
        facility.setCode(code);
        client.setFacility(facility);
        facade.updateClient(client);
    }
}
