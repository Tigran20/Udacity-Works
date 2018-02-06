package com.alextroy.basketballcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String SCORE_TEAM_A = "score_team_a";
    private static final String SCORE_TEAM_B = "score_team_b";

    private int mScoreTeamA = 0;
    private int mScoreTeamB = 0;

    private EditText mNameTeamA;
    private EditText mNameTeamB;

    private String mNameA;
    private String mNameB;

    private TextView mScoreViewTeamA;
    private TextView mScoreViewTeamB;
    private TextView mWinnerTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreViewTeamA = findViewById(R.id.team_a_score);
        mScoreViewTeamB = findViewById(R.id.team_b_score);

        if(savedInstanceState != null) {
            mScoreTeamA = savedInstanceState.getInt(SCORE_TEAM_A);
            mScoreTeamB = savedInstanceState.getInt(SCORE_TEAM_B);
        }
    }

    public void addThreeForTeamA(View v) {
        mScoreTeamA = mScoreTeamA + 3;
        displayForTeamA(mScoreTeamA);
    }

    public void addTwoForTeamA(View v) {
        mScoreTeamA = mScoreTeamA + 2;
        displayForTeamA(mScoreTeamA);
    }

    public void addOneForTeamA(View v) {
        mScoreTeamA = mScoreTeamA + 1;
        displayForTeamA(mScoreTeamA);
    }

    public void addThreeForTeamB(View v) {
        mScoreTeamB = mScoreTeamB + 3;
        displayForTeamB(mScoreTeamB);
    }

    public void addTwoForTeamB(View v) {
        mScoreTeamB = mScoreTeamB + 2;
        displayForTeamB(mScoreTeamB);
    }

    public void addOneForTeamB(View v) {
        mScoreTeamB = mScoreTeamB + 1;
        displayForTeamB(mScoreTeamB);
    }

    public void resetScore(View v) {
        mScoreTeamA = 0;
        mScoreTeamB = 0;
        displayForTeamA(mScoreTeamA);
        displayForTeamB(mScoreTeamB);
        findViewById(R.id.team_winner).setVisibility(View.INVISIBLE);
    }

    public void winner(View v) {
        mNameTeamA = findViewById(R.id.name_team_a);
        mNameTeamB = findViewById(R.id.name_team_b);

        mNameA = String.valueOf(mNameTeamA.getText());
        mNameB = String.valueOf(mNameTeamB.getText());

        if (mScoreTeamB > mScoreTeamA) {
            if (!mNameB.isEmpty())
                winnerTeam("TEAM " + mNameB + " WIN");
            else {
                winnerTeam("TEAM B WIN");
            }
        } else if (mScoreTeamB < mScoreTeamA) {
            if (!mNameA.isEmpty())
                winnerTeam("TEAM " + mNameA + " WIN");
            else {
                winnerTeam("TEAM A WIN");
            }
        } else if (mScoreTeamA == mScoreTeamB) {
            winnerTeam("DRAW");
        }
    }

    public void displayForTeamA(int score) {
        mScoreViewTeamA.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {

        mScoreViewTeamB.setText(String.valueOf(score));
    }

    public void winnerTeam(String team_name) {
        mWinnerTeam = findViewById(R.id.team_winner);
        mWinnerTeam.setText(team_name);
        findViewById(R.id.team_winner).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_TEAM_A, mScoreTeamA);
        outState.putInt(SCORE_TEAM_B, mScoreTeamB);
    }
}
