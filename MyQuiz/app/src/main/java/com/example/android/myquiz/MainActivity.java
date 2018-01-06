package com.example.android.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    public void startQuizGame(View v) {
        String userName = mUserNameInput.getText().toString();

        if (isUserNameNotCorrect(userName)) {
            Toast.makeText(this, R.string.input_name_error_msg, Toast.LENGTH_LONG).show();
            return;
        }

        Intent startQuizIntent = new Intent(this, Questions.class);
        startQuizIntent.putExtra(Questions.USER_NAME_EXTRA, userName);
        startActivity(startQuizIntent);
    }

    private boolean isUserNameNotCorrect(String userName) {
        return userName.isEmpty() || !userName.matches(USER_NAME_PATTERN);
    }

}
