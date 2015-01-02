package androidcourse.com.myPersonalAccountant.sqlhelperImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelper.Entity;
import androidcourse.com.myPersonalAccountant.sqlhelper.SqlRepository;

/**
 * Created by Emrah.
 */
public abstract class RepositoryImpl<E extends Entity> extends SQLiteOpenHelper implements SqlRepository<E> {

    private static String TABLE_NAME;

    public RepositoryImpl(Context ctx, String databaseName, int version, String tableName){
        super(ctx, databaseName, null, version);
        this.TABLE_NAME = tableName;
    }

    protected abstract ContentValues objToContentValues(E item);
    protected abstract E cursorToObj(Cursor cursor);

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    @Override
    public List<E> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        List<E> orders = new ArrayList<E>();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            orders.add(cursorToObj(cursor));
            cursor.moveToNext();
        }

        return orders;
    }

    @Override
    public E getByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FORM " + TABLE_NAME + " WHERE id = " + id, null);


        if (cursor.getCount() <= 0) {
            return null;
        } else {
            cursor.moveToFirst();
            return cursorToObj(cursor);
        }
    }

    @Override
    public Long insert(E item) {

        ContentValues cv = objToContentValues(item);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public Integer update(E item) {
        ContentValues cv = objToContentValues(item);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(TABLE_NAME, cv, " id = " + item.getId(), null);
    }

    @Override
    public int delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = " + id, null);
    }

    @Override
    public int delete(E item) {
        return this.delete(item.getId());
    }
}
