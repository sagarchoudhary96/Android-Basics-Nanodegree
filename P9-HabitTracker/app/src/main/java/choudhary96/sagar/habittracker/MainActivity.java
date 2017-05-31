package choudhary96.sagar.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * References from https://developer.android.com/training/basics/data-storage/databases.html
 * https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#insert(java.lang.String,%20java.lang.String,%20android.content.ContentValues)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate subclass of SQLiteOpenHelper
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        //query database
        // Gets the data repository in write mode using getWritableDatabase()

        insertIntoTable(mDbHelper.getWritableDatabase(), "playing Football", "2");
        insertIntoTable(mDbHelper.getWritableDatabase(), "music", "3");
        getData(mDbHelper.getReadableDatabase());
        updateData(mDbHelper.getReadableDatabase(),"2", "coding", "5");
        deleteData(mDbHelper.getReadableDatabase());

    }

    // insert data into the habit table
    public void insertIntoTable (SQLiteDatabase db, String habit, String time) {

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitContract.HABIT_TITLE, habit);
        values.put(HabitContract.HABIT_TIME, time);

        long rowId = db.insert(HabitContract.TABLE, null, values);
    }

    // get all the data from table
    public Cursor getData(SQLiteDatabase db){

        return  db.rawQuery("SELECT * FROM " + HabitContract.TABLE, null, null);
    }

    // delete all the data from table
    public int deleteData(SQLiteDatabase db) {

        return db.delete(HabitContract.TABLE, null, null);
    }

    //update the data of the table
    public int updateData(SQLiteDatabase db, String id, String habit, String time){

        ContentValues values = new ContentValues();

        values.put(HabitContract.HABIT_TITLE, habit);
        values.put(HabitContract.HABIT_TIME, time);

        return db.update(HabitContract.TABLE, values, "id=?",new String[]{id});
    }
}
