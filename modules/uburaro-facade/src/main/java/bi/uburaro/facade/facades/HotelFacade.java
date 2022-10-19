package bi.uburaro.facade.facades;

import bi.uburaro.facade.data.HotelData;

import java.util.Collection;

public interface HotelFacade {

    /**
     * Gets a hotel by code
     *
     * @param code of the hotel
     * @param allFields if the inherited fields will be included
     * @return Hotel
     */
    HotelData getHotelByCode(String code,boolean allFields);

    /**
     * Gets the current hotel
     *
     * @param allFields if the inherited fields will be included
     * @return Hotel
     */
    HotelData getCurrentHotel(boolean allFields);

    /**
     * Gets the parent of the hotel, where the branch belongs to
     *
     * @param code of the hotel
     * @return Hotel
     */
    HotelData getSuperHotel(String code);

    /**
     * Gets the branches of the current hotel
     *
     * @return Collection of Hotels
     */
    Collection<HotelData> getHotelBranches(String code);

    /**
     * Gets current hotel specific branch
     *
     * @return Hotes
     */
    HotelData getCurrentHotelBranch(String code);

    /**
     * Gets All the hotels
     *
     * @return Collection of Hotels
     */
    Collection<HotelData> getAllHotels();

    /**
     * Creates and saves a new hotel
     *
     * @param hotelData with the minimum fields, code,name and alias
     */
    void createHotel(HotelData hotelData);

    /**
     * Deletes a hotel by code
     *
     * @param code of the hotel
     */
    void deleteHotel(String code);

    HotelData updateHotel(HotelData hotelData);
}
