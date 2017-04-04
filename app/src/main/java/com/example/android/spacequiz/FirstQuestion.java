package com.example.android.spacequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class FirstQuestion extends AppCompatActivity {

    int score = 0;
    String username;
    boolean seeScore;
    private TextView showUserScoreTextView;
    private CheckBox getUserAnswerQ1First;
    private CheckBox getUserAnswerQ1Second;
    private CheckBox getUserAnswerQ1Third;
    private CheckBox getUserAnswerQ1Fourth;
    boolean q1AnswerFirst;
    boolean q1AnswerSecond;
    boolean q1AnswerThird;
    boolean q1AnswerFourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);
        showUserScoreTextView = (TextView) findViewById(R.id.show_user_score);

        Intent firstIntent = getIntent();
        username = firstIntent.getStringExtra(ProjectVariables.PASS_USERNAME);
        seeScore = firstIntent.getExtras().getBoolean(ProjectVariables.PASS_SEE_SCORE);

        if (username.equals("")) {
            username = getString(R.string.guest);
        }
        keepUserScore();

        getUserAnswerQ1First = (CheckBox) findViewById(R.id.first_answer_one);
        getUserAnswerQ1Second = (CheckBox) findViewById(R.id.first_answer_second);
        getUserAnswerQ1Third = (CheckBox) findViewById(R.id.first_answer_three);
        getUserAnswerQ1Fourth = (CheckBox) findViewById(R.id.first_answer_four);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(ProjectVariables.STATE_SCORE);
            username = savedInstanceState.getString(ProjectVariables.STATE_USERNAME);
            showUserScoreTextView.setText(savedInstanceState.getString(ProjectVariables.STATE_USER_SCORE_TEXT));
            q1AnswerFirst = savedInstanceState.getBoolean(ProjectVariables.STATE_QUESTION_A1);
            q1AnswerSecond = savedInstanceState.getBoolean(ProjectVariables.STATE_QUESTION_A2);
            q1AnswerThird = savedInstanceState.getBoolean(ProjectVariables.STATE_QUESTION_A3);
            q1AnswerFourth = savedInstanceState.getBoolean(ProjectVariables.STATE_QUESTION_A4);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ProjectVariables.STATE_SCORE, score);
        outState.putString(ProjectVariables.STATE_USERNAME, username);
        outState.putString(ProjectVariables.STATE_USER_SCORE_TEXT, showUserScoreTextView.getText().toString());
        outState.putBoolean(ProjectVariables.STATE_QUESTION_A1, q1AnswerFirst);
        outState.putBoolean(ProjectVariables.STATE_QUESTION_A2, q1AnswerSecond);
        outState.putBoolean(ProjectVariables.STATE_QUESTION_A3, q1AnswerThird);
        outState.putBoolean(ProjectVariables.STATE_QUESTION_A1, q1AnswerFourth);

        super.onSaveInstanceState(outState);
    }


    public void keepUserScore() {
        if (seeScore) {
            showUserScoreTextView.setText(getString(R.string.score_information, username, score));
        }
    }

    public void checkAnswers(View view) {
        q1AnswerFirst = getUserAnswerQ1First.isChecked();
        q1AnswerSecond = getUserAnswerQ1Second.isChecked();
        q1AnswerThird = getUserAnswerQ1Third.isChecked();
        q1AnswerFourth = getUserAnswerQ1Fourth.isChecked();

        if (q1AnswerFirst && q1AnswerSecond && q1AnswerFourth && !q1AnswerThird) {
            score++;
        }

        keepUserScore();

        Intent intentSecondQuestion = new Intent(this, SecondQuestion.class);
        intentSecondQuestion.putExtra(ProjectVariables.PASS_SCORE, score);
        intentSecondQuestion.putExtra(ProjectVariables.PASS_USERNAME, username);
        intentSecondQuestion.putExtra(ProjectVariables.PASS_SEE_SCORE, seeScore);
        startActivity(intentSecondQuestion);
        finish();

    }


}