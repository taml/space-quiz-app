package com.example.android.spacequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class FourthQuestion extends AppCompatActivity {

    int score;
    String username;
    boolean seeScore;
    boolean checkedButton;
    TextView showUserScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);

        showUserScoreTextView = (TextView) findViewById(R.id.show_user_score);

        Intent fourthIntent = getIntent();
        score = fourthIntent.getExtras().getInt(ProjectVariables.PASS_SCORE);
        username = fourthIntent.getStringExtra(ProjectVariables.PASS_USERNAME);
        seeScore = fourthIntent.getExtras().getBoolean(ProjectVariables.PASS_SEE_SCORE);

        keepUserScore();

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(ProjectVariables.STATE_SCORE);
            username = savedInstanceState.getString(ProjectVariables.STATE_USERNAME);
            showUserScoreTextView.setText(savedInstanceState.getString(ProjectVariables.STATE_USER_SCORE_TEXT));
            checkedButton = savedInstanceState.getBoolean(ProjectVariables.STATE_CHECKBOX_IS_CHECKED);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ProjectVariables.STATE_SCORE, score);
        outState.putString(ProjectVariables.STATE_USERNAME, username);
        outState.putString(ProjectVariables.STATE_USER_SCORE_TEXT, showUserScoreTextView.getText().toString());
        outState.putBoolean(ProjectVariables.STATE_CHECKBOX_IS_CHECKED, checkedButton);

        super.onSaveInstanceState(outState);
    }

    public void keepUserScore() {
        if (seeScore) {
            showUserScoreTextView.setText(getString(R.string.score_information, username, score));
        }
    }

    public void onRadioButtonTapped(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.fourth_answer_one:
                if (checked)
                    checkedButton = true;
                    break;
            case R.id.fourth_answer_second:
                if (checked)
                    checkedButton = false;
                    break;
        }
    }

    public void checkAnswers(View view) {

        if (checkedButton) {
            score++;
        }

        keepUserScore();

        Intent intentFifthQuestion = new Intent(this, FifthQuestion.class);
        intentFifthQuestion.putExtra(ProjectVariables.PASS_SCORE, score);
        intentFifthQuestion.putExtra(ProjectVariables.PASS_USERNAME, username);
        intentFifthQuestion.putExtra(ProjectVariables.PASS_SEE_SCORE, seeScore);
        startActivity(intentFifthQuestion);
        finish();
    }

}
