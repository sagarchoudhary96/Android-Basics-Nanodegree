package choudhary96.sagar.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by admin on 19-07-2016.
 */

/**
 *references from :-https://developer.android.com/guide/topics/data/data-storage.html#db
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private Context context;
    public HabitDbHelper(Context context) {

        super(context, HabitContract.DB_NAME, null, HabitContract.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String Create_Table = "CREATE TABLE " + HabitContract.TABLE + " ( " +
                HabitContract.Id +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitContract.HABIT_TITLE + " TEXT NOT NULL, " +
                HabitContract.HABIT_TIME + " INTEGER NOT NULL);";

        Log.v("Main Activity",Create_Table);
        database.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int OLD_VERSION, int NEW_VERSION) {
        database.execSQL("DROP TABLE IF EXISTS " + HabitContract.TABLE);
        onCreate(database);
    }


    public void deleteDatabase() {
        context.deleteDatabase(HabitContract.DB_NAME);
    }
}
