package bi.uburaro.facade.converters;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.facade.data.ItemData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Converter<SOURCE,TARGET> extends org.springframework.core.convert.converter.Converter<SOURCE,TARGET> {
    /**
     * Converts all the sources by iterating through all the sources and converting them
     *
     * @param sources of Items
     * @return Collection of <TARGET>
     */
    default Collection<TARGET> convertAll(final Collection<SOURCE> sources) {
        final List<TARGET> targets = new ArrayList<>();
        sources.forEach(source -> targets.add(convert(source)));
        return targets;
    }

}
