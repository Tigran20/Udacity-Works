package com.example.android.miwok_my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private ArrayList<Word> phases;
    private ListView listView;
    private WordAdapter wordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        listView = findViewById(R.id.list);

        phases = new ArrayList<>();
        phases.add(new Word("Where are you going?", "minto wuksus"));
        phases.add(new Word("What is your name?", "tinnә oyaase'nә"));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));
        phases.add(new Word("My name is", "oyaaset..."));

        wordAdapter = new WordAdapter(this, phases, R.color.category_phrases);
        listView.setAdapter(wordAdapter);
    }

}
