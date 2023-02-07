package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.ItemData;
import org.springframework.util.ObjectUtils;

public class ItemPopulator implements Populator<ItemType, ItemData> {
    @Override
    public void populate(ItemType source, ItemData target) {
        target.setCode(source.getCode());

        Boolean active = source.isActive();
        if(!ObjectUtils.isEmpty(active)){
            target.setActive(active);
        }

        Boolean visible = source.isVisible();
        if(!ObjectUtils.isEmpty(visible)) {
            target.setVisible(visible);
        }
    }
}
