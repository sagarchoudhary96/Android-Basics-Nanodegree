package choudhary96.sagar.p10_inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by admin on 28-07-2016.
 * References from https://developer.android.com/training/basics/data-storage/databases.html
 * https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#insert(java.lang.String,%20java.lang.String,%20android.content.ContentValues)
 */
public class dbQuery {

    private dbHelper mDbHelper;
    private static dbQuery INSTANCE;
    private  SQLiteDatabase db;
    private dbQuery(Context context) {
         mDbHelper = new dbHelper(context);
    }

    public static dbQuery getInstance(Context context) {
        if (context == null) {
            return null;
        }

        if (INSTANCE == null) {
            INSTANCE = new dbQuery(context);
        }
        return INSTANCE;
    }

    public void insertIntoTable(String tableName, ContentValues values) {

            db = mDbHelper.getWritableDatabase();
            db.insert(tableName, null, values);

    }

    public Cursor readFromTable(String tableName, String[] projections) {

            db = mDbHelper.getReadableDatabase();
            return db.query(tableName, projections, null, null, null, null, null);

    }

    public Cursor readFromTable(String tableName, String[] projection, String selection,
                             String[] selectionArgs) {
        if (tableName != null) {
            SQLiteDatabase db = mDbHelper.getReadableDatabase();
            return db.query(tableName, projection, selection, selectionArgs, null, null, null);
        }
        return null;
    }

    public void deleteEntry (String tableName,String selection,String[] selectionArgs) {

        db = mDbHelper.getReadableDatabase();
        db.delete(tableName, selection, selectionArgs);

    }

    public void updateData (String tableName, ContentValues values, String selection, String[] selectionArgs) {

        db = mDbHelper.getReadableDatabase();
        db.update(tableName, values, selection, selectionArgs);

    }

}
