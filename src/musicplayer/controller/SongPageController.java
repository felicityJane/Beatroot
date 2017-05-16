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
    @FXML private Label albumLabel,artistLabel,trackLabel,trackTimeLabel,publicationLabel;
    private URL url;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private Server_Connector connector;
    private Album album;
    private MusicArtist musicArtist;
    private MusicTrack musicTrack;
    GlobalVariables globalVariables = GlobalVariables.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalVariables.setSongPageController(this);
        getAlbumInfo();
    }
    public void getAlbumInfo(){
        album = globalVariables.getAlbum();
        musicTrack = globalVariables.getMusicTrack();
        musicArtist = globalVariables.getMusicArtist();

        imageView.setImage(album.getAlbumCover());
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imageView.setEffect(dropShadow);

        albumLabel.setText(String.format("Album : %-20s" , album.getAlbumName()));
        albumLabel.getStyleClass().add("albumText");
        artistLabel.setText(String.format("Artist : %-20s" , musicArtist.getStageName()));
        artistLabel.getStyleClass().add("artistText");
        trackLabel.setText(String.format("%-20s", musicTrack.getTrackName()));
        trackLabel.getStyleClass().add("artistText");
        trackTimeLabel.setText(String.format("Length : %-20s" , musicTrack.getTrackTime()));
        trackTimeLabel.getStyleClass().add("artistText");
        publicationLabel.setText(String.format("Publication Date : %-20s" , musicTrack.getPublicationYear()));
        publicationLabel.getStyleClass().add("artistText");
    }
}