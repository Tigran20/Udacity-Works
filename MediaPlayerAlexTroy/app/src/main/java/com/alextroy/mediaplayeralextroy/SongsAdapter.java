package com.alextroy.mediaplayeralextroy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alextroy.mediaplayeralextroy.activities.SongCardActivity;
import com.alextroy.mediaplayeralextroy.model.Songs;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    private Activity activity;
    private List<Songs> items;

    public SongsAdapter(Activity activity, List<Songs> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.numberTV.setText(items.get(position).getSongNumber());
        viewHolder.songTitleTV.setText(items.get(position).getSongTitle());
        viewHolder.songAuthorTV.setText(items.get(position).getSongAuthor());
        viewHolder.songId = items.get(position).getAudioResourceId();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView numberTV;
        private TextView songTitleTV;
        private TextView songAuthorTV;
        private int songId;

        public ViewHolder(View view) {
            super(view);

            numberTV = view.findViewById(R.id.song_number);
            songTitleTV = view.findViewById(R.id.song_title);
            songAuthorTV = view.findViewById(R.id.song_author);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SongCardActivity.class);
                    intent.putExtra("title", songTitleTV.getText().toString());
                    intent.putExtra("author", songAuthorTV.getText().toString());
                    intent.putExtra("songId", songId);
                    activity.startActivity(intent);
                }
            });
        }
    }


}


