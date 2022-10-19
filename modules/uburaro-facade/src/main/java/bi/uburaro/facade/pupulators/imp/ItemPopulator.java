package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.ItemData;
import org.springframework.util.ObjectUtils;

public class ItemPopulator implements Populator<ItemType, ItemData> {
    @Override
    public void populate(ItemType source, ItemData target) {
        target.setCode(source.getCode());

        Boolean active = source.getActive();
        if(!ObjectUtils.isEmpty(active)){
            target.setActive(active);
        }

        Boolean visible = source.getVisible();
        if(!ObjectUtils.isEmpty(active)) {
            target.setVisible(visible);
        }
    }
}
