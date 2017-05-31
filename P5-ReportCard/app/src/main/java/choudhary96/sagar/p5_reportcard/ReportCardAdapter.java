package choudhary96.sagar.p5_reportcard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 29-06-2016.
 */
public class ReportCardAdapter extends ArrayAdapter<ReportCard> {


    public ReportCardAdapter(Activity context, ArrayList<ReportCard> reportCard) {

        super(context, 0, reportCard);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ReportCard currentReportCard = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        nameTextView.setText(currentReportCard.getCourseName());

        TextView creditTextView = (TextView) listItemView.findViewById(R.id.credit);
        creditTextView.setText(currentReportCard.creditToString());

        TextView gradeTextView = (TextView) listItemView.findViewById(R.id.grade);
        gradeTextView.setText(currentReportCard.gradeToString());

        return listItemView;
    }
}
