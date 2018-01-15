package com.example.android.miwok_my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    //Созаем массив для слов, ЛистВью и адаптер для соединения Массива и ЛистВью
    private ArrayList<Word> words;
    private ListView listView;
    private WordAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        listView = findViewById(R.id.list);

        //Инициализируем массив и заполняем его данными конструктором из Word
        words = new ArrayList<>();

        words.add(new Word("one", "lutti"));
        words.add(new Word("two", "lutti_2"));

        //Иницализируем кастомный адаптер и кладем в него данные из массива words
        itemsAdapter = new WordAdapter(this, words);

        //Устанавливаем адаптер в ЛистВью
        listView.setAdapter(itemsAdapter);
    }
}
