package choudhary96.sagar.p8_newsapp;

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
import java.util.List;

/**
 * references taken from www.stackoverflow.com and
 * www.developers.android.com
 */

public final class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    //base url for the news API
    private static final String HTTP_METHOD = "GET";
    private static final String BASE_URL = "http://content.guardianapis.com/search";

    //api-key for news API
    private static final String API_KEY = "3e746062-16e1-4066-9fc7-ca4a01c15727";

    //parameters to search in the json response
    private static final String RESPONSE = "response";
    private static final String RESULTS = "results";
    private static final String WEB_TITLE = "webTitle";
    private static final String WEB_URL = "webUrl";
    private static final String SECTION_ID = "sectionId";
    private static final String FIELDS = "fields";
    private static final String THUMBNAIL = "thumbnail";

    private QueryUtils() {
    }

    // build the url from which we have to recieve the json response
    public static URL buildUrl() {
        String finalUrl = BASE_URL + "?page-size=20&api-key=" + API_KEY + "&show-fields=thumbnail";
        URL url = null;
        try {
            url = new URL(finalUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    // fetch Json response and convert it to the string
    public static String fetchJsonResponse(URL url) {
        // StringBuilder to build string from json response
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

    public static List<NewsStory> fetchNewsStories(String newsStoryJSON) {

        ArrayList<NewsStory> newsStories = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(newsStoryJSON);
            JSONObject response = baseJsonResponse.getJSONObject(RESPONSE);
            JSONArray jsonStories = response.getJSONArray(RESULTS);
            for (int i = 0; i < jsonStories.length(); i++) {
                JSONObject jsonStory = jsonStories.getJSONObject(i);
                String title = jsonStory.getString(WEB_TITLE);
                String url = jsonStory.getString(WEB_URL);
                String category = jsonStory.getString(SECTION_ID);
                String thumbnailUrl = null;
                if (jsonStory.has(FIELDS)) {
                    JSONObject fieldsObject = jsonStory.getJSONObject(FIELDS);
                    if (fieldsObject != null) {
                        thumbnailUrl = fieldsObject.getString(THUMBNAIL);
                    }
                }

                NewsStory newsStory = new NewsStory(title, category, url, thumbnailUrl);
                newsStories.add(newsStory);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }
        return newsStories;
    }
}
