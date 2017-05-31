package choudhary96.sagar.p10_inventoryapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    private static productAdapter listAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        // textview to be  shown when there is no data in the database
        TextView empty_textview = (TextView) findViewById(R.id.empty);

        //set up the list views
        final ListView listView  = (ListView)findViewById(R.id.list);
        listView.setEmptyView(empty_textview);

        cursor = dbQuery.getInstance(context).readFromTable(dbContract.productTable.TABLE_NAME, null);


        if (cursor != null) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {

                    listAdapter = new productAdapter(MainActivity.this, cursor, 0);
                    listView.setAdapter(listAdapter);
                }
            });
        } else {
            empty_textview.setVisibility(View.VISIBLE);
        }

        Button addProductButton = (Button)findViewById(R.id.button);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, addProduct.class);
                startActivity(i);
            }
        });


    }

    public static void refreshCursor() {
        Cursor cursor = dbQuery.getInstance(context).readFromTable(dbContract.productTable.TABLE_NAME, null);
        listAdapter.swapCursor(cursor);
    }
}
