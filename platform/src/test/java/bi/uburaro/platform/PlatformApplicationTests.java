package bi.uburaro.platform;

import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.services.impl.AbstractSessionService;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.ReservationType;
import bi.uburaro.core.types.RoomType;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.initialdata.services.DataImporterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class PlatformApplicationTests {
    @Resource(name = "dataImporterService")
    DataImporterService dataImporterService;

    @Test
    void contextLoads() {
    }

    @Test
    void anyTest() {
        dataImporterService.importCurrent();
//        typeService.save(hotelType);
    }
}
