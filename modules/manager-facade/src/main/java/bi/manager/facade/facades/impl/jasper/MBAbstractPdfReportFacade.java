package bi.manager.facade.facades.impl.jasper;

import bi.manager.facade.facades.MBPdfReportFacade;
import org.springframework.core.io.Resource;

public class MBAbstractPdfReportFacade implements MBPdfReportFacade {

    private final Resource report;

    public MBAbstractPdfReportFacade(Resource report) {
        this.report = report;
    }

    @Override
    public Resource getReportTemplate() {
        return report;
    }
}
