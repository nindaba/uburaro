package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.core.types.ReservationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static bi.uburaro.core.UburaroCoreConstants.TABLE_PREFIX;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"reservations"})
@NoArgsConstructor
@Entity
@Table(name = TABLE_PREFIX + GroupType.ITEM_TYPE)
@Inheritance(strategy = InheritanceType.JOINED)
public class GroupType extends ItemType {
    public static final String ITEM_TYPE = "group";
    public static final String GROUP_NAME = "groupName";

    private String groupName;

    @ManyToMany
    @JoinTable(name = "reservations2groups",
            joinColumns = {
                    @JoinColumn(name = "reservations_date_created"),
                    @JoinColumn(name = "reservations_item_type"),
                    @JoinColumn(name = "reservations_t_key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "groups_date_created"),
                    @JoinColumn(name = "groups_item_type"),
                    @JoinColumn(name = "groups_t_key")
            })
    Set<ReservationType> reservations = new HashSet<>();
}
