package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.RoomType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.RoomData;

public class BasicRoomPopulator implements Populator<RoomType, RoomData> {
    @Override
    public void populate(RoomType source, RoomData target) {
        target.setRoomNumber(source.getRoomNumber());
    }
}
