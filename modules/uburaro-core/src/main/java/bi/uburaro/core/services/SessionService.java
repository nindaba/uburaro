package bi.uburaro.core.services;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.PrincipalType;

public interface SessionService {
//    Map<String,Object> session;
    HotelType getCurrentHotel();
    PrincipalType getCurrentUser();


    <T> T getSessionAttribute(String key);
}
