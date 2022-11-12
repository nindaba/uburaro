package bi.uburaro.facade.facades.impl;

import bi.uburaro.core.services.HotelService;
import bi.uburaro.core.services.SessionService;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.groups.BranchGroupType;
import bi.uburaro.facade.converters.Converters;
import bi.uburaro.facade.data.HotelData;
import bi.uburaro.facade.facades.HotelFacade;
import bi.uburaro.facade.converters.Converter;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Optional;

public class DefaultHotelFacade implements HotelFacade {
    private final Converter<HotelType, HotelData> converter;
    private final Converter<HotelType, HotelData> fullConverter;
    private final HotelService hotelService;
    private final SessionService sessionService;
    private final TypeService typeService;

    public DefaultHotelFacade(Converter<HotelType, HotelData> converter,
                              Converter<HotelType, HotelData> fullConverter,
                              HotelService hotelService,
                              SessionService sessionService,
                              TypeService typeService) {
        this.converter = converter;
        this.fullConverter = fullConverter;
        this.hotelService = hotelService;
        this.sessionService = sessionService;
        this.typeService = typeService;
    }

    @Override
    public HotelData getHotelByCode(final String code, final boolean allFields) {
        return Converters.convert(
                allFields ? fullConverter : converter,
                Optional.ofNullable(sessionService.getCurrentHotel())
                        .filter(hotelType -> ObjectUtils.nullSafeEquals(hotelType.getCode(), code))
                        .orElse(hotelService.getHotelByCode(code)));
    }


    @Override
    public HotelData getCurrentHotel(final boolean allFields) {
        return Converters.convert(allFields ? fullConverter : converter,
                sessionService.getCurrentHotel());
    }

    @Override
    public HotelData getSuperHotel(final String code) {
        //todo
        return null;
    }

    @Override
    public Collection<HotelData> getHotelBranches(final String code) {
        return Converters.convertAll(converter, hotelService.getHotelBranches(code));
    }

    @Override
    public HotelData getCurrentHotelBranch(final String code) {
        BranchGroupType branchGroup = sessionService.getCurrentHotel().getBranchGroup();
        if (!ObjectUtils.isEmpty(branchGroup)) {
            return branchGroup.getMembers().stream()
                    .filter(hotelType -> ObjectUtils.nullSafeEquals(hotelType.getCode(), code))
                    .findFirst()
                    .map(hotel -> Converters.convert(converter, hotel))
                    .orElse(null);
        }
        return null;
    }

    @Override
    public Collection<HotelData> getAllHotels() {
        return converter.convertAll(typeService.findAll(HotelType.class));
    }

    @Override
    public void createHotel(final HotelData hotelData) {
        HotelType hotelType = typeService.create(HotelType.class);
        setHotelDetails(hotelData, hotelType);
        typeService.save(hotelType);
    }

    @Override
    public void deleteHotel(final String code) {
        typeService.delete(code, HotelType.class);
    }

    @Override
    public HotelData updateHotel(final HotelData hotelData) {
        HotelType hotel = hotelService.getHotelByCode(hotelData.getCode());
        setHotelDetails(hotelData, hotel);
        typeService.save(hotel);
        return Converters.convert(converter, hotel);
    }

    private void setHotelDetails(final HotelData source, final HotelType target) {
        target.setName(source.getName());
        target.setAlias(source.getAlias());
    }
}
