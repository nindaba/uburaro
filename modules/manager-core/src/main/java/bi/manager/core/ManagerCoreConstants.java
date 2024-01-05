package bi.manager.core;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static java.time.temporal.ChronoUnit.*;

public class ManagerCoreConstants {
    public static final Map<Integer, ChronoUnit> RENT_UNIT_SCALE = Map.of(1, DAYS, 30, MONTHS, 365, YEARS);
    public static final String INVENTORY_ORDER_PREFIX = "inventory.order.prefix";
    public static final String RENT_ORDER_PREFIX = "rent.order.prefix";

    public static void main(String[] args) {
        final LocalDate date = LocalDate.of(2023, Month.NOVEMBER, 1);
        final LocalDate to = LocalDate.of(2023, Month.DECEMBER, 31);

        LocalDate nextOrderDate = date
                .plus(1, RENT_UNIT_SCALE.getOrDefault(30, DAYS));

        while (nextOrderDate.isAfter(to)) {
            nextOrderDate = nextOrderDate.minusDays(1);
        }

        System.out.println(nextOrderDate);
    }

}
