package com.example.android.spacequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView usernameTextView;
    String username;
    public CheckBox seeScoreCheckBox;
    boolean seeScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameTextView = (TextView) findViewById(R.id.get_username);
        seeScoreCheckBox = (CheckBox) findViewById(R.id.see_your_score);

        if (savedInstanceState != null) {
            username = savedInstanceState.getString(ProjectVariables.STATE_INITIAL_USERNAME);
            seeScore = savedInstanceState.getBoolean(ProjectVariables.STATE_CHECKBOX_VALUE);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ProjectVariables.STATE_INITIAL_USERNAME, username);
        outState.putBoolean(ProjectVariables.STATE_CHECKBOX_VALUE, seeScore);

        super.onSaveInstanceState(outState);
    }


    public void continueQuiz(View view) {

        username = usernameTextView.getText().toString();
        seeScore = seeScoreCheckBox.isChecked();


        Intent intentFirstQuestion = new Intent(this, FirstQuestion.class);
        intentFirstQuestion.putExtra(ProjectVariables.PASS_USERNAME, username);
        intentFirstQuestion.putExtra(ProjectVariables.PASS_SEE_SCORE, seeScore);
        startActivity(intentFirstQuestion);
        finish();
    }
}
