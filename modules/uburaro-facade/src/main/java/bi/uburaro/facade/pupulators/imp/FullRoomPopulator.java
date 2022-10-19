package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.RoomType;
import bi.uburaro.core.types.groups.PriceGroupType;
import bi.uburaro.core.types.groups.RoomGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import bi.uburaro.facade.converters.Converter;
import bi.uburaro.facade.converters.Converters;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.RoomData;
import bi.uburaro.facade.data.groups.PriceGroupData;
import bi.uburaro.facade.data.groups.RoomGroupData;
import bi.uburaro.facade.data.groups.TaxGroupData;

public class FullRoomPopulator implements Populator<RoomType, RoomData> {
    private final Converter<TaxGroupType, TaxGroupData> taxGroupConverter;
    private final Converter<PriceGroupType, PriceGroupData> priceGroupConverter;
    private final Converter<RoomGroupType, RoomGroupData> roomGroupConverter;

    public FullRoomPopulator(Converter<TaxGroupType, TaxGroupData> taxGroupConverter, Converter<PriceGroupType, PriceGroupData> priceGroupConverter, Converter<RoomGroupType, RoomGroupData> roomGroupConverter) {
        this.taxGroupConverter = taxGroupConverter;
        this.priceGroupConverter = priceGroupConverter;
        this.roomGroupConverter = roomGroupConverter;
    }

    @Override
    public void populate(final RoomType source, final RoomData target) {
        target.setRoomNumber(source.getRoomNumber());
        target.setTaxGroups(Converters.convertAll(taxGroupConverter,source.getTaxGroups()));
        target.setPriceGroups(Converters.convertAll(priceGroupConverter,source.getPriceGroups()));
        target.setRoomGroups(Converters.convertAll(roomGroupConverter,source.getRoomGroups()));
    }
}
