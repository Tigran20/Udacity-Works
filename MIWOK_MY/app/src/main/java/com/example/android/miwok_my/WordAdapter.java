package com.example.android.miwok_my;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private Word currentWord;

    private TextView mMiwokTV;
    private TextView mDefaultTV;

    //Конструктор
    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
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

        //Возвращаем заполненную данными Вьюху
        return convertView;
    }


}
