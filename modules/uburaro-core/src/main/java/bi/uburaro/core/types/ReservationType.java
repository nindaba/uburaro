package bi.uburaro.core.types;

import bi.uburaro.core.types.groups.GroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static bi.uburaro.core.UburaroCoreConstants.DATABASE_KEYWORDS_PREFIX;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = ReservationType.ITEM_TYPE)
public class ReservationType extends ItemType {
    public static final String ITEM_TYPE = "reservation";

    public static final String RESERVATION_DATE = "bookingDate";
    public static final String CHECK_IN = "checkIn";
    public static final String CHECK_OUT = "checkOut";
    public static final String ROOM = "room";
    public static final String RESERVED_BY_EMPLOYEE = "reservedByEmployee";
    public static final String RESERVED_BY_CUSTOMER = "reservedByCustomer";
    public static final String GROUPS = "groups";
    public static final String COMPANY = "company";

    @DateTimeFormat(pattern = "${uburaro.format.dateTime}")
    private Date reservationDate;
    private String checkIn;
    private String checkOut;
    private String room;

    @ManyToOne
    private EmployeeType reservedByEmployee;
    @ManyToOne
    private CustomerType reservedByCustomer;
    @Column(name = DATABASE_KEYWORDS_PREFIX + GROUPS)
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
    private Set<GroupType> groups;
}
