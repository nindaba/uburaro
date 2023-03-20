package bi.manager.core;

import java.time.temporal.ChronoUnit;
import java.util.Map;

import static java.time.temporal.ChronoUnit.*;

public class ManagerCoreConstants {
    public static final Map<Integer, ChronoUnit> RENT_UNIT_SCALE = Map.of(1, DAYS, 30, MONTHS, 365, YEARS);
    public static final String INVENTORY_ORDER_PREFIX = "inventory.order.prefix";
    public static final String RENT_ORDER_PREFIX = "rent.order.prefix";

}
