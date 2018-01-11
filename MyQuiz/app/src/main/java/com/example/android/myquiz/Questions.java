package com.example.android.myquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Questions extends AppCompatActivity {

    //Константы для сохранения данных
    public static final String USER_NAME_EXTRA = "user_name_extra";
    public static final String SCORE = "score";
    public static final String QUESTION_NUMBER = "question_number";
    public static final String HEALTH_POINTS = "health";

    //Переменная для имени
    private String mUserName;

    //Вьюхи для отображения счета, хп и вопроса
    private TextView score;
    private TextView hp;
    private TextView mQuestionView;

    //Кнопки с вариантами ответов
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    //Переменная для правильного ответа
    private String mAnswer;

    //Переменная для количества хп
    private int mHealth = 3;

    //Переменная для счета
    private int mScore = 0;

    //Переменная для номера вопроса
    private int mQuestionNumber = 0;

    //Переменная для количества неправильных ответов
    private int mUncorrectAnswer;

    //Массивы для хранения вопросов и правильных ответов
    private String[] mQuestionsList;
    private String[] mCorrectAnswers;

    //Массив для хранения всех вариантов ответов
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        score = findViewById(R.id.score);
        hp = findViewById(R.id.tv_hp);
        mQuestionView = findViewById(R.id.question);

        answer1 = findViewById(R.id.answer_1);
        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);
        answer4 = findViewById(R.id.answer_4);

        answer1.setOnClickListener(new QuestionListener());
        answer2.setOnClickListener(new QuestionListener());
        answer3.setOnClickListener(new QuestionListener());
        answer4.setOnClickListener(new QuestionListener());

        Intent intent = getIntent();
        mUserName = intent.getStringExtra(USER_NAME_EXTRA);

        mQuestionsList = getResources().getStringArray(R.array.questions);
        mCorrectAnswers = getResources().getStringArray(R.array.answers);

        if (savedInstanceState != null) {
            mUserName = savedInstanceState.getString(USER_NAME_EXTRA);
            mScore = savedInstanceState.getInt(SCORE);
            mHealth = savedInstanceState.getInt(HEALTH_POINTS);
            mQuestionNumber = savedInstanceState.getInt(QUESTION_NUMBER);
        }

        score.setText(getString(R.string.score) + getString(R.string.space) + mScore);
        updateQuestion(mQuestionNumber);
        hp.setText(String.valueOf(mHealth));
    }


    public class QuestionListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            if (button.getText().toString().equals(mAnswer)) {

                updateScore();
                mQuestionNumber++;
                updateQuestion(mQuestionNumber);

            } else if (!button.getText().toString().equals(mAnswer)) {
                mQuestionNumber++;
                updateQuestion(mQuestionNumber);
                mUncorrectAnswer++;
                mHealth--;
                if (mHealth == 0) {
                    gameOver();
                    hideItems();
                }
                hp.setText(String.valueOf(mHealth));
            } else if (mScore == 10 || mQuestionNumber == 9) {
                gameWin();
                hideItems();
            }
        }
    }

    private void updateQuestion(int Question) {
        if (mQuestionNumber < mQuestionsList.length) {

            mQuestionView.setText(mQuestionsList[mQuestionNumber]);

            answer1.setText(mChoices[mQuestionNumber][0]);
            answer2.setText(mChoices[mQuestionNumber][1]);
            answer3.setText(mChoices[mQuestionNumber][2]);
            answer4.setText(mChoices[mQuestionNumber][3]);

            mAnswer = mCorrectAnswers[mQuestionNumber];
        } else
            gameWin();
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
        score.setText(getString(R.string.score) + getString(R.string.space) + ++mScore);
    }

    private void gameOver() {
        createAlert((mUserName)
                + ", "
                + getString(R.string.game_over)
                + getString(R.string.your_score)
                + ""
                + mScore
                + getString(R.string.space)
                + getString(R.string.points));
    }

    private void gameWin() {
        createAlert((mUserName)
                + ", "
                + getString(R.string.game_win)
                + getString(R.string.your_score)
                + ""
                + mScore
                + getString(R.string.space)
                + getString(R.string.points));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
                .setMessage(R.string.exit_question)
                .setCancelable(false)
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Questions.this.finish();
                    }
                }).create().show();
    }

    public void exitFromApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    void createAlert(String message) {
        new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        exitFromApp();
                        finish();
                    }
                })
                .setPositiveButton(R.string.new_game, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                }).create().show();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(Questions.USER_NAME_EXTRA, mUserName);
        outState.putInt(Questions.SCORE, mScore);
        outState.putInt(Questions.HEALTH_POINTS, mHealth);
        outState.putInt(Questions.QUESTION_NUMBER, mQuestionNumber);

        super.onSaveInstanceState(outState);

    }
}
