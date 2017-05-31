package choudhary96.sagar.p10_inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class productDetail extends AppCompatActivity {

    // Views
    TextView productname;
    TextView productPrice;
    TextView productQuantity;
    ImageView productImage;

    // Buttons
    Button incrementButton;
    Button decrementButton;
    Button orderItem;
    Button deleteItem;

    // variables required for intent data
    int mProductId;
    String mProductName;
    int mProductQuantity;
    String mImageUrl;
    double mProductPrice;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        //set the id for each references
        productname = (TextView)findViewById(R.id.productName);
        productPrice = (TextView)findViewById(R.id.productPrice);
        productQuantity = (TextView)findViewById(R.id.productQuantity);
        productImage = (ImageView)findViewById(R.id.productImage);
        incrementButton = (Button)findViewById(R.id.increment_quantity_button);
        decrementButton = (Button)findViewById(R.id.decrement_quantity_button);
        orderItem = (Button)findViewById(R.id.btnOrderMore);
        deleteItem = (Button)findViewById(R.id.btnDelete);

        // get the intent content and set them to respective objects
        Intent i = getIntent();
        if (i != null) {
            Bundle bundle = i.getExtras();
            mProductId = bundle.getInt("id");
            mProductName = bundle.getString("productName");
            mProductQuantity = bundle.getInt("quantity");
            mProductPrice = bundle.getDouble("price");
            mImageUrl = bundle.getString("imageUrl");
        }

        productname.setText(mProductName);
        productQuantity.setText("Quantity: " + mProductQuantity);
        productPrice.setText("$ " + mProductPrice);
        Picasso.with(context)
                .load(mImageUrl)
                .noFade()
                .into(productImage);


        //Increment Quantity
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProductQuantity++;
                productQuantity.setText("Quantity: " + mProductQuantity);
                ContentValues values = new ContentValues();
                values.put(dbContract.productTable.COL_QUANTITY, mProductQuantity);
                String selection = dbContract.productTable._ID + " =  ? ";
                String[] selectionArgs ={String.valueOf(mProductId)};
                dbQuery.getInstance(context).updateData(dbContract.productTable.TABLE_NAME, values, selection, selectionArgs);
                MainActivity.refreshCursor();
            }
        });

        //Decrease the quantity
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProductQuantity--;
                if (mProductQuantity < 0){
                    mProductQuantity = 0;
                }
                productQuantity.setText("Quantity: " + mProductQuantity);
                ContentValues values = new ContentValues();
                values.put(dbContract.productTable.COL_QUANTITY, mProductQuantity);
                String selection = dbContract.productTable._ID + " = ? ";
                String[] selectionArgs ={String.valueOf(mProductId)};
                dbQuery.getInstance(context).updateData(dbContract.productTable.TABLE_NAME, values, selection, selectionArgs);
                MainActivity.refreshCursor();
            }
        });

        //to delete the product
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to delete this product?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String selection = dbContract.productTable._ID + " = ? ";
                                String[] selectionArgs ={String.valueOf(mProductId)};
                                dbQuery.getInstance(context).deleteEntry(dbContract.productTable.TABLE_NAME, selection, selectionArgs);
                                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                MainActivity.refreshCursor();
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        //to order more product
        orderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Reorder product";
                String message = "Product Name: " + mProductName +
                        "\nProduct Price: " + mProductPrice +
                        "\nQuantity To be ordered: 30";
                String[] emails = {"reorder@outlook.com"};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, emails);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

}
