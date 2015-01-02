package androidcourse.com.myPersonalAccountant.util;

/**
 * Created by Emrah.
 */
public class SqlUtil {
    private SqlUtil() {
    }

    public static Boolean intToBoolean(Integer number) {
        if (number == null) {
            return null;
        } else if (number == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static Integer booleanToInt(Boolean bool) {
        if (bool == null) {
            return null;
        } else if (bool) {
            return 1;
        } else {
            return 0;
        }
    }
}
