package androidcourse.com.myPersonalAccountant.sqlhelperImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidcourse.com.myPersonalAccountant.entity.FamilyRelationship;
import androidcourse.com.myPersonalAccountant.entity.User;

/**
 * Created by Emrah on 4.1.2015 г..
 */
public class FamilyRelationshipRepository extends RepositoryImpl<FamilyRelationship> {
    private static final String TABLE_NAME = FamilyRelationship.class.getSimpleName();

    private static final String DATABASE_COLUMN_ID = "id";
    private static final String DATABASE_COLUMN_RELATIONSHIP = "relationship";
    private static final String DATABASE_COLUMN_USER_ONE = "userOne";
    private static final String DATABASE_COLUMN_USER_TWO = "userTwo";


    private Integer id;
    private String relationship;
    private User user;


    public FamilyRelationshipRepository(Context ctx) {
        super(ctx, TABLE_NAME);
    }

    @Override
    protected ContentValues objToContentValues(FamilyRelationship item) {
        return null;
    }

    @Override
    protected FamilyRelationship cursorToObj(Cursor cursor) {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " "
                        + " ( " + DATABASE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + " " + DATABASE_COLUMN_RELATIONSHIP + " TEXT, "
                        + " " + DATABASE_COLUMN_USER_ONE + " INTEGER, "
                        + " " + DATABASE_COLUMN_USER_TWO + " INTEGER, "
                        + " " + "FOREIGN KEY(" + DATABASE_COLUMN_USER_ONE + ") REFERENCES " + User.class.getSimpleName() + " ( " + DATABASE_COLUMN_ID + " ), "
                        + " " + "FOREIGN KEY(" + DATABASE_COLUMN_USER_TWO + ") REFERENCES " + User.class.getSimpleName() + " ( " + DATABASE_COLUMN_ID + " ) ) "
        );
    }
}
