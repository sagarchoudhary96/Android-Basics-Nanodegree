package choudhary96.sagar.p10_inventoryapp;

import android.provider.BaseColumns;

/**
 * Created by admin on 27-07-2016.
 * references from http://www.developers.android.com
 * and http://www.stackoverflow.com
 */
public class dbContract {

    //Default Constructor
    public dbContract(){
    }

    //Database NAme and Version
    public static final String DB_NAME ="Inventory.db";
    public static final int DB_VERSION = 1;

    public static abstract class productTable implements BaseColumns{

        public static final String TABLE_NAME = "Inventory";
        public static final String COL_PRODUCT_NAME = "name";
        public static final String COL_QUANTITY = "quantity";
        public static final String COL_PRICE = "price";
        public static final String COL_IMAGE_URL = "imageUrl";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PRODUCT_NAME +
                " TEXT NOT NULL," + COL_QUANTITY + " INT NOT NULL," + COL_PRICE +
                " DOUBLE NOT NULL,"  + COL_IMAGE_URL + " TEXT NOT NULL);";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
