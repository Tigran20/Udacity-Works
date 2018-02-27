package com.alextroy.mediaplayeralextroy;

public class MyMediaPlayer {

    private String songNumber;
    private String songTitle;
    private String songAuthor;
    private int audioResourceId;

    public MyMediaPlayer(String songNumber, String songTitle, String songAuthor,  int audioResourceId) {
        this.songNumber = songNumber;
        this.songTitle = songTitle;
        this.songAuthor = songAuthor;
        this.audioResourceId = audioResourceId;
    }

    public String getSongNumber() {
        return songNumber;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }

}
