package musicplayer.model;

/**
 * Created by felic on 11/05/2017.
 */
public class GlobalVariables {

    private final static GlobalVariables instance = new GlobalVariables();

    public static GlobalVariables getInstance(){
        return instance;
    }
    private Album album;
    private String albumCover;

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }
}
