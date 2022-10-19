package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.groups.BranchGroupType;
import bi.uburaro.facade.converters.Converter;
import bi.uburaro.facade.converters.Converters;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.BranchGroupData;
import bi.uburaro.facade.data.HotelData;

public class RoomGroupPopulator implements Populator<BranchGroupType, BranchGroupData> {
    private final Converter<HotelType, HotelData> hotelConverter;

    public RoomGroupPopulator(Converter<HotelType, HotelData> hotelConverter) {
        this.hotelConverter = hotelConverter;
    }

    @Override
    public void populate(final BranchGroupType source,final BranchGroupData target) {
        target.setMembers(Converters.convertAll(hotelConverter,source.getMembers()));
    }
}
