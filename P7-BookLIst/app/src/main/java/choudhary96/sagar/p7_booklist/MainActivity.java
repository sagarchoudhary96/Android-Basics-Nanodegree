package choudhary96.sagar.p7_booklist;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    // declaration for referencing different views
    private EditText mQuery;
    private ListView mResultsListView;
    private TextView mError_txtView;

    // adapter for the listView to display book data
    private BookAdapter mBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuery = (EditText) findViewById(R.id.query);
        mResultsListView = (ListView) findViewById(R.id.results);
        mError_txtView = (TextView) findViewById(R.id.error_txtView);

        mBookAdapter = new BookAdapter(this);
        mResultsListView.setAdapter(mBookAdapter);
        mResultsListView.setEmptyView(mError_txtView);


        mQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                search(null);
                return true;
            }
        });
    }

    public void search(View view) {

        String query = mQuery.getText().toString();
        if (query.length() != 0) {
            mQuery.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

            /**
             * used to check whether device is connected to internet or not
             * read from stackOverflow --> http://stackoverflow.com/questions/6493517/detect-if-android-has-internet-connection
             */
            ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            URL url = QueryUtils.buildUrl(query);
            Log.v(LOG_TAG, url.toString());

            if (url == null) {
                displayError(getString(R.string.error_bad_url));
            } else if ( manager.getActiveNetworkInfo() == null || !manager.getActiveNetworkInfo().isConnected()) {
                displayError(getString(R.string.error_no_connection));

            } else {
                Toast.makeText(this, "Please Wait, Searching for Books...", Toast.LENGTH_SHORT).show();
                mBookAdapter.clear();
                new FetchBooksTask().execute(url);
            }
        }
        else {
            displayError(getString(R.string.error_no_query));
        }
    }

    // used to set the listView
    private void setListView(List<Book> bookList) {
        mBookAdapter.clear();
        mBookAdapter.addAll(bookList);

    }

    // used to display error to empty listView
    private void displayError(String message) {
        mError_txtView.setText(message);
        mBookAdapter.clear();
    }


    private class FetchBooksTask extends AsyncTask<URL, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(URL... params) {
            // make http request to fetch json for the url
            String json = QueryUtils.fetchJsonResponse(params[0]);
            return QueryUtils.bookList(json);
        }

        @Override
        protected void onPostExecute(List<Book> bookList) {
            Log.d(LOG_TAG, "Query complete");
            setListView(bookList);
        }
    }

}
