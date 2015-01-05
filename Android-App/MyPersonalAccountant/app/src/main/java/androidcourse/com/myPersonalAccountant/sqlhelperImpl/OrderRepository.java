package androidcourse.com.myPersonalAccountant.sqlhelperImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidcourse.com.myPersonalAccountant.entity.Category;
import androidcourse.com.myPersonalAccountant.entity.Expense;
import androidcourse.com.myPersonalAccountant.entity.User;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelper.SqlRepository;
import androidcourse.com.myPersonalAccountant.util.CalendarUtil;
import androidcourse.com.myPersonalAccountant.util.SqlUtil;

/**
 * Created by Emrah.
 */
public class OrderRepository extends RepositoryImpl<UserOrder> implements SqlRepository<UserOrder> {
    private static String TABLE_NAME = UserOrder.class.getSimpleName();
    private static Integer DATABASE_VERSION = 2;
    private static String DATABASE_COLUMN_ID = "id";
    private static String DATABASE_COLUMN_NAME = "name";
    private static String DATABASE_COLUMN_DESCRIPTION = "description";
    private static String DATABASE_COLUMN_CREATE_DATE = "createdDate";
    private static String DATABASE_COLUMN_ON_SERVER = "onServer";
    private static String DATABASE_COLUMN_ON_IS_PAID = "isPaid";
    private static String DATABASE_COLUMN_ON_VALUE = "value";
    private static String DATABASE_COLUMN_IS_DELETED = "isDeleted";
    private static String DATABASE_COLUMN_EXPENSE_ID = "expenseID";
    private static String DATABASE_COLUMN_CATEGORY_ID = "categoryID";
    private static String DATABASE_COLUMN_USER_ID = "userID";
    private Context ctx;

    public OrderRepository(Context ctx) {
        super(ctx, TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " "
                        + " ( " + DATABASE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + " " + DATABASE_COLUMN_NAME + " TEXT, "
                        + " " + DATABASE_COLUMN_CREATE_DATE + " TEXT, "
                        + " " + DATABASE_COLUMN_DESCRIPTION + " TEXT, "
                        + " " + DATABASE_COLUMN_ON_SERVER + " INTEGER, "
                        + " " + DATABASE_COLUMN_ON_IS_PAID + " INTEGER, "
                        + " " + DATABASE_COLUMN_ON_VALUE + " REAL, "
                        + " " + DATABASE_COLUMN_EXPENSE_ID + " INTEGER, "
                        + " " + DATABASE_COLUMN_CATEGORY_ID + " INTEGER, "
                        + " " + DATABASE_COLUMN_USER_ID + " INTEGER, "
                        + " " + DATABASE_COLUMN_IS_DELETED + " INTEGER, "
                        + " " + "FOREIGN KEY(" + DATABASE_COLUMN_EXPENSE_ID + ") REFERENCES " + Expense.class.getSimpleName() + " ( " + DATABASE_COLUMN_ID + " ), "
                        + " " + "FOREIGN KEY(" + DATABASE_COLUMN_CATEGORY_ID + ") REFERENCES " + Category.class.getSimpleName() + " ( " + DATABASE_COLUMN_ID + " ), "
                        + " " + "FOREIGN KEY(" + DATABASE_COLUMN_USER_ID + ") REFERENCES " + User.class.getSimpleName() + " ( " + DATABASE_COLUMN_ID + " ) ) "
        );
    }

    @Override
    protected ContentValues objToContentValues(UserOrder order) {
        ContentValues cv = new ContentValues();

        cv.put(DATABASE_COLUMN_ID, order.getId());
        cv.put(DATABASE_COLUMN_NAME, order.getName());
        cv.put(DATABASE_COLUMN_DESCRIPTION, order.getDescription());
        cv.put(DATABASE_COLUMN_CREATE_DATE, CalendarUtil.tryDateToString(order.getCreatedDate()));
        cv.put(DATABASE_COLUMN_ON_SERVER, SqlUtil.booleanToInt(order.getOnServer()));
        cv.put(DATABASE_COLUMN_ON_IS_PAID, SqlUtil.booleanToInt(order.getIsPaid()));
        cv.put(DATABASE_COLUMN_ON_VALUE, order.getValue());

        cv.put(DATABASE_COLUMN_EXPENSE_ID, order.getExpense() == null ? null : order.getExpense().getId());
        cv.put(DATABASE_COLUMN_CATEGORY_ID, order.getCategory() == null ? null : order.getCategory().getId());
        cv.put(DATABASE_COLUMN_USER_ID, order.getUser() == null ? null : order.getUser().getId());

        return cv;
    }

    @Override
    protected UserOrder cursorToObj(Cursor cursor) {
        UserOrder order = new UserOrder();

        order.setId(cursor.getInt(cursor.getColumnIndex(DATABASE_COLUMN_ID)));
        order.setName(cursor.getString(cursor.getColumnIndex(DATABASE_COLUMN_NAME)));
        order.setDescription(cursor.getString(cursor.getColumnIndex(DATABASE_COLUMN_DESCRIPTION)));

        String createdDate = cursor.getString(cursor.getColumnIndex(DATABASE_COLUMN_CREATE_DATE));
        order.setCreatedDate(CalendarUtil.tryToParseDate(createdDate));

        Integer onServer = cursor.getInt(cursor.getColumnIndex(DATABASE_COLUMN_ON_SERVER));
        order.setOnServer(SqlUtil.intToBoolean(onServer));

        Integer isPaid = cursor.getInt(cursor.getColumnIndex(DATABASE_COLUMN_ON_IS_PAID));
        order.setIsPaid(SqlUtil.intToBoolean(onServer));

        order.setValue(cursor.getDouble(cursor.getColumnIndex(DATABASE_COLUMN_ON_VALUE)));


//        DATABASE_COLUMN_SCHEDULE_ID = "scheduleID";
//        DATABASE_COLUMN_EXPENSE_ID = "expenseID";
//        DATABASE_COLUMN_CATEGORY_ID = "categoryID";
//        DATABASE_COLUMN_USER_ID = "userID";
        return order;
    }
}
