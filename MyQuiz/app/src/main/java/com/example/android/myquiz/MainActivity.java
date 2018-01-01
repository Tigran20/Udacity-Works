package com.example.android.myquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    private TextView mQuestionView;
    private TextView score;

    public String[] mQuestionsList;
    public String[] mCorrectAnswers;

    public int[][] mChoices = {
            {R.string.choice_1, R.string.choice_2, R.string.choice_3, R.string.choice_4},
            {R.string.choice_1_2, R.string.choice_2_2, R.string.choice_3_2, R.string.choice_4_2},
            {R.string.choice_1_3, R.string.choice_2_3, R.string.choice_3_3, R.string.choice_4_3},
            {R.string.choice_1_4, R.string.choice_2_4, R.string.choice_3_4, R.string.choice_4_4},
            {R.string.choice_1_5, R.string.choice_2_5, R.string.choice_3_5, R.string.choice_4_5},
            {R.string.choice_1_6, R.string.choice_2_6, R.string.choice_3_6, R.string.choice_4_6},
            {R.string.choice_1_7, R.string.choice_2_7, R.string.choice_3_7, R.string.choice_4_7},
            {R.string.choice_1_8, R.string.choice_2_8, R.string.choice_3_8, R.string.choice_4_8},
            {R.string.choice_1_9, R.string.choice_2_9, R.string.choice_3_9, R.string.choice_4_9},
            {R.string.choice_1_10, R.string.choice_2_10, R.string.choice_3_10, R.string.choice_4_10},
    };

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionsList = getResources().getStringArray(R.array.questions);
        mCorrectAnswers = getResources().getStringArray(R.array.answers);

        score = findViewById(R.id.score);
        mQuestionView = findViewById(R.id.question);

        answer1 = findViewById(R.id.answer_1);
        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);
        answer4 = findViewById(R.id.answer_4);

        answer1.setOnClickListener(new QuestionListener());
        answer2.setOnClickListener(new QuestionListener());
        answer3.setOnClickListener(new QuestionListener());
        answer4.setOnClickListener(new QuestionListener());

        updateQuestion();
    }

    private class QuestionListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            if (button.getText().toString().equals(mAnswer)) {
                updateScore();
                updateQuestion();
                if (mScore == 10) {
                    hideItems();
                    gameWin();
                }
            } else {
                gameOver();
            }
        }
    }

    private void updateQuestion() {
        if (mQuestionNumber < mQuestionsList.length) {

            mQuestionView.setText(mQuestionsList[mQuestionNumber]);

            answer1.setText(mChoices[mQuestionNumber][0]);
            answer2.setText(mChoices[mQuestionNumber][1]);
            answer3.setText(mChoices[mQuestionNumber][2]);
            answer4.setText(mChoices[mQuestionNumber][3]);

            mAnswer = mCorrectAnswers[mQuestionNumber];
            mQuestionNumber++;
        }
    }

    private void hideItems() {
        mQuestionView.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        answer1.setVisibility(View.INVISIBLE);
        answer2.setVisibility(View.INVISIBLE);
        answer3.setVisibility(View.INVISIBLE);
        answer4.setVisibility(View.INVISIBLE);
    }

    private void updateScore() {
        score.setText(getString(R.string.score) + ++mScore);
    }

    private void gameOver() {
        createAlert(getString(R.string.game_over) + getString(R.string.your_score) + "" + mScore + " " + getString(R.string.points));
    }

    private void gameWin() {
        createAlert(getString(R.string.game_win) + getString(R.string.your_score) + " " + mScore + " " + getString(R.string.points));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
                .setMessage(R.string.exit_question)
                .setCancelable(false)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        exitFromApp();
                    }
                }).create().show();
    }

    public void exitFromApp() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    void createAlert(String message) {
        new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exitFromApp();
                    }
                })
                .setPositiveButton(R.string.new_game, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }).create().show();
    }
}
