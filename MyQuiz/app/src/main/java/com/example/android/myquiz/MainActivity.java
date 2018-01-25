package com.example.android.myquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

        mUserNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (mUserNameInput.getText().toString().equals("")) {
                    mUserNameInput.setCursorVisible(true);
                } else {
                    mUserNameInput.setCursorVisible(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mUserNameInput.setCursorVisible(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
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
    }

    private boolean isUserNameNotCorrect(String userName) {
        return userName.isEmpty() || !userName.matches(USER_NAME_PATTERN);
    }

}
