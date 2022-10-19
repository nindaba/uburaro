package bi.uburaro.core.services;

import bi.uburaro.core.types.HotelType;

import java.util.Collection;
import java.util.List;

public interface HotelService {
    /**
     * Gets Hotels by code
     *
     * @param code of the hotel
     * @return Hotel
     */
    HotelType getHotelByCode(String code);

    /**
     * Gets the parent of a hotel, where the hotel belongs
     *
     * @param code of the sub hotel
     * @return Hotel
     */
    HotelType getSuperHotel(String code);

    /**
     * Gets hotel branches of a hotel
     *
     * @param code of the parent hotel
     * @return Collection of Hotels
     */
    Collection<HotelType> getHotelBranches(String code);

    /**
     * Gets all the hotels
     *
     * @return Collection of Hotels
     */
    Collection<HotelType> getAllHotels();

}
