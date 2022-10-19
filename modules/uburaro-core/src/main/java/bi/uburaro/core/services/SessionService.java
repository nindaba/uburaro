package bi.uburaro.core.services;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.PrincipalType;

public interface SessionService {
//    Map<String,Object> session;
    HotelType getCurrentHotel();
    PrincipalType getCurrentUser();


    Object getSessionAttribute(String key);
}
/**

each user should have a session
 which means they will be user,session age, hotel, and anything that I want to store there
 */