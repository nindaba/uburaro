package bi.uburaro.platform;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.ReservationType;
import bi.uburaro.core.types.RoomType;
import bi.uburaro.core.services.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class PlatformApplicationTests {
    @Resource(name = "typeService")
    protected TypeService typeService;

    @Test
    void contextLoads() {
    }

    @Test
    void anyTest() {
        HotelType hotelType = typeService.create(HotelType.class);
//        typeService.save(hotelType);
    }
}
