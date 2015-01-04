package androidcourse.com.myPersonalAccountant.sqlhelperImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidcourse.com.myPersonalAccountant.entity.Schedule;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelper.SqlRepository;
import androidcourse.com.myPersonalAccountant.util.CalendarUtil;
import androidcourse.com.myPersonalAccountant.util.ConstantsUtil;
import androidcourse.com.myPersonalAccountant.util.SqlUtil;

/**
 * Created by Emrah.
 */
public class ScheduleRepository extends RepositoryImpl<Schedule> implements SqlRepository<Schedule> {

    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = Schedule.class.getSimpleName();

    private static String DATABASE_COLUMN_ID = "id";
    private static String DATABASE_COLUMN_NAME = "name";
    private static String DATABASE_COLUMN_FIRST_PAYMENT_DATE = "firstPaymentDate";
    private static String DATABASE_COLUMN_IS_REPEATABLE = "isRepeatable";
    private static String DATABASE_COLUMN_NOTIFICATION_BEFORE_PAYMENT_IN_MINUTES = "notificationBeforePaymentInMinutes";
    private static String DATABASE_COLUMN_FIRST_DATE = "firstDate";
    private static String DATABASE_COLUMN_END_DATE = "endDate";
    private static String DATABASE_COLUMN_USER_ORDER_ID = "userOrderID";

    private Context ctx;

    public ScheduleRepository(Context ctx) {
        super(ctx, ConstantsUtil.DATABASE_NAME, DATABASE_VERSION, TABLE_NAME);
        this.ctx = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " "
                        + " ( " + DATABASE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + " " + DATABASE_COLUMN_NAME + " TEXT, "
                        + " " + DATABASE_COLUMN_FIRST_PAYMENT_DATE + " TEXT, "
                        + " " + DATABASE_COLUMN_IS_REPEATABLE + " INTEGER, "
                        + " " + DATABASE_COLUMN_NOTIFICATION_BEFORE_PAYMENT_IN_MINUTES + " INTEGER, "
                        + " " + DATABASE_COLUMN_FIRST_DATE + " TEXT, "
                        + " " + DATABASE_COLUMN_END_DATE + " TEXT, "
                        + " " + DATABASE_COLUMN_USER_ORDER_ID + " INTEGER, "
                        + " " + "FOREIGN KEY(" + DATABASE_COLUMN_USER_ORDER_ID + ") REFERENCES " + UserOrder.class.getSimpleName() + " ( " + DATABASE_COLUMN_ID + " ) ) "
        );
    }


    @Override
    protected ContentValues objToContentValues(Schedule item) {
        ContentValues cv = new ContentValues();

        cv.put(DATABASE_COLUMN_ID, item.getId());
        cv.put(DATABASE_COLUMN_NAME, item.getName());
        cv.put(DATABASE_COLUMN_FIRST_PAYMENT_DATE, CalendarUtil.tryDateToString(item.getFirstPaymentDate()));
        cv.put(DATABASE_COLUMN_IS_REPEATABLE, SqlUtil.booleanToInt(item.getIsRepeatable()));
        cv.put(DATABASE_COLUMN_NOTIFICATION_BEFORE_PAYMENT_IN_MINUTES, item.getNotificationBeforePaymentInMinutes());
        cv.put(DATABASE_COLUMN_FIRST_DATE, CalendarUtil.tryDateToString(item.getFirstDate()));
        cv.put(DATABASE_COLUMN_END_DATE, CalendarUtil.tryDateToString(item.getEndDate()));
        cv.put(DATABASE_COLUMN_USER_ORDER_ID, item.getUserOrder() == null ? null : item.getUserOrder().getId());

        return cv;
    }

    @Override
    protected Schedule cursorToObj(Cursor cursor) {

        return null;
    }
}
