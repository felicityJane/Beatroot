package musicplayer.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import musicplayer.SceneManager;
import musicplayer.Server_Connector;
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
        globalVariables.setAlbumPageController(this);
        getAlbumInfo();
        popUpMenuAlbumPage(imageView);
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
    private void popUpMenuAlbumPage(ImageView imageView){
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem songPage = new MenuItem("See song info");
        final MenuItem artistPage = new MenuItem("See artist info");
        contextMenu.getItems().addAll(songPage,artistPage);
        SceneManager sceneManager = new SceneManager();
        for (Node n : albumPageAnchorPane.getChildren()) {

            if (n instanceof ImageView ) {
                n.setOnContextMenuRequested(event -> contextMenu.show(n, event.getScreenX(), event.getScreenY()));
            }
        }
        songPage.setOnAction(event -> {
            try {
                Stage stage = (Stage) albumPageAnchorPane.getScene().getWindow();
                stage.close();
                sceneManager.popUpWindow(event, "view/songPage.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        artistPage.setOnAction(event -> {
            try {
                Stage stage = (Stage) albumPageAnchorPane.getScene().getWindow();
                stage.close();
                sceneManager.popUpWindow(event, "view/artistPage.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}