package bi.uburaro.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EntityScan(basePackages = "bi.uburaro.core.types")
@ImportResource(value = "classpath:platform-spring.xml")
@PropertySources({
        @PropertySource("classpath:uburaro-web.properties"),
        @PropertySource("classpath:uburaro-initialdata.properties")
})
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}
