package com.example.android.miwok_my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private ArrayList<Word> family;
    private ListView listView;
    private WordAdapter wordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        listView = findViewById(R.id.list);

        family = new ArrayList<>();
        family.add(new Word("father", "әpә", R.drawable.family_father));
        family.add(new Word("mother", "әṭa", R.drawable.family_mother));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));
        family.add(new Word("son", "angsi", R.drawable.family_son));

        wordAdapter = new WordAdapter(this, family, R.color.category_family);
        listView.setAdapter(wordAdapter);

    }

}
