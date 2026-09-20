package utils;

import java.time.LocalDate;

public class DateUtil {

    public static LocalDate getToday() {
        return LocalDate.now();
    }

    public static LocalDate getTomorrow() {
        return LocalDate.now().plusDays(1);
    }

    public static LocalDate getYesterday() {
        return LocalDate.now().minusDays(1);
    }

}
