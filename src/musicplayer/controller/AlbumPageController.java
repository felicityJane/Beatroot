package musicplayer.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.Album;
import musicplayer.model.GlobalVariables;
import musicplayer.model.MusicArtist;
import musicplayer.model.MusicTrack;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AlbumPageController implements Initializable {
    @FXML private AnchorPane albumPageAnchorPane;
    @FXML private ImageView imageView;
    @FXML private Label albumLabel, artistLabel, releaseDateLabel;
    @FXML private ListView<String> listView;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    GlobalVariables globalVariables = GlobalVariables.getInstance();
    private int counter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalVariables.setAlbumPageController(this);
        listView.getItems().clear();
        getAlbumInfo();
        popUpMenuSongPage();
        popUpMenuArtistPage();
    }

    private void getAlbumInfo(){
        Album album = globalVariables.getAlbum();
        ArrayList<MusicTrack> musicTracks = globalVariables.getMusicTracks();

        imageView.setImage(album.getAlbumCover());
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imageView.setEffect(dropShadow);

        albumLabel.setText("Album: " + album.getAlbumName());
        albumLabel.getStyleClass().add("albumText");

        for (MusicTrack m: musicTracks) {
            counter++;
            globalVariables.setMusicTrack(m);
            db_connector.getArtistDetails(m.getID());
            MusicArtist musicArtist = globalVariables.getMusicArtist();
            artistLabel.setText("Artist: " + musicArtist.getStageName());
            artistLabel.getStyleClass().add("artistText");
            listView.getItems().add((m.getTrackName()));
            String publicationDate = String.valueOf(musicArtist.getPublicationYear());
            releaseDateLabel.setText(String.format("Release Date : %-20s" , publicationDate.substring(0,4)));
            releaseDateLabel.getStyleClass().add("artistText");
        }
    }
    private void popUpMenuSongPage(){
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem songPage = new MenuItem("See song info");
        contextMenu.getItems().addAll(songPage);
        for (Node n : albumPageAnchorPane.getChildren()) {
            if (n instanceof ListView ) {
                n.setOnContextMenuRequested(event -> contextMenu.show(n, event.getScreenX(), event.getScreenY()));
            }
        }
        songPage.setOnAction(event -> {
            try {
                String trackName = listView.getSelectionModel().getSelectedItem();
                Integer trackId = db_connector.getMusicTrackInfo(trackName);
                globalVariables.getMusicTrack().setID(trackId);
                Stage stage = (Stage) albumPageAnchorPane.getScene().getWindow();
                stage.close();
                SceneManager.getInstance().popUpWindow( "view/songPage.fxml");
            } catch (IOException e) {
                DialogBoxManager.errorDialogBox("error occurred","an error has occurred changing to song page scene, please try again");
                e.printStackTrace();
            }
        });
    }
    private void popUpMenuArtistPage(){
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem artistPage = new MenuItem("See artist info");
        contextMenu.getItems().addAll(artistPage);
        for (Node n : albumPageAnchorPane.getChildren()) {
            if (n instanceof ImageView ) {
                n.setOnContextMenuRequested(event -> contextMenu.show(n, event.getScreenX(), event.getScreenY()));
            }
        }
        artistPage.setOnAction(event -> {
            try {
                Stage stage = (Stage) albumPageAnchorPane.getScene().getWindow();
                stage.close();
                SceneManager.getInstance().popUpWindow( "view/artistPage.fxml");
            } catch (IOException e) {
                DialogBoxManager.errorDialogBox("error occurred","an error has occurred changing to artist page scene, please try again");
                e.printStackTrace();
            }
        });

    }
}