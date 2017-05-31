package choudhary96.sagar.p10_inventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 27-07-2016.
 */
public class dbHelper extends SQLiteOpenHelper {
    private Context context;

    public dbHelper(Context context) {
        super(context, dbContract.DB_NAME, null, dbContract.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(dbContract.productTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(dbContract.productTable.DELETE_TABLE);
        onCreate(database);
    }

    public void deleteDatabase() {
        context.deleteDatabase(dbContract.DB_NAME);
    }
}
