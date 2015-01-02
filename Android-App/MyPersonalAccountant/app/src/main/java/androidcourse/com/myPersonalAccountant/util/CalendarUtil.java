package androidcourse.com.myPersonalAccountant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Emrah on 30.12.2014 г..
 */
public class CalendarUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT);

    private CalendarUtil() {
    }

    public static Date tryToParseDate(String dateAsString) {
        Date date;

        try {
            date = dateFormat.parse(dateAsString);
        } catch (ParseException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }

        return date;
    }

    public static String tryDateToString(Date date) {
        if (date == null) {
            return null;
        } else {
            return dateFormat.format(date);
        }
    }
}
