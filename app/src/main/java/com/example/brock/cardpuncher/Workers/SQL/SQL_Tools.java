package com.example.brock.cardpuncher.Workers.SQL;

/**
 * Created by b on 2016-12-01.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQL_Tools extends SQLiteOpenHelper{
    private SQLiteOpenHelper _openHelper;

    public static final String DATABASE_NAME = "Customers.db";
    public static final String TABLE_NAME = "Customer_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "STAMPS";

    public SQL_Tools (Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        _openHelper = new SimpleSQLiteOpenHelper(context);
    }

    public SQL_Tools(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, STAMPS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
    class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {
        SimpleSQLiteOpenHelper(Context context) {
            super(context, "main.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table todos (_id integer primary key autoincrement, title text, priority integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    /**
     * Return a cursor object with all rows in the table.
     * @return A cursor suitable for use in a SimpleCursorAdapter
     */
    public Cursor getAll() {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        return db.rawQuery("select * from todos order by priority, title", null);
    }

    /**
     * Return values for a single row with the specified id
     * @param id The unique id for the row o fetch
     * @return All column values are stored as properties in the ContentValues object
     */
    public ContentValues get(long id) {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        ContentValues row = new ContentValues();
        Cursor cur = db.rawQuery("select title, priority from todos where _id = ?", new String[] { String.valueOf(id) });
        if (cur.moveToNext()) {
            row.put("name", cur.getString(0));
            row.put("points", cur.getInt(1));
            row.put("lastVisit", cur.getString(2));
        }
        cur.close();
        db.close();
        return row;
    }

    /**
     * Add a new row to the database table
     * @param cu_name The name value for the new row
     * @param cu_points The points value for the new row
     * @param cu_lastVisit the timestamp of the last visit
     * @return The unique id of the newly added row
     */
    public long add(String cu_name, int cu_points, String cu_lastVisit) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        ContentValues row = new ContentValues();
        row.put("title", cu_name);
        row.put("priority", cu_points);
        row.put("lastVisit", cu_lastVisit);
        long id = db.insert("todos", null, row);
        db.close();
        return id;
    }

    /**
     * Delete the specified row from the database table. For simplicity reasons, nothing happens if
     * this operation fails.
     * @param id The unique id for the row to delete
     */
    public void delete(long id) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete("todos", "_id = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    /**
     * NEEDS to be updated with new entries from 'add'
     * Updates a row in the database table with new column values, without changing the unique id of the row.
     * For simplicity reasons, nothing happens if this operation fails.
     * @param id The unique id of the row to update
     * @param name The new name value
     * @param points The new points value
     */
    public void update(long id, String name, int points, String lastVisit){
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        ContentValues row = new ContentValues();
        row.put("name", name);
        row.put("points", points);
        row.put("lastVisit", lastVisit);
        db.update("todos", row, "_id = ?", new String[] { String.valueOf(id) } );
        db.close();
    }

}
