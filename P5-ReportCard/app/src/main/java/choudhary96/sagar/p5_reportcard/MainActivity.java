package choudhary96.sagar.p5_reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ReportCard> reportCards = new ArrayList<ReportCard>();
        reportCards.add(new ReportCard("COMPUTER 5201", 4, 'B'));
        reportCards.add(new ReportCard("ENGINEERING MATHEMATICS 6721", 5, 'A'));
        reportCards.add(new ReportCard("BEE 5361", 4, 'A'));
        reportCards.add(new ReportCard("BEEE 5461", 5, 'A'));
        reportCards.add(new ReportCard("ENGINEERING PHYSICS 5541", 5, 'A'));
        reportCards.add(new ReportCard("INTEGRATED PROJECT", 4, 'A'));
        reportCards.add(new ReportCard("ENGINEERING CHEMISTRY 5301", 4, 'B'));
        reportCards.add(new ReportCard("NETWORKING 287", 4, 'B'));


        ReportCardAdapter cardAdapter = new ReportCardAdapter(this, reportCards);
        ListView listView = (ListView) findViewById(R.id.reportCard);
        listView.setAdapter(cardAdapter);
    }
}
