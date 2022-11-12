package bi.uburaro.facade.facades.impl;

import bi.uburaro.facade.facades.InitialDataFacade;
import bi.uburaro.initialdata.services.DataImporterService;

public class DefaultInitialDataFacade implements InitialDataFacade {
    protected final DataImporterService dataImporterService;

    public DefaultInitialDataFacade(DataImporterService dataImporterService) {
        this.dataImporterService = dataImporterService;
    }

    @Override
    public void importCurrent() {
        dataImporterService.importCurrent();
    }
}
