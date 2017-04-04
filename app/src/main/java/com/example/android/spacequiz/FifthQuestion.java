package com.example.android.spacequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class FifthQuestion extends AppCompatActivity {

    int score;
    String username;
    boolean seeScore;
    TextView showUserScoreTextView;
    private CheckBox getUserAnswerQ5First;
    private CheckBox getUserAnswerQ5Second;
    private CheckBox getUserAnswerQ5Third;
    private CheckBox getUserAnswerQ5Fourth;
    boolean q5AnswerFirst;
    boolean q5AnswerSecond;
    boolean q5AnswerThird;
    boolean q5AnswerFourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_question);

        showUserScoreTextView = (TextView) findViewById(R.id.show_user_score);
        getUserAnswerQ5First = (CheckBox) findViewById(R.id.fifth_answer_one);
        getUserAnswerQ5Second = (CheckBox) findViewById(R.id.fifth_answer_second);
        getUserAnswerQ5Third = (CheckBox) findViewById(R.id.fifth_answer_three);
        getUserAnswerQ5Fourth = (CheckBox) findViewById(R.id.fifth_answer_four);

        Intent fifthIntent = getIntent();
        score = fifthIntent.getExtras().getInt(ProjectVariables.PASS_SCORE);
        username = fifthIntent.getStringExtra(ProjectVariables.PASS_USERNAME);
        seeScore = fifthIntent.getExtras().getBoolean(ProjectVariables.PASS_SEE_SCORE);

        keepUserScore();

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(ProjectVariables.STATE_SCORE);
            username = savedInstanceState.getString(ProjectVariables.STATE_USERNAME);
            showUserScoreTextView.setText(savedInstanceState.getString(ProjectVariables.STATE_USER_SCORE_TEXT));
            q5AnswerFirst = savedInstanceState.getBoolean(ProjectVariables.STATE_QUESTION_A1);
            q5AnswerSecond = savedInstanceState.getBoolean(ProjectVariables.STATE_QUESTION_A2);
            q5AnswerThird = savedInstanceState.getBoolean(ProjectVariables.STATE_QUESTION_A3);
            q5AnswerFourth = savedInstanceState.getBoolean(ProjectVariables.STATE_QUESTION_A4);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ProjectVariables.STATE_SCORE, score);
        outState.putString(ProjectVariables.STATE_USERNAME, username);
        outState.putString(ProjectVariables.STATE_USER_SCORE_TEXT, showUserScoreTextView.getText().toString());
        outState.putBoolean(ProjectVariables.STATE_QUESTION_A1, q5AnswerFirst);
        outState.putBoolean(ProjectVariables.STATE_QUESTION_A2, q5AnswerSecond);
        outState.putBoolean(ProjectVariables.STATE_QUESTION_A3, q5AnswerThird);
        outState.putBoolean(ProjectVariables.STATE_QUESTION_A1, q5AnswerFourth);

        super.onSaveInstanceState(outState);
    }

    public void keepUserScore() {
        if (seeScore) {
            showUserScoreTextView.setText(getString(R.string.score_information, username, score));
        }
    }

    public void checkAnswers(View view) {
        q5AnswerFirst = getUserAnswerQ5First.isChecked();
        q5AnswerSecond = getUserAnswerQ5Second.isChecked();
        q5AnswerThird = getUserAnswerQ5Third.isChecked();
        q5AnswerFourth = getUserAnswerQ5Fourth.isChecked();

        if (q5AnswerSecond && q5AnswerFourth && !q5AnswerFirst && !q5AnswerThird) {
            score++;
        }

        keepUserScore();

        Toast.makeText(this, getString(R.string.final_score, score), Toast.LENGTH_SHORT).show();

        Intent intentEndQuiz = new Intent(this, EndQuiz.class);
        intentEndQuiz.putExtra(ProjectVariables.PASS_SCORE, score);
        intentEndQuiz.putExtra(ProjectVariables.PASS_USERNAME, username);
        intentEndQuiz.putExtra(ProjectVariables.PASS_SEE_SCORE, seeScore);
        startActivity(intentEndQuiz);
        finish();

    }

}
