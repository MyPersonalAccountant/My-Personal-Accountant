package androidcourse.com.myPersonalAccountant.sqlhelperImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidcourse.com.myPersonalAccountant.entity.Expense;
import androidcourse.com.myPersonalAccountant.entity.User;

/**
 * Created by Emrah on 4.1.2015 г..
 */
public class ExpenseRepository extends RepositoryImpl<Expense> {

    private static final String TABLE_NAME = Expense.class.getSimpleName();

    private static final String DATABASE_COLUMN_ID = "id";
    private static final String DATABASE_COLUMN_NAME = "name";
    private static final String DATABASE_COLUMN_DESCRIPTION = "description";
    private static final String DATABASE_COLUMN_USER_ID = "userId";

    private Integer id;
    private String name;
    private String description;
    private User user;


    public ExpenseRepository(Context ctx) {
        super(ctx, TABLE_NAME);
    }

    @Override
    protected ContentValues objToContentValues(Expense item) {
        return null;
    }

    @Override
    protected Expense cursorToObj(Cursor cursor) {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " "
                        + " ( " + DATABASE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + " " + DATABASE_COLUMN_NAME + " TEXT, "
                        + " " + DATABASE_COLUMN_DESCRIPTION + " TEXT, "
                        + " " + DATABASE_COLUMN_USER_ID + " INTEGER, "
                        + " " + "FOREIGN KEY(" + DATABASE_COLUMN_USER_ID + ") REFERENCES " + User.class.getSimpleName() + " ( " + DATABASE_COLUMN_ID + " ) ) "
        );
    }
}
