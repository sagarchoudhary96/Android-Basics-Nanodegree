package choudhary96.sagar.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int score = 0 ;

    private static final String correctAnswer1 = "17"; // ans for ist question
    private static final String correctAnswer5 = "2";  // ans for the fifth question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void gradeCheck(View view) {

        //Response for first question
        EditText answerField1 = (EditText)findViewById(R.id.question_1_answer);
        String ques_1_answer = answerField1.getText().toString().trim();

        // Response for second question
        RadioButton ques_2 = (RadioButton)findViewById(R.id.question_2_option_2);
        boolean ques_2_answer = ques_2.isChecked();

        // Response for third question
        RadioButton ques_3 = (RadioButton)findViewById(R.id.question_3_option_3);
        boolean ques_3_answer = ques_3.isChecked();

        // Response for fourth question
        CheckBox optionOneChecked = (CheckBox)findViewById(R.id.question_4_option_1);
        boolean option_1_checked = optionOneChecked.isChecked();

        CheckBox optionTwoChecked = (CheckBox)findViewById(R.id.question_4_option_2);
        boolean option_2_checked = optionTwoChecked.isChecked();

        CheckBox optionThreeChecked = (CheckBox)findViewById(R.id.question_4_option_3);
        boolean option_3_checked = optionThreeChecked.isChecked();

        CheckBox optionFourChecked = (CheckBox)findViewById(R.id.question_4_option_4);
        boolean option_4_checked = optionFourChecked.isChecked();


        //Response for fifth question
        EditText answerField2 = (EditText)findViewById(R.id.question_5_answer);
        String ques_5_answer = answerField2.getText().toString().trim();

        //calculate the score
        score = getScore(ques_1_answer, ques_2_answer, ques_3_answer, option_1_checked, option_2_checked,option_3_checked, option_4_checked, ques_5_answer);


        // Toast message
        CharSequence text = "score is :" + score;
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

        //reset score back to zero
        score = 0;
    }

    public int getScore(String ques_1_ans,boolean ques_2_ans,
                        boolean ques_3_ans, boolean option_1_checked, boolean option_2_checked,
                        boolean option_3_checked, boolean option_4_checked, String ques_5_ans) {
        if (ques_1_ans.equals(correctAnswer1)) {
            score += 1;
        }

        if (ques_2_ans) {
            score += 1;
        }

        if (ques_3_ans) {
            score += 1;
        }

        if (option_2_checked && option_4_checked && !option_1_checked && !option_3_checked) {
            score += 1;
        }

        if (ques_5_ans.equals(correctAnswer5)) {
            score += 1;
        }

        return score;
    }
}
