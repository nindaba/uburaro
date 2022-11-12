package bi.uburaro.facade.pupulators;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.facade.data.ItemData;

public interface Populator<SOURCE extends ItemType, TARGET extends ItemData> {
    /**
     * Populates the target attributes from the source
     *
     * @param source
     * @param target
     */
    void populate(final SOURCE source,final TARGET target);
}
