package androidcourse.com.myPersonalAccountant.sqlhelperImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidcourse.com.myPersonalAccountant.entity.User;
import androidcourse.com.myPersonalAccountant.sqlhelper.SqlUserRepository;
import androidcourse.com.myPersonalAccountant.util.CalendarUtil;
import androidcourse.com.myPersonalAccountant.util.ConstantsUtil;

/**
 * Created by Emrah.
 */
public class UserRepository extends RepositoryImpl<User> implements SqlUserRepository<User> {

    private static Context ctx;

    private static String TABLE_NAME = User.class.getSimpleName();
    private static Integer DATABASE_VERSION = 1;

    private static String DB_COLUMN_ID = "id";
    private static String DB_COLUMN_NAME = "name";
    private static String DB_COLUMN_PASSWORD = "password";
    private static String DB_COLUMN_EMAIL = "email";
    private static String DB_COLUMN_BIRTH_DATE = "birthDate";
    private static String DB_COLUMN_COUNTRY = "country";
    private static String DB_COLUMN_ADDRESS = "address";
    private static String DB_COLUMN_PHONE = "phone";

    public UserRepository(Context ctx){
        super(ctx, ConstantsUtil.DATABASE_NAME, DATABASE_VERSION, TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                        " ( " + DB_COLUMN_ID + "  INTEGER PRIMARY KEY  AUTOINCREMENT, " +
                        " " + DB_COLUMN_NAME + " text, " +
                        " " + DB_COLUMN_PASSWORD + " text, " +
                        " " + DB_COLUMN_EMAIL + " text, " +
                        " " + DB_COLUMN_BIRTH_DATE + " text, " +
                        " " + DB_COLUMN_COUNTRY + " text, " +
                        " " + DB_COLUMN_ADDRESS + " text, " +
                        " " + DB_COLUMN_PHONE + " text )"
        );
    }

    protected ContentValues objToContentValues(User item) {
        ContentValues cv = new ContentValues();
        cv.put(DB_COLUMN_ID, item.getId());
        cv.put(DB_COLUMN_NAME, item.getName());
        cv.put(DB_COLUMN_PASSWORD, item.getPassword());
        cv.put(DB_COLUMN_EMAIL, item.getEmail());
        cv.put(DB_COLUMN_BIRTH_DATE, CalendarUtil.tryDateToString(item.getBirthDate()));
        cv.put(DB_COLUMN_COUNTRY, item.getCountry());
        cv.put(DB_COLUMN_ADDRESS, item.getAddress());
        cv.put(DB_COLUMN_PHONE, item.getPhone());
        return cv;
    }

    @Override
    protected User cursorToObj(Cursor cursor) {
        User user = new User();

        user.setId(cursor.getInt(cursor.getColumnIndex(DB_COLUMN_ID)));
        user.setName(cursor.getString(cursor.getColumnIndex(DB_COLUMN_NAME)));
        user.setAddress(cursor.getString(cursor.getColumnIndex(DB_COLUMN_ADDRESS)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(DB_COLUMN_PASSWORD)));

        String birthDate = cursor.getString(cursor.getColumnIndex(DB_COLUMN_BIRTH_DATE));
        user.setBirthDate(CalendarUtil.tryToParseDate(birthDate));

        user.setCountry(cursor.getString(cursor.getColumnIndex(DB_COLUMN_COUNTRY)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(DB_COLUMN_EMAIL)));
        user.setPhone(cursor.getString(cursor.getColumnIndex(DB_COLUMN_PHONE)));

        return user;
    }

    @Override
    public void getFamilyRelationships(User item) {

    }

    @Override
    public void getIncomes(User item) {

    }

    @Override
    public void setFamilyRelationships(User item) {

    }

    @Override
    public void setIncomes(User item) {

    }
}
