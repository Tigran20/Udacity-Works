package com.tigran.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String mName;
    private String mChemistryGrade;
    private String mMathGrade;
    private String mHistoryGrade;

    private TextView mNameTextView;
    private TextView mGradeTextView;

    private EditText mUserName;
    private EditText mChemistryGradeEt;
    private EditText mMathGradeEt;
    private EditText mHistoryGradeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameTextView = (TextView) findViewById(R.id.text_view_name);
        mGradeTextView = (TextView) findViewById(R.id.text_view_grade);

        mUserName = (EditText) findViewById(R.id.name_user);
        mChemistryGradeEt = (EditText) findViewById(R.id.chemistry_grade);
        mMathGradeEt = (EditText) findViewById(R.id.math_grade);
        mHistoryGradeEt = (EditText) findViewById(R.id.history_grade);
    }

    public void setName() {
        mName = String.valueOf(mUserName.getText());
    }

    public void setChemistryGrade() {
        mChemistryGrade = String.valueOf(mChemistryGradeEt.getText());
    }

    public void setMathGrade() {
        mMathGrade = String.valueOf(mMathGradeEt.getText());
    }

    public void setHistoryGrade() {
        mHistoryGrade = String.valueOf(mHistoryGradeEt.getText());
    }

    public void displayGrades(View v) {
        setName();
        setChemistryGrade();
        setMathGrade();
        setHistoryGrade();
        mNameTextView.setText(nameItem());
        mGradeTextView.setText(valueItem());
    }


    public String nameItem() {
        return "Name: " + "\n" +
                "Chemistry : " + "\n" +
                "Math : " + "\n" +
                "History : ";
    }

    public String valueItem() {
        return mName + "\n" +
                mChemistryGrade + "\n" +
                mMathGrade + "\n" +
                mHistoryGrade + "\n";
    }

}
