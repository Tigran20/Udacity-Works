package com.example.android.myquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private TextView mHealthPointsTV;
    private TextView mQuestionViewTV;

    //Кнопки с вариантами ответов
    private Button mAnswerBtn1;
    private Button mAnswerBtn2;
    private Button mAnswerBtn3;
    private Button mAnswerBtn4;

    //РадиоБаттоны с вариантами ответов
    private RadioButton mAnswerRB1;
    private RadioButton mAnswerRB2;
    private RadioButton mAnswerRB3;
    private RadioButton mAnswerRB4;

    private CheckBox mAnswerCB1;
    private CheckBox mAnswerCB2;
    private CheckBox mAnswerCB3;
    private CheckBox mAnswerCB4;

    private EditText mAnswerET;
    private Button mNext;
    private Button mNextCB;

    //Переменная для правильного ответа
    private String mAnswer;

    //Переменная для количества хп
    private int mHealth = 3;

    //Переменная для счета
    private int mScore = 0;

    //Переменная для номера вопроса
    private int mQuestionNumber = 0;

    private RadioGroup mRadioGroup;
    private View mBtnGroup;
    private View mEtField;
    private View mCBGroup;

    //Переменная для количества неправильных ответов
    private int mUncorrectAnswer;

    //Задержка, чтобы увидеть ответ РадиоБаттона
    private Handler handler;

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

        init();

        if (savedInstanceState != null) {
            mUserName = savedInstanceState.getString(USER_NAME_EXTRA);
            mScore = savedInstanceState.getInt(SCORE);
            mHealth = savedInstanceState.getInt(HEALTH_POINTS);
            mQuestionNumber = savedInstanceState.getInt(QUESTION_NUMBER);
        }

        Intent intent = getIntent();
        mUserName = intent.getStringExtra(USER_NAME_EXTRA);

        mQuestionsList = getResources().getStringArray(R.array.questions);
        mCorrectAnswers = getResources().getStringArray(R.array.answers);

        score.setText(getString(R.string.score) + getString(R.string.space) + mScore);
        updateQuestion(mQuestionNumber);
        mHealthPointsTV.setText(String.valueOf(mHealth));
        updateLL(mQuestionNumber);
    }

    public class QuestionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            if (button.getText().toString().equals(mAnswer)) {
                updateScore();
                mQuestionNumber++;
                updateQuestion(mQuestionNumber);
                updateLL(mQuestionNumber);
            } else if (!button.getText().toString().equals(mAnswer)) {
                mQuestionNumber++;
                updateQuestion(mQuestionNumber);
                updateLL(mQuestionNumber);
                mUncorrectAnswer++;
                mHealth--;
                if (mHealth == 0) {
                    gameOver();
                    hideItems();
                }
                mHealthPointsTV.setText(String.valueOf(mHealth));
            }
        }
    }

    public class RBListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            RadioButton rb = (RadioButton) view;
            switch (rb.getId()) {
                case R.id.answer_rb_1:
                    if (mAnswerRB1.isChecked()) {
                        mAnswerRB1.setTextColor(Color.GREEN);
                        mAnswerRB2.setEnabled(false);
                        mAnswerRB3.setEnabled(false);
                        mAnswerRB4.setEnabled(false);
                    }
                    handlerRb();
                    updateScore();
                    break;
                case R.id.answer_rb_2:
                    if (mAnswerRB2.isChecked()) {
                        mAnswerRB2.setTextColor(Color.RED);
                        mAnswerRB1.setEnabled(false);
                        mAnswerRB3.setEnabled(false);
                        mAnswerRB4.setEnabled(false);
                    }
                    handlerRb();
                    lessHpForRadioButton();
                    break;
                case R.id.answer_rb_3:
                    if (mAnswerRB3.isChecked()) {
                        mAnswerRB3.setTextColor(Color.RED);
                        mAnswerRB1.setEnabled(false);
                        mAnswerRB2.setEnabled(false);
                        mAnswerRB4.setEnabled(false);
                    }
                    handlerRb();
                    lessHpForRadioButton();
                    break;
                case R.id.answer_rb_4:
                    if (mAnswerRB4.isChecked()) {
                        mAnswerRB4.setTextColor(Color.RED);
                        mAnswerRB1.setEnabled(false);
                        mAnswerRB2.setEnabled(false);
                        mAnswerRB3.setEnabled(false);
                    }
                    handlerRb();
                    lessHpForRadioButton();
                    break;

                default:
                    break;
            }
        }
    }

    private class CBListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            CheckBox cb = (CheckBox) view;
            switch (cb.getId()) {
                case R.id.answer_cb_1:
                    if (mAnswerCB1.isChecked()) {
//                        mAnswerCB1.setTextColor(Color.GREEN);
                    }
                    break;
                case R.id.answer_cb_2:
                    if (mAnswerCB2.isChecked()) {
//                        mAnswerCB2.setTextColor(Color.RED);
                    }
                    break;
                case R.id.answer_cb_3:
                    if (mAnswerCB3.isChecked()) {
//                        mAnswerCB3.setTextColor(Color.GREEN);
                    }
                    break;
                case R.id.answer_cb_4:
                    if (mAnswerCB4.isChecked()) {
//                        mAnswerCB4.setTextColor(Color.RED);
                    }
                    break;
            }
        }
    }

    private void updateQuestion(int Question) {
        if (mQuestionNumber < mQuestionsList.length) {
            mQuestionViewTV.setText(mQuestionsList[mQuestionNumber]);

            mAnswerBtn1.setText(mChoices[mQuestionNumber][0]);
            mAnswerBtn2.setText(mChoices[mQuestionNumber][1]);
            mAnswerBtn3.setText(mChoices[mQuestionNumber][2]);
            mAnswerBtn4.setText(mChoices[mQuestionNumber][3]);

            mAnswerCB1.setText(mChoices[mQuestionNumber][0]);
            mAnswerCB2.setText(mChoices[mQuestionNumber][1]);
            mAnswerCB3.setText(mChoices[mQuestionNumber][2]);
            mAnswerCB4.setText(mChoices[mQuestionNumber][3]);

            mAnswerRB1.setText(mChoices[mQuestionNumber][0]);
            mAnswerRB2.setText(mChoices[mQuestionNumber][1]);
            mAnswerRB3.setText(mChoices[mQuestionNumber][2]);
            mAnswerRB4.setText(mChoices[mQuestionNumber][3]);

            mAnswer = mCorrectAnswers[mQuestionNumber];
        } else if (mScore > 6) {
            gameWin();
            hideItems();
        } else
            gameWin();
            hideItems();
    }

    private void hideItems() {
        mQuestionViewTV.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        mBtnGroup.setVisibility(View.INVISIBLE);
        mRadioGroup.setVisibility(View.INVISIBLE);
        mCBGroup.setVisibility(View.INVISIBLE);
        mEtField.setVisibility(View.INVISIBLE);
        mNextCB.setVisibility(View.INVISIBLE);
        mNext.setVisibility(View.INVISIBLE);
    }

    private void updateLL(int n) {
        if (n == 0) {
            mQuestionViewTV.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            mRadioGroup.setVisibility(View.VISIBLE);
            mEtField.setVisibility(View.INVISIBLE);
            mBtnGroup.setVisibility(View.INVISIBLE);
            mCBGroup.setVisibility(View.INVISIBLE);
            mNextCB.setVisibility(View.INVISIBLE);
            mNext.setVisibility(View.INVISIBLE);
        } else if (n == 4) {
            mQuestionViewTV.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            mNext.setVisibility(View.VISIBLE);
            mEtField.setVisibility(View.VISIBLE);
            mRadioGroup.setVisibility(View.INVISIBLE);
            mBtnGroup.setVisibility(View.INVISIBLE);
            mCBGroup.setVisibility(View.INVISIBLE);
            mNextCB.setVisibility(View.INVISIBLE);
        } else if (n == 6) {
            mQuestionViewTV.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            mCBGroup.setVisibility(View.VISIBLE);
            mNextCB.setVisibility(View.VISIBLE);
            mEtField.setVisibility(View.INVISIBLE);
            mRadioGroup.setVisibility(View.INVISIBLE);
            mBtnGroup.setVisibility(View.INVISIBLE);
            mNext.setVisibility(View.INVISIBLE);
        } else if (n < 10) {
            mQuestionViewTV.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            mBtnGroup.setVisibility(View.VISIBLE);
            mRadioGroup.setVisibility(View.INVISIBLE);
            mEtField.setVisibility(View.INVISIBLE);
            mCBGroup.setVisibility(View.INVISIBLE);
            mNextCB.setVisibility(View.INVISIBLE);
            mNext.setVisibility(View.INVISIBLE);
        } else {
            hideItems();
        }
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


    void createAlert(String message) {
        new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                })
                .setPositiveButton(R.string.new_game, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent startMainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startMainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startMainIntent.putExtra(USER_NAME_EXTRA, mUserName);
                        startActivity(startMainIntent);
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

    public void handlerRb() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mQuestionNumber++;
                updateQuestion(mQuestionNumber);
                updateLL(mQuestionNumber);
            }
        }, 500);
    }

    public void lessHpForRadioButton() {
        mUncorrectAnswer++;
        mHealth--;
        mHealthPointsTV.setText(String.valueOf(mHealth));
    }

    public void init() {
        score = findViewById(R.id.score);
        mHealthPointsTV = findViewById(R.id.tv_hp);
        mQuestionViewTV = findViewById(R.id.question);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_gr);
        mBtnGroup = (LinearLayout) findViewById(R.id.btn_group);
        mEtField = (LinearLayout) findViewById(R.id.edit_text_answer);
        mCBGroup = (LinearLayout) findViewById(R.id.checkbox_gr);

        mAnswerET = (EditText) findViewById(R.id.edit_text);
        mNext = (Button) findViewById(R.id.next_button);
        mNextCB = (Button) findViewById(R.id.next_button_cb);

        mAnswerRB1 = findViewById(R.id.answer_rb_1);
        mAnswerRB2 = findViewById(R.id.answer_rb_2);
        mAnswerRB3 = findViewById(R.id.answer_rb_3);
        mAnswerRB4 = findViewById(R.id.answer_rb_4);

        mAnswerBtn1 = findViewById(R.id.answer_1);
        mAnswerBtn2 = findViewById(R.id.answer_2);
        mAnswerBtn3 = findViewById(R.id.answer_3);
        mAnswerBtn4 = findViewById(R.id.answer_4);

        mAnswerCB1 = findViewById(R.id.answer_cb_1);
        mAnswerCB2 = findViewById(R.id.answer_cb_2);
        mAnswerCB3 = findViewById(R.id.answer_cb_3);
        mAnswerCB4 = findViewById(R.id.answer_cb_4);

        mAnswerBtn1.setOnClickListener(new QuestionListener());
        mAnswerBtn2.setOnClickListener(new QuestionListener());
        mAnswerBtn3.setOnClickListener(new QuestionListener());
        mAnswerBtn4.setOnClickListener(new QuestionListener());

        mAnswerRB1.setOnClickListener(new RBListener());
        mAnswerRB2.setOnClickListener(new RBListener());
        mAnswerRB3.setOnClickListener(new RBListener());
        mAnswerRB4.setOnClickListener(new RBListener());

        mAnswerCB1.setOnClickListener(new CBListener());
        mAnswerCB2.setOnClickListener(new CBListener());
        mAnswerCB3.setOnClickListener(new CBListener());
        mAnswerCB4.setOnClickListener(new CBListener());

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerET.getText().toString().equalsIgnoreCase("Venus") ||
                        mAnswerET.getText().toString().equalsIgnoreCase("Венера")) {
                    updateScore();
                    mQuestionNumber++;
                    updateQuestion(mQuestionNumber);
                    updateLL(mQuestionNumber);
                } else {
                    mQuestionNumber++;
                    updateQuestion(mQuestionNumber);
                    updateLL(mQuestionNumber);
                    mUncorrectAnswer++;
                    mHealth--;
                    if (mHealth == 0) {
                        gameOver();
                        hideItems();
                    }
                    mHealthPointsTV.setText(String.valueOf(mHealth));
                }
            }
        });

        mNextCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerCB1.isChecked() && mAnswerCB3.isChecked()) {
                    updateScore();
                    mQuestionNumber++;
                    updateQuestion(mQuestionNumber);
                    updateLL(mQuestionNumber);
                } else {
                    mQuestionNumber++;
                    updateQuestion(mQuestionNumber);
                    updateLL(mQuestionNumber);
                    mUncorrectAnswer++;
                    mHealth--;
                    if (mHealth == 0) {
                        gameOver();
                        hideItems();
                    }
                    mHealthPointsTV.setText(String.valueOf(mHealth));
                }
            }
        });

        handler = new Handler();

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (view != null && imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view instanceof EditText) {

                view.clearFocus();
                hideKeyboard();
            }
        }

        return super.dispatchTouchEvent(ev);
    }


}
