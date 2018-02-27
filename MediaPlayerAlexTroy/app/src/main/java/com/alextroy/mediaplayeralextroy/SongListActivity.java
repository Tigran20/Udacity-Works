package com.alextroy.mediaplayeralextroy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    private RecyclerView listView;
    private MyMediaPlayerAdapter myMediaPlayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ArrayList<MyMediaPlayer> myMediaPlayerSongs = new ArrayList<MyMediaPlayer>();

        myMediaPlayerSongs.add(new MyMediaPlayer("1", "It's my life", "Bon Jovi", R.raw.my_life_bon_jovi));
        myMediaPlayerSongs.add(new MyMediaPlayer("2", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));
        myMediaPlayerSongs.add(new MyMediaPlayer("3", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));
        myMediaPlayerSongs.add(new MyMediaPlayer("4", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));
        myMediaPlayerSongs.add(new MyMediaPlayer("5", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));
        myMediaPlayerSongs.add(new MyMediaPlayer("6", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));
        myMediaPlayerSongs.add(new MyMediaPlayer("7", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));
        myMediaPlayerSongs.add(new MyMediaPlayer("8", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));
        myMediaPlayerSongs.add(new MyMediaPlayer("9", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));
        myMediaPlayerSongs.add(new MyMediaPlayer("10", "My heart will go on", "Celine Dion", R.raw.my_heart_will_go_on_celine_dion));

        listView = findViewById(R.id.list);
        listView.setHasFixedSize(true);

        LinearLayoutManager horizontalManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(horizontalManager);
        myMediaPlayerAdapter = new MyMediaPlayerAdapter(this, myMediaPlayerSongs);
        listView.setAdapter(myMediaPlayerAdapter);
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
