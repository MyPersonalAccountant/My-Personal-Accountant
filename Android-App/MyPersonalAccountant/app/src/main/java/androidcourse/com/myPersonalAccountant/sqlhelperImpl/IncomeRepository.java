package androidcourse.com.myPersonalAccountant.sqlhelperImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidcourse.com.myPersonalAccountant.entity.Income;
import androidcourse.com.myPersonalAccountant.entity.User;

/**
 * Created by Emrah on 4.1.2015 г..
 */
public class IncomeRepository extends RepositoryImpl<Income> {

    private static final String TABLE_NAME = Income.class.getSimpleName();

    private static final String DATABASE_COLUMN_ID = "id";
    private static final String DATABASE_COLUMN_NAME = "name";
    private static final String DATABASE_COLUMN_VALUE = "value";
    private static final String DATABASE_COLUMN_DESCRIPTION = "description";
    private static final String DATABASE_COLUMN_USER_ID = "userId";

    public IncomeRepository(Context ctx) {
        super(ctx, TABLE_NAME);
    }

    @Override
    protected ContentValues objToContentValues(Income item) {
        return null;
    }

    @Override
    protected Income cursorToObj(Cursor cursor) {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " "
                        + " ( " + DATABASE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + " " + DATABASE_COLUMN_NAME + " TEXT, "
                        + " " + DATABASE_COLUMN_VALUE + " REAL, "
                        + " " + DATABASE_COLUMN_DESCRIPTION + " TEXT, "
                        + " " + DATABASE_COLUMN_USER_ID + " INTEGER, "
                        + " " + "FOREIGN KEY(" + DATABASE_COLUMN_USER_ID + ") REFERENCES " + User.class.getSimpleName() + " ( " + DATABASE_COLUMN_ID + " ) ) "
        );
    }
}
