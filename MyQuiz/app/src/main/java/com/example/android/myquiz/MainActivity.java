package com.example.android.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String USER_NAME_PATTERN = "[\\w.-]{1,20}";
    private EditText mUserNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserNameInput = findViewById(R.id.ed_user_name);

        Intent intent = getIntent();
        String text = intent.getStringExtra(Questions.USER_NAME_EXTRA);
        mUserNameInput.setText(text);
    }

    public void startQuizGame(View v) {
        String userName = mUserNameInput.getText().toString();

        if (isUserNameNotCorrect(userName)) {
            Toast.makeText(this, R.string.input_name_error_msg, Toast.LENGTH_LONG).show();
            return;
        }

        Intent startQuizIntent = new Intent(this, Questions.class);
        startQuizIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startQuizIntent.putExtra(Questions.USER_NAME_EXTRA, userName);
        startActivity(startQuizIntent);
        hideKeyboard();
    }

    private boolean isUserNameNotCorrect(String userName) {
        return userName.isEmpty() || !userName.matches(USER_NAME_PATTERN);
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

        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if(view instanceof EditText) {

                view.clearFocus();
                hideKeyboard();
            }
        }

        return super.dispatchTouchEvent(ev);
    }
}
