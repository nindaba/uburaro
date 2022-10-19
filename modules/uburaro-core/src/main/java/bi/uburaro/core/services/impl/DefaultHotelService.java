package bi.uburaro.core.services.impl;

import bi.uburaro.core.exceptions.NotFoundException;
import bi.uburaro.core.services.HotelService;
import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.utils.MessageUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;

@Log4j2
public class DefaultHotelService implements HotelService {
    private final TypeService typeService;
    private final SessionService sessionService;
    private static final String NOT_FOUND_RESPONSE = "Unable to find Hotel of code {}";
    private static final String EMPTY_CODE = "Empty code";

    public DefaultHotelService(TypeService typeService, SessionService sessionService) {
        this.typeService = typeService;
        this.sessionService = sessionService;
    }


    @Override
    public HotelType getHotelByCode(String code) {
        HotelType itemByCode = typeService.findItemByCode(code, HotelType.class);

        if (ObjectUtils.isEmpty(itemByCode)) {
            log.debug(NOT_FOUND_RESPONSE, EMPTY_CODE);
            throw new NotFoundException(
                    MessageUtils.format(NOT_FOUND_RESPONSE, EMPTY_CODE));
        }
        return itemByCode;
    }


    @Override
    public HotelType getSuperHotel(String code) {
        return null;
    }

    @Override
    public Collection<HotelType> getHotelBranches(String code) {
        HotelType hotel = sessionService.getCurrentHotel();

        if (!ObjectUtils.nullSafeEquals(hotel.getCode(), code)) {
            hotel = typeService.findItemByCode(code, HotelType.class);
        }
        return hotel.getBranchGroup().getMembers();
    }


    @Override
    public List<HotelType> getAllHotels() {
        return null;
    }
}
