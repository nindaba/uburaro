package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static bi.uburaro.core.UburaroCoreConstants.DATABASE_KEYWORDS_PREFIX;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = DATABASE_KEYWORDS_PREFIX + GroupType.ITEM_TYPE)
@Inheritance(strategy = InheritanceType.JOINED)
public class GroupType extends ItemType {
    public static final String ITEM_TYPE = "group";
    public static final String GROUP_NAME = "groupName";

    private String groupName;
}
