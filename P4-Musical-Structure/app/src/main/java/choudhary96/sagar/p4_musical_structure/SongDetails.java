package choudhary96.sagar.p4_musical_structure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SongDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);
        Button paymentPageButton = (Button)findViewById(R.id.payment_button);
        paymentPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaymentInfo.class);
                startActivity(intent);
            }
        });
        Button nowPlayingButton = (Button)findViewById(R.id.now_playing_button);
        nowPlayingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NowPlaying.class);
                startActivity(i);

            }
        });
    }
}
