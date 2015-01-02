package androidcourse.com.myPersonalAccountant.sqlhelperImpl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidcourse.com.myPersonalAccountant.entity.User;

/**
 * Created by Emrah on 30.12.2014 г..
 */
public class UserSQLHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = User.class.getSimpleName();
    private static String DB_COLUMN_NAME_ID = "id";
    private static String DB_COLUMN_NAME_NAME = "name";
    private static String DB_COLUMN_NAME_AGE = "age";
    private static String DB_COLUMN_NAME_PASSWORD = "password";
    private static String DB_COLUMN_NAME_EMAIL = "email";
    private static String DB_COLUMN_NAME_BIRTH_DATE = "birthDate";
    private static String DB_COLUMN_NAME_COUNTRY = "country";
    private static String DB_COLUMN_NAME_ADDRESS = "address";
    private static String DB_COLUMN_NAME_PHONE = "phone";


    public UserSQLHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_NAME +
                        " " + DB_COLUMN_NAME_ID + "  INTEGER PRIMARY KEY  AUTOINCREMENT, " +
                        " " + DB_COLUMN_NAME_NAME + " text, " +
                        " " + DB_COLUMN_NAME_AGE + " int, " +
                        " " + DB_COLUMN_NAME_PASSWORD + " text, " +
                        " " + DB_COLUMN_NAME_EMAIL + " text, " +
                        " " + DB_COLUMN_NAME_BIRTH_DATE + " text, " +
                        " " + DB_COLUMN_NAME_COUNTRY + " text, " +
                        " " + DB_COLUMN_NAME_ADDRESS + " text, " +
                        " " + DB_COLUMN_NAME_PHONE + " text )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
    }


}
