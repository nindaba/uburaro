package bi.uburaro.core.types.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity(name = RoomGroupType.ITEM_TYPE)
public class RoomGroupType extends GroupType {
    public static final String ITEM_TYPE = "roomGroup";
}
