package choudhary96.sagar.p7_booklist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by admin on 30-06-2016.
 */
public class BookAdapter extends ArrayAdapter<Book> {


    BookAdapter(Activity activity) {
        super(activity, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //get the book located at position
        Book book_item = getItem(position);

        //get the textview in list item
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(book_item.title);

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText("Author: " + book_item.author);
        return listItemView;
    }
}
