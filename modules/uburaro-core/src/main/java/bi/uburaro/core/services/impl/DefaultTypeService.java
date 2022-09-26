package bi.uburaro.core.services.impl;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.ItemType;

public class DefaultTypeService implements TypeService {

    @Override
    public <T> T create() {

        return (T) new ItemType();
    }
}
