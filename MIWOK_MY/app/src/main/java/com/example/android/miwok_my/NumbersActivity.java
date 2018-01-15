package com.example.android.miwok_my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    //Созаем массив для слов, ЛистВью и адаптер для соединения Массива и ЛистВью
    private ArrayList<Word> words;
    private ListView listView;
    private WordAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        listView = findViewById(R.id.list);

        //Инициализируем массив и заполняем его данными конструктором из Word
        words = new ArrayList<>();
        words.add(new Word("one", "lutti", R.drawable.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));

        //Иницализируем кастомный адаптер и кладем в него данные из массива words
        itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        //Устанавливаем адаптер в ЛистВью
        listView.setAdapter(itemsAdapter);
    }
}
