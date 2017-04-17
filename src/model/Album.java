package model;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Created by fatih on 2017-04-13.
 */
public class Album {
    private String albumName;
    private Image coverImage;
    private ArrayList<MusicTrack> musicTrack;

    public Album(String albumName, Image coverImage, ArrayList<MusicTrack> musicTrack) {
        this.albumName = albumName;
        this.coverImage = coverImage;
        this.musicTrack = musicTrack;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }

    public ArrayList<MusicTrack> getMusicTrack() {
        return musicTrack;
    }

    public void setMusicTrack(ArrayList<MusicTrack> musicTrack) {
        this.musicTrack = musicTrack;
    }
}

