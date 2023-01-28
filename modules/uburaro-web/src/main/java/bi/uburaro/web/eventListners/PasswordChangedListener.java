package bi.uburaro.web.eventListners;


import bi.uburaro.initialdata.event.PasswordChangedEvent;
import bi.uburaro.web.services.UserDetailsService;
import org.springframework.context.ApplicationListener;

public class PasswordChangedListener implements ApplicationListener<PasswordChangedEvent> {
    private final UserDetailsService userDetailsService;

    public PasswordChangedListener(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onApplicationEvent(PasswordChangedEvent event) {
        userDetailsService.updatePassword(event.getPrincipal());
    }

}
