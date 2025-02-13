package bi.uburaro.platform;

import bi.uburaro.initialdata.services.DataImporterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PlatformApplicationTests {
    @Resource(name = "dataImporterService")
    DataImporterService dataImporterService;

    @Test
    void contextLoads() {
    }

    @Test
    void anyTest() {
//        dataImporterService.importCurrent();
//        typeService.save(hotelType);
    }
}
