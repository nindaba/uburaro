package bi.uburaro.core.services;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.PrincipalType;

public interface SessionService {
//    Map<String,Object> session;
    HotelType getCurrentHotel();
    PrincipalType getCurrentUser();

    /**
     * Gets anonymous user
     *
     * @return user
     */
    PrincipalType getAnonymousUser();


    <T> T getSessionAttribute(String key);


}
