package com.example.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_INDEX = "index";
    private static final String EXTRA_ANSWER_IS_TRUE = "answerIsTrue";

    private TextView mQuestionTextView;

    private View mPrevButton;
    private View mNextButton;

    private Button mCheat;

    private int mCurrentIndex = 0;

    private boolean answerIsTrue;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        init();
        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev_button:
                mCurrentIndex = (mCurrentIndex + mQuestionBank.length - 1) % mQuestionBank.length;
                updateQuestion();
                break;
            case R.id.next_button:
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                break;
            case R.id.cheat_button:
                answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent cheat = new Intent(QuizActivity.this, CheatActivity.class);
                cheat.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivity(cheat);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void init() {
        mQuestionTextView = findViewById(R.id.question_text_view);

        mPrevButton = findViewById(R.id.prev_button);
        mNextButton = findViewById(R.id.next_button);

        mCheat = findViewById(R.id.cheat_button);

        mPrevButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);

        mCheat.setOnClickListener(this);
    }
}
