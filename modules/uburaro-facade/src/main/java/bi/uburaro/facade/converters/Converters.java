package bi.uburaro.facade.converters;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.facade.data.ItemData;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Collections;

public class Converters{
    public static <SOURCE extends ItemType, TARGET extends ItemData> Collection<TARGET> convertAll(Converter<SOURCE,TARGET> converter,Collection<SOURCE> sources){
        if (!ObjectUtils.isEmpty(converter)){
            return converter.convertAll(sources);
        }
        return Collections.emptyList();
    }
    public static <SOURCE extends ItemType, TARGET extends ItemData> TARGET convert(Converter<SOURCE,TARGET> converter,SOURCE source){
        if (!ObjectUtils.isEmpty(converter) && !ObjectUtils.isEmpty(source)){
            return converter.convert(source);
        }
        return null;
    }

}
