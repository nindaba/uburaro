package bi.uburaro.facade.populator;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.RoomType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@SpringBootTest
@ImportResource("classpath:uburaro-core-spring.xml")
public class RoomPopulator {
    @Resource(name = "typeService")
    TypeService typeService;

    @Test
    void roomTypeToRoomData() {
    }
}
