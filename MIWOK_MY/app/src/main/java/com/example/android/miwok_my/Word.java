package com.example.android.miwok_my;


public class Word {

    //Создали две переменные для хранения слов
    private String mDefaultTranslation;
    private String mMiwokTranslation;

    //Конструктор для иници данных
    public Word(String mDefaultTranslation, String mMiwokTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
    }

    //Создали геттеры, чтобы можно было получить данные private из другого класса
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }
}
