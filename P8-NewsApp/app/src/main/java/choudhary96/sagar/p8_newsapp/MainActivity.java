package choudhary96.sagar.p8_newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsStory>> {

    private static final int NEWS_LOADER_ID = 1;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private ListView mNewsListView;
    private URL url;
    private TextView error;
    private ProgressBar progressBar;

    private NewsAdapter mNewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsListView = (ListView) findViewById(R.id.news_stories);
        error = (TextView)findViewById(R.id.error);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        mNewsAdapter = new NewsAdapter(this);
        mNewsListView.setAdapter(mNewsAdapter);
        mNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsStory newsStory = mNewsAdapter.getItem(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, newsStory.mUri);
                startActivity(intent);
            }
        });

        url = QueryUtils.buildUrl();
        updateNewsData();
    }

    private void updateNewsData() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        if (url == null) {
            displayError(getString(R.string.error_bad_url));
        } else if (manager.getActiveNetworkInfo() == null || !manager.getActiveNetworkInfo().isConnected()) {
            displayError(getString(R.string.error_no_connection));
        } else {
            if(mNewsListView.getVisibility()== View.GONE) {
                mNewsAdapter.clear();
                error.setVisibility(View.GONE);
                mNewsListView.setVisibility(View.VISIBLE);
            }
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        }
    }


    private void displayError(String message) {
        progressBar.setVisibility(View.GONE);
        mNewsListView.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
        error.setText(message);
        Log.e(LOG_TAG, message);
    }


    @Override
    public Loader<List<NewsStory>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsStory>> loader, List<NewsStory> data) {
        mNewsAdapter.clear();
        if (data != null && !data.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            mNewsAdapter.addAll(data);
        } else {
            displayError(getString(R.string.error_no_result));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsStory>> loader) {
        mNewsAdapter.clear();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mNewsAdapter.clear();
        mNewsListView.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        updateNewsData();
        return true;
    }
}

