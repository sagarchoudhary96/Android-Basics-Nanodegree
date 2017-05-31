package choudhary96.sagar.p8_newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by admin on 01-07-2016.
 */
public class NewsAdapter extends ArrayAdapter<NewsStory> {

    private final LayoutInflater inflater;
    NewsAdapter (Context context) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = inflater.inflate(R.layout.news_list_item, parent, false);
        }


        //get the reference to news content textView
        TextView news_title = (TextView) listItemView.findViewById(R.id.title);

        //get the reference to the news category
        TextView news_category = (TextView) listItemView.findViewById(R.id.category);

        //get the news located at that position
        NewsStory newsStory = getItem(position);

        //sets the title of news story list item
        news_title.setText(newsStory.mTitle);

        //set the category of news story list item
        news_category.setText(newsStory.mCategory);

        //get the thumbnail url
        String articleThumbnail = newsStory.mThumbnailUrl;

        //set the image of the story list item

        ImageView thumbnail_Image = (ImageView) listItemView.findViewById(R.id.article_thumbnail);

        Picasso.with(getContext())
                .load(articleThumbnail)
                .noFade()
                .into(thumbnail_Image);


        return listItemView;
    }
}
