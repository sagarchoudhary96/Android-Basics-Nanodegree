package choudhary96.sagar.p10_inventoryapp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        final EditText productName = (EditText) findViewById(R.id.nameInput);
        final EditText productQuantity = (EditText) findViewById(R.id.quantityInput);
        final EditText productPrice = (EditText) findViewById(R.id.priceInput);
        final EditText productImageUrl = (EditText) findViewById(R.id.imageUrlInput);

        Button addButton = (Button) findViewById(R.id.saveButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mProductName = productName.getText().toString();
                if (productName.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Name can\'t be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                double mProductPrice = 0.0;
                if (productPrice.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Enter the price", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    mProductPrice = Double.parseDouble(productPrice.getText().toString());
                }

                int mProductQuantity;
                if (productQuantity.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Quantity needed", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    mProductQuantity = Integer.parseInt(productQuantity.getText().toString());
                }

                String mImageUrl = productImageUrl.getText().toString();
                if (productImageUrl.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Image Url can\'t be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                ContentValues values = new ContentValues();
                values.put(dbContract.productTable.COL_PRODUCT_NAME, mProductName);
                values.put(dbContract.productTable.COL_QUANTITY, mProductQuantity);
                values.put(dbContract.productTable.COL_PRICE, mProductPrice);
                values.put(dbContract.productTable.COL_IMAGE_URL, mImageUrl);
                dbQuery.getInstance(getBaseContext()).insertIntoTable(dbContract.productTable.TABLE_NAME, values);
                MainActivity.refreshCursor();
                finish();

            }
        });
    }
}
