package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.AddressType;
import bi.uburaro.core.types.HotelType;
import bi.uburaro.core.types.LanguageType;
import bi.uburaro.core.types.RoomType;
import bi.uburaro.core.types.groups.BranchGroupType;
import bi.uburaro.core.types.groups.TaxGroupType;
import bi.uburaro.facade.converters.Converter;
import bi.uburaro.facade.converters.Converters;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.*;
import bi.uburaro.facade.data.groups.TaxGroupData;

public class FullHotelPopulator implements Populator<HotelType,HotelData> {
    private final Converter<LanguageType, LanguageData> languageConverter;
    private final Converter<RoomType, RoomData> roomConverter;
    private final Converter<BranchGroupType, BranchGroupData> branchGroupConverter;
    private final Converter<AddressType, AddressData> addressConverter;
    private final Converter<TaxGroupType, TaxGroupData> taxGroupConverter;

    public FullHotelPopulator(Converter<LanguageType, LanguageData> languageConverter,
                              Converter<RoomType, RoomData> roomConverter,
                              Converter<BranchGroupType, BranchGroupData> branchGroupConverter,
                              Converter<AddressType, AddressData> addressConverter,
                              Converter<TaxGroupType, TaxGroupData> taxGroupConverter) {
        this.languageConverter = languageConverter;
        this.roomConverter = roomConverter;
        this.branchGroupConverter = branchGroupConverter;
        this.addressConverter = addressConverter;
        this.taxGroupConverter = taxGroupConverter;
    }

    @Override
    public void populate(final HotelType source, final HotelData target) {
        target.setRooms(Converters.convertAll(roomConverter, source.getRooms()));
        target.setBranchGroup(Converters.convert(branchGroupConverter, source.getBranchGroup()));
        target.setAddresses(Converters.convertAll(addressConverter, source.getAddresses()));
        target.setTaxGroups(Converters.convertAll(taxGroupConverter, source.getTaxGroups()));
        target.setDefaultTaxGroup(Converters.convert(taxGroupConverter, source.getDefaultTaxGroup()));
        target.setDefaultLanguage(Converters.convert(languageConverter, source.getDefaultLanguage()));
        target.setLanguages(Converters.convertAll(languageConverter, source.getLanguages()));

    }

}
