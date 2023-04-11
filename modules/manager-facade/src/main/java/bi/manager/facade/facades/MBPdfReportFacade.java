package bi.manager.facade.facades;

import bi.manager.facade.data.jasper.MBReportData;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public interface MBPdfReportFacade {

    /**
     * Fills the provided data to the jasper report template and exports it to the outputstream
     *
     * @param data
     * @param outputStream
     */
    default void getPdfReport(MBReportData data, OutputStream outputStream) {
        try {
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_DATA", data);
            JasperReport jasperReport = JasperCompileManager.compileReport(getReportTemplate().getInputStream());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    Resource getReportTemplate();
}
