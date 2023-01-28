package bi.uburaro.core.services.impl;

import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.PrincipalType;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AbstractSessionService implements SessionService {
    @Override
    public HotelType getCurrentHotel() {
        log.warn("No current hotel in the session");
        return null;
    }

    @Override
    public PrincipalType getCurrentUser() {
        log.warn("No current user in the session");
        return null;
    }

    @Override
    public PrincipalType getAnonymousUser() {
        log.warn("No anonymous user in the session");
        return null;
    }

    @Override
    public <T> T getSessionAttribute(String key) {
        log.warn("No current {} to stored in the session",key);
        return null;
    }
}
