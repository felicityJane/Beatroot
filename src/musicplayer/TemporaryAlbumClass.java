package musicplayer;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class TemporaryAlbumClass {

    private ArrayList<String> tracks = new ArrayList<>();
    private Image albumCover;

    public ArrayList<String> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<String> tracks) {
        this.tracks = tracks;
    }

    public Image getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(Image albumCover) {
        this.albumCover = albumCover;
    }
}
