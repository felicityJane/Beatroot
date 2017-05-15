package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import musicplayer.DB_Connector;
import musicplayer.Server_Connector;
import musicplayer.model.Album;
import musicplayer.model.GlobalVariables;
import musicplayer.model.MusicArtist;
import musicplayer.model.MusicTrack;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SongPageController implements Initializable {
    @FXML private AnchorPane songPageAnchorPane;
    @FXML private ImageView imageView;
    @FXML private Label albumLabel, artistLabel;
    @FXML private ListView<String> listView;
    private URL url;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private Server_Connector connector;
    private Album album;
    private MusicArtist musicArtist;
    private ArrayList<MusicTrack> musicTracks;
    GlobalVariables globalVariables = GlobalVariables.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalVariables.setSongPageController(this);
        getAlbumInfo();
    }
    public void getAlbumInfo(){
        int counter = 0;
        album = globalVariables.getAlbum();
        musicTracks = globalVariables.getMusicTracks();
        imageView.setImage(album.getAlbumCover());
        albumLabel.setText("Album: " + album.getAlbumName());
        listView.getItems().clear();
        for (MusicTrack m: musicTracks) {
            counter++;
            String string = String.format("Track" + counter + " : " + "%-20s", m.getTrackName());
            int trackId = m.getID();
            musicArtist = db_connector.getArtistDetails(trackId);
            globalVariables.setMusicArtist(musicArtist);
            listView.getItems().add(string);
            artistLabel.setText("Artist: " + musicArtist.getStageName());
        }
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imageView.setEffect(dropShadow);
    }
}