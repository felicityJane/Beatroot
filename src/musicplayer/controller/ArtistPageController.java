package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import musicplayer.DB_Connector;
import musicplayer.model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ArtistPageController implements Initializable {
    @FXML private AnchorPane artistPageAnchorPane;
    @FXML private ImageView imageView;
    @FXML private Label albumLabel, artistLabel, descriptionLabel, foundationLabel;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    GlobalVariables globalVariables = GlobalVariables.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalVariables.setArtistPageController(this);
        getAlbumInfo();
    }
    private void getAlbumInfo() {
        Album album = globalVariables.getAlbum();
        ArrayList<MusicTrack> musicTracks = globalVariables.getMusicTracks();
        MusicArtist musicArtist = globalVariables.getMusicArtist();

        imageView.setImage(album.getAlbumCover());
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imageView.setEffect(dropShadow);

        albumLabel.setText(String.format("Album : %-20s" , album.getAlbumName()));
        albumLabel.getStyleClass().add("albumText");
        artistLabel.setText(String.format("Artist : %-20s" , musicArtist.getStageName()));
        artistLabel.getStyleClass().add("artistText");
        String publicationDate = String.valueOf(musicArtist.getPublicationYear());
        foundationLabel.setText(String.format("Founded year : %-20s" , publicationDate.substring(0,4)));
        foundationLabel.getStyleClass().add("artistText");
        StringBuilder sb = new StringBuilder(musicArtist.getArtistDescription());

        int i = 0;
        while ((i = sb.indexOf(" ", i + 30)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        descriptionLabel.setText(String.format("%-20s",String.valueOf(sb)));
        descriptionLabel.getStyleClass().add("descriptionText");
    }
}
