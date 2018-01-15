package com.example.android.miwok_my;


public class Word {

    //Создали переменные для хранения слов, айдишник изобр и константа для отсутствия изображения воовсе
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int imageViewId = NO_IMAGE;
    private final static int NO_IMAGE = -1;

    //Конструктор для иници данных
    public Word(String mDefaultTranslation, String mMiwokTranslation, int image) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.imageViewId = image;
    }

    //Конструктор для иници данных без изображения
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

    public int getImageViewId() {
        return imageViewId;
    }

    //Есть ли изображение - то айди не дб равен -1
    public boolean hasImage() {
        return imageViewId != NO_IMAGE;
    }
}
