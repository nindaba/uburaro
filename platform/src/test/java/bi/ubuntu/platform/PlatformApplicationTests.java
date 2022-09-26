package bi.ubuntu.platform;

import bi.uburaro.core.types.RoomType;
import bi.uburaro.core.services.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class PlatformApplicationTests {
    @Resource(name = "typeService")
    protected TypeService typeService;

    @Test
    void contextLoads() {
    }

    @Test
    void springConfigs() {
        RoomType expected = new RoomType();
        assertEquals(expected,expected);
    }
}
