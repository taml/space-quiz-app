package com.example.android.spacequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThirdQuestion extends AppCompatActivity {

    int score;
    String username;
    boolean seeScore;
    TextView showUserScoreTextView;
    TextView getUserAnswerTextView;
    String q3AnswerFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);

        showUserScoreTextView = (TextView) findViewById(R.id.show_user_score);
        getUserAnswerTextView = (TextView) findViewById(R.id.third_answer_first);

        Intent thirdIntent = getIntent();
        score = thirdIntent.getExtras().getInt(ProjectVariables.PASS_SCORE);
        username = thirdIntent.getStringExtra(ProjectVariables.PASS_USERNAME);
        seeScore = thirdIntent.getExtras().getBoolean(ProjectVariables.PASS_SEE_SCORE);

        keepUserScore();

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(ProjectVariables.STATE_SCORE);
            username = savedInstanceState.getString(ProjectVariables.STATE_USERNAME);
            showUserScoreTextView.setText(savedInstanceState.getString(ProjectVariables.STATE_USER_SCORE_TEXT));
            q3AnswerFirst = savedInstanceState.getString(ProjectVariables.STATE_QUESTION_A1);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ProjectVariables.STATE_SCORE, score);
        outState.putString(ProjectVariables.STATE_USERNAME, username);
        outState.putString(ProjectVariables.STATE_USER_SCORE_TEXT, showUserScoreTextView.getText().toString());
        outState.putString(ProjectVariables.STATE_QUESTION_A1, q3AnswerFirst);

        super.onSaveInstanceState(outState);
    }

    public void keepUserScore() {
        if (seeScore) {
            showUserScoreTextView.setText(getString(R.string.score_information, username, score));
        }
    }

    public void checkAnswers(View view) {
        q3AnswerFirst = getUserAnswerTextView.getText().toString();

        if(q3AnswerFirst.equals("1969")) {
            score++;
        }
        keepUserScore();

        Intent intentFourthQuestion = new Intent(this, FourthQuestion.class);
        intentFourthQuestion.putExtra(ProjectVariables.PASS_SCORE, score);
        intentFourthQuestion.putExtra(ProjectVariables.PASS_USERNAME, username);
        intentFourthQuestion.putExtra(ProjectVariables.PASS_SEE_SCORE, seeScore);
        startActivity(intentFourthQuestion);
        finish();

    }

}
