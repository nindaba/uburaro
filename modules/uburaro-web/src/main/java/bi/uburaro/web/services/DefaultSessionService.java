package bi.uburaro.web.services;

import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.PrincipalType;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Log4j2
public class DefaultSessionService implements SessionService {
    private static final String SESSION_HOTEL = "session.current.hotel";
    private static final String SESSION_USER = "session.current.user";
    private final Environment environment;

    public DefaultSessionService(Environment environment) {
        this.environment = environment;
    }


    @Override
    public HotelType getCurrentHotel() {
        ItemType currentHotel = getSessionAttribute(SESSION_HOTEL);
        if(currentHotel instanceof HotelType){
            log.info("Found current hotel: {}",((HotelType) currentHotel)::getCode);
            return (HotelType) currentHotel;
        }
        log.debug("No Hotel is stored in the session");
        return null;
    }

    @Override
    public PrincipalType getCurrentUser() {
        ItemType currentUser = getSessionAttribute(SESSION_USER);
        if(currentUser instanceof PrincipalType){
            log.info("Found current hotel: {}",((PrincipalType) currentUser)::getCode);
            return (PrincipalType) currentUser;
        }
        log.debug("No Hotel is stored in the session");
        return null;
    }
    @Override
    public <T> T getSessionAttribute(String key) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        StandardSessionFacade sessionFacade = (StandardSessionFacade) requestAttributes.getSessionMutex();
        return (T) sessionFacade.getAttribute(environment.getProperty(key));
    }
}
