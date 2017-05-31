package choudhary96.sagar.p7_booklist;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final String HTTP_METHOD = "GET";
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    //query Parameters for the json search
    private static final String KEY_ITEMS = "items";
    private static final String KEY_VOLUME_INFO = "volumeInfo";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHORS = "authors";

    private QueryUtils() {
    }

    // return the url for the query entered by the user
    public static URL buildUrl(String searchQuery) {
        URL url = null;
        String finalUrl = BASE_URL + "?q=" + searchQuery + "&maxResults=15&printType=books";
        try {
            url = new URL(finalUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL: ", e);
        }
        return url;
    }

    //fetch JSON from URl
    public static String fetchJsonResponse(URL url) {

        StringBuilder output = new StringBuilder();
        InputStreamReader inputStreamReader;
        BufferedReader reader;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(HTTP_METHOD);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return output.toString();
    }



    public static ArrayList<Book> bookList(String json) {

        ArrayList<Book> books = new ArrayList<>();

        try {
            JSONObject jsonResult = new JSONObject(json);
            JSONArray arrayBooks = jsonResult.getJSONArray(KEY_ITEMS);

            for (int i = 0; i < arrayBooks.length(); i++) {

                JSONObject book = arrayBooks.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject(KEY_VOLUME_INFO);

                String title = volumeInfo.getString(KEY_TITLE);

                JSONArray Authors = volumeInfo.getJSONArray(KEY_AUTHORS);
                String author = Authors.getString(0);
                books.add(new Book(title, author));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }
        return books;
    }

}
