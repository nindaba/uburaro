package bi.manager.web.controllers;

import bi.uburaro.facade.facades.InitialDataFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static bi.manager.web.ManagerWebConstants.Controller.DataImport.endpoint;

@RestController
public class DataImportController {
    private final InitialDataFacade initialDataFacade;
    public DataImportController(InitialDataFacade initialDataFacade) {
        this.initialDataFacade = initialDataFacade;
    }

    @RequestMapping(path = endpoint, method = RequestMethod.POST)
    public void importData(){
        initialDataFacade.importCurrent();
    }


}
