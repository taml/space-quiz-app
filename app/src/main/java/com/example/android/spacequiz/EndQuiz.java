package com.example.android.spacequiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndQuiz extends AppCompatActivity {

    int score;
    String username;
    TextView userFinalScoreTextView;
    TextView userScoreSummaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_quiz);

        userFinalScoreTextView = (TextView) findViewById(R.id.user_final_score);
        userScoreSummaryTextView = (TextView) findViewById(R.id.score_summary);

        Intent secondIntent = getIntent();
        score = secondIntent.getExtras().getInt(ProjectVariables.PASS_SCORE);
        username = secondIntent.getStringExtra(ProjectVariables.PASS_USERNAME);
        displayFinalScore();
    }

    public void displayFinalScore() {
        userFinalScoreTextView.setText(getString(R.string.final_score, score));

        if (score >= 4) {
            userScoreSummaryTextView.setText(getString(R.string.scored_well, username));
        } else if (score >= 2) {
            userScoreSummaryTextView.setText(getString(R.string.scored_ok, username));
        } else {
            userScoreSummaryTextView.setText(getString(R.string.scored_low, username));
        }

    }

    public void shareResults(View view) {
        String intentSubject = getString(R.string.email_subject);
        String intentMessage = getString(R.string.email_message, username, score, username);
        if (username.equals(getString(R.string.guest))) {
            intentMessage = getString(R.string.email_guest_message, score);
        }

        Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, intentSubject);
            intentEmail.putExtra(Intent.EXTRA_TEXT, intentMessage);
        if (intentEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(intentEmail);
        }
    }

}
