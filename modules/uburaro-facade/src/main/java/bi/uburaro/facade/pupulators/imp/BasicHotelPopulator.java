package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.HotelType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.HotelData;
import org.springframework.util.ObjectUtils;

public class BasicHotelPopulator implements Populator<HotelType,HotelData> {
    @Override
    public void populate(final HotelType source,final HotelData target) {
        String name = source.getName();
        if(!ObjectUtils.isEmpty(name)){
            target.setName(name);
        }
        String alias = source.getAlias();
        if (!ObjectUtils.isEmpty(alias)){
            target.setAlias(alias);
        }
    }
}
