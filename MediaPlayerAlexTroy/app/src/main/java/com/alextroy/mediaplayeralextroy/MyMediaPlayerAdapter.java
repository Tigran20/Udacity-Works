package com.alextroy.mediaplayeralextroy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyMediaPlayerAdapter extends RecyclerView.Adapter<MyMediaPlayerAdapter.ViewHolder> {

    private Activity activity;
    private List<MyMediaPlayer> items;

    public MyMediaPlayerAdapter(Activity activity, List<MyMediaPlayer> items) {
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
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView numberTV;
        private TextView songTitleTV;
        private TextView songAuthorTV;

        public ViewHolder(View view) {
            super(view);
            numberTV = view.findViewById(R.id.song_number);
            songTitleTV = view.findViewById(R.id.song_title);
            songAuthorTV = view.findViewById(R.id.song_author);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SongCard.class);
                    activity.startActivity(intent);
                }
            });
        }
    }


}


