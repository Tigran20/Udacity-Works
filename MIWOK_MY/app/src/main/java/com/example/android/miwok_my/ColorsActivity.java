package com.example.android.miwok_my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Word> colors;
    private ListView listView;
    private WordAdapter wordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        listView = findViewById(R.id.list);

        colors = new ArrayList<>();
        colors.add(new Word("red", "weṭeṭṭi", R.drawable.color_red));
        colors.add(new Word("green", "chokokki", R.drawable.color_green));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));


        wordAdapter = new WordAdapter(this, colors, R.color.category_colors);
        listView.setAdapter(wordAdapter);

    }

}
