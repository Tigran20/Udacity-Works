package com.example.android.miwok_my;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorBackId;
    private View textContainer;
    private int color;

    private Word currentWord;

    private TextView mMiwokTV;
    private TextView mDefaultTV;
    private ImageView imageView;

    //Конструктор
    public WordAdapter(Activity context, ArrayList<Word> words, int colorBackId) {
        super(context, 0, words);
        mColorBackId = colorBackId;
    }

    //Получаем Вьюху с позицией и стилем отображения
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Надуваем Вьюху данными через LayoutInflater, используя формат вывода в стиле list_item
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Получаем элемент типа Word по его позиции
        currentWord = getItem(position);

        //Создаем Вьюху и кладем в нее данные из mMiwokTranslation Word
        mMiwokTV = (TextView) convertView.findViewById(R.id.miwok_tv);
        mMiwokTV.setText(currentWord.getmMiwokTranslation());

        //Создаем Вьюху и кладем в нее данные из mDefaultTranslation Word
        mDefaultTV = (TextView) convertView.findViewById(R.id.default_tv);
        mDefaultTV.setText(currentWord.getmDefaultTranslation());

        //Создаем IV и кладем в нее данные из imageViewId Word;
        imageView = (ImageView) convertView.findViewById(R.id.image_view);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageViewId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        textContainer = convertView.findViewById(R.id.text_container);
        color = ContextCompat.getColor(getContext(), mColorBackId);
        textContainer.setBackgroundColor(color);

        //Возвращаем заполненную данными Вьюху
        return convertView;
    }


}
