package choudhary96.sagar.p8_newsapp;

import android.net.Uri;

public class NewsStory {

    public final String mTitle;
    public final String mCategory;
    public final String mThumbnailUrl;
    public final Uri mUri;

    NewsStory(String title, String category, String url, String thumbnailUrl) {

        this.mTitle = title;

        this.mThumbnailUrl = thumbnailUrl;

        this.mCategory = category;

        this.mUri = Uri.parse(url);
    }
}
