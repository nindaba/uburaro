package bi.uburaro.facade.pupulators.imp;

import bi.uburaro.core.types.groups.GroupType;
import bi.uburaro.facade.pupulators.Populator;
import bi.uburaro.facade.data.groups.GroupData;

public class GroupPopulator implements Populator<GroupType, GroupData> {
    @Override
    public void populate(GroupType source, GroupData target) {
        target.setGroupName(source.getGroupName());
    }
}
