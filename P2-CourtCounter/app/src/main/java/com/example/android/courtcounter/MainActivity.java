package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int ScoreA=0;
    int ScoreB=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addThreeForTeamA(View v){
        ScoreA=ScoreA+3;
        displayForTeamA(ScoreA);
    }
    public void addTwoForTeamA(View v){
        ScoreA=ScoreA+2;
        displayForTeamA(ScoreA);
    }
    public void freeThrowA(View v){
        ScoreA=ScoreA+1;
        displayForTeamA(ScoreA);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void addThreeForTeamB(View v){
        ScoreB=ScoreB+3;
        displayForTeamB(ScoreB);
    }
    public void addTwoForTeamB(View v){
        ScoreB=ScoreB+2;
        displayForTeamB(ScoreB);
    }
    public void freeThrowB(View v){
        ScoreB=ScoreB+1;
        displayForTeamB(ScoreB);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    public void Reset(View v){
        ScoreA=0;
        ScoreB=0;
        displayForTeamA(ScoreA);
        displayForTeamB(ScoreB);
    }
}
