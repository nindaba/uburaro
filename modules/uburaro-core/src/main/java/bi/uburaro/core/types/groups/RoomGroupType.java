package bi.uburaro.core.types.groups;

import bi.uburaro.core.types.CompanyType;
import bi.uburaro.core.types.RoomType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static bi.uburaro.core.types.CompanyType.PREFERRED_ROOMS_GROUPS;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {
        "preferredCompanies",
        "rooms"
})
@NoArgsConstructor
@Entity(name = RoomGroupType.ITEM_TYPE)
public class RoomGroupType extends GroupType {
    public static final String ITEM_TYPE = "roomGroup";
    public static final String PREFERRED_COMPANIES = "preferredCompanies";
    public static final String ROOMS = "rooms";

    @ManyToMany
    @JoinTable(name = "preferred_companies2preferred_room_groups",
            joinColumns = {
                    @JoinColumn(name = "preferred_companies_date_created"),
                    @JoinColumn(name = "preferred_companies_t_key"),
                    @JoinColumn(name = "preferred_companies_item_type"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "preferred_room_groups_date_created"),
                    @JoinColumn(name = "preferred_room_groups_t_key"),
                    @JoinColumn(name = "preferred_room_groups_item_type"),
            })
    private Set<CompanyType> preferredCompanies = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "rooms2room_groups",
            joinColumns = {
                    @JoinColumn(name = "room_date_created"),
                    @JoinColumn(name = "room_item_type"),
                    @JoinColumn(name = "room_t_key")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "room_groups_date_created"),
                    @JoinColumn(name = "room_groups_item_type"),
                    @JoinColumn(name = "room_groups_t_key")
            })
    private Set<RoomType> rooms = new HashSet<>();
}
