package bi.manager.facade.factories;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MBDateUtil {
    public static LocalDate getLocalDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
