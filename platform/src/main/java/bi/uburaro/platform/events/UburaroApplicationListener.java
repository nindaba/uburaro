package bi.uburaro.platform.events;

import bi.uburaro.core.mergers.Merger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.time.LocalDate;
import java.util.Date;


public class UburaroApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        context.getBeansOfType(Merger.class)
                .values().forEach(Merger::merge);

        System.setProperty("logging.file.name", LocalDate.now().toString());
    }
}
