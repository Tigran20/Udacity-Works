package com.alextroy.mediaplayeralextroy.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alextroy.mediaplayeralextroy.R;
import com.alextroy.mediaplayeralextroy.SongsAdapter;
import com.alextroy.mediaplayeralextroy.model.Songs;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    private RecyclerView listView;
    private SongsAdapter songsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ArrayList<Songs> songsSongs = new ArrayList<Songs>();

        songsSongs.add(new Songs("1", getString(R.string.it_is_my_life), getString(R.string.bon_jovi), R.raw.it_is_my_life));
        songsSongs.add(new Songs("2", getString(R.string.number_two), getString(R.string.andrea_delgado_olson), R.raw.number_two));
        songsSongs.add(new Songs("3", getString(R.string.number_three), getString(R.string.andrea_delgado_olson), R.raw.number_three));
        songsSongs.add(new Songs("4", getString(R.string.number_four), getString(R.string.andrea_delgado_olson), R.raw.number_four));
        songsSongs.add(new Songs("5", getString(R.string.number_five), getString(R.string.andrea_delgado_olson), R.raw.number_five));
        songsSongs.add(new Songs("6", getString(R.string.number_six), getString(R.string.andrea_delgado_olson), R.raw.number_six));
        songsSongs.add(new Songs("7", getString(R.string.number_ыумут), getString(R.string.andrea_delgado_olson), R.raw.number_seven));
        songsSongs.add(new Songs("8", getString(R.string.number_eight), getString(R.string.andrea_delgado_olson), R.raw.number_eight));
        songsSongs.add(new Songs("9", getString(R.string.number_nine), getString(R.string.andrea_delgado_olson), R.raw.number_nine));
        songsSongs.add(new Songs("10", getString(R.string.number_ten), getString(R.string.andrea_delgado_olson), R.raw.number_ten));

        listView = findViewById(R.id.list);
        listView.setHasFixedSize(true);

        LinearLayoutManager horizontalManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(horizontalManager);
        songsAdapter = new SongsAdapter(this, songsSongs);
        listView.setAdapter(songsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
