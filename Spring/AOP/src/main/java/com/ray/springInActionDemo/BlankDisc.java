package com.ray.springInActionDemo;

import java.util.List;

/**
 * Created by Ray on 2017/6/4.
 */
public class BlankDisc {

    private String title;

    private String artist;

    private List<String> tracks;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    public void play(int num) {
        System.out.println("Playing " + title + " by " + artist);
        System.out.println("Playing Number : " + num);
        tracks.forEach(s -> System.out.println("-Track: " + s));
    }


}
