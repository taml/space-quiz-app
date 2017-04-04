package com.example.android.spacequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class SecondQuestion extends AppCompatActivity {

    int score;
    String username;
    boolean seeScore;
    TextView showUserScoreTextView;
    boolean checkedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);
        showUserScoreTextView = (TextView) findViewById(R.id.show_user_score);

        Intent secondIntent = getIntent();
        score = secondIntent.getExtras().getInt(ProjectVariables.PASS_SCORE);
        username = secondIntent.getStringExtra(ProjectVariables.PASS_USERNAME);
        seeScore = secondIntent.getExtras().getBoolean(ProjectVariables.PASS_SEE_SCORE);

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
            case R.id.second_answer_one:
                if (checked)
                    checkedButton = false;
                    break;
            case R.id.second_answer_second:
                if (checked)
                    checkedButton = false;
                    break;
            case R.id.second_answer_third:
                if (checked)
                    checkedButton = true;
                    break;
        }
    }

    public void checkAnswers(View view) {

        if (checkedButton) {
            score++;
        }

        keepUserScore();

        Intent intentThirdQuestion = new Intent(this, ThirdQuestion.class);
        intentThirdQuestion.putExtra(ProjectVariables.PASS_SCORE, score);
        intentThirdQuestion.putExtra(ProjectVariables.PASS_USERNAME, username);
        intentThirdQuestion.putExtra(ProjectVariables.PASS_SEE_SCORE, seeScore);
        startActivity(intentThirdQuestion);
        finish();
    }
}
