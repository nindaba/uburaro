package bi.uburaro.initialdata.event;

import bi.uburaro.core.types.PrincipalType;
import org.springframework.context.ApplicationEvent;

public class PasswordChangedEvent extends ApplicationEvent {
    private final PrincipalType principal;

    public PasswordChangedEvent(Object source, PrincipalType principal) {
        super(source);
        this.principal = principal;
    }

    /**
     * Create a new ContextStartedEvent.
     *
     * @param source        the {@code ApplicationContext} that the event is raised for
     *                      (must not be {@code null})
     * @param principal
     */


    public PrincipalType getPrincipal() {
        return principal;
    }
}
