package ie.hjkl.companieshouse.data.utils;

import org.joda.time.LocalDate;

public class DateUtils {

    public static LocalDate parse(String chFormat) {
        if (chFormat.trim().isEmpty())
            return null;

        int year = Integer.parseInt(chFormat.substring(0, 4));
        int month = Integer.parseInt(chFormat.substring(4, 6));
        int day = Integer.parseInt(chFormat.substring(6, 8));

        if (year == 0)
            return null;

        return new LocalDate(year, month, day);
    }

}
