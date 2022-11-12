package bi.uburaro.core.services.impl;

import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.PrincipalType;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AbstractSessionService implements SessionService {
    @Override
    public HotelType getCurrentHotel() {
        log.debug("No current hotel");
        return null;
    }

    @Override
    public PrincipalType getCurrentUser() {
        log.debug("No current user");
        return null;
    }

    @Override
    public <T> T getSessionAttribute(String key) {
        log.debug("No current {} to stored in the session",key);
        return null;
    }
}
