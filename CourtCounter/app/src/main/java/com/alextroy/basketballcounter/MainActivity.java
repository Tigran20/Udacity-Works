package com.alextroy.basketballcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    EditText nameTeamA;
    EditText nameTeamB;

    String nameA;
    String nameB;

    TextView winnerTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addThreeForTeamA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }

    public void addTwoForTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    public void addThreeForTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }

    public void addTwoForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        findViewById(R.id.team_winner).setVisibility(View.INVISIBLE);
    }

    public void winner (View v){
        nameTeamA = findViewById(R.id.name_team_a);
        nameTeamB = findViewById(R.id.name_team_b);

        nameA = String.valueOf(nameTeamA.getText());
        nameB = String.valueOf(nameTeamB.getText());

        if (scoreTeamB > scoreTeamA) {
            if(!nameB.isEmpty())
                winnerTeam("TEAM " + nameB + " WIN");
            else {
                winnerTeam("TEAM B WIN");
            }
        }

        else if (scoreTeamB < scoreTeamA) {
            if(!nameA.isEmpty())
                winnerTeam("TEAM " + nameA + " WIN");
            else {
                winnerTeam("TEAM A WIN");
            }
        }

        else if (scoreTeamA == scoreTeamB) {
            winnerTeam("DRAW");
        }
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void winnerTeam(String team_name) {
        winnerTeam = (TextView) findViewById(R.id.team_winner);
        winnerTeam.setText(team_name);
        findViewById(R.id.team_winner).setVisibility(View.VISIBLE);
    }




}
