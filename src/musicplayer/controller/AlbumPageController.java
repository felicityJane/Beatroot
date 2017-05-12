package musicplayer.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import musicplayer.DB_Connector;
import musicplayer.SceneManager;
import musicplayer.Server_Connector;
import musicplayer.model.Album;
import musicplayer.model.GlobalVariables;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AlbumPageController implements Initializable {
    @FXML private MainMenuController mainMenuController;
    @FXML private AnchorPane albumPageAnchorPane;
    @FXML private ImageView imageView;
    @FXML private Label albumLabel;
    @FXML private ListView listView;
    //private WelcomeMenuController welcomeMenuController;
    private URL url;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private Server_Connector connector;
    private String imgUrl;
    private Album album;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.menuBarFitToParent(albumPageAnchorPane);
        mainMenuController.enableMenuItems();

        getAlbumInfo();

//        int albumId = Integer.parseInt(db_connector.search("album_id", "album", "album_cover_path = " + "'" + imgUrl + "'"));
//        String albumName = db_connector.search("album_name", "album", "album_id = " + Integer.toString(albumId));
//        albumLabel.setText("Album Name: " + albumName);

    }

    public void getAlbumInfo(){
        imageView.setImage(new Image(GlobalVariables.getInstance().getAlbumCover()));
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imageView.setEffect(dropShadow);
    }
    public void setAlbum(Album album){
        this.album = album;
    }

//    public void init(WelcomeMenuController welcomeMenuController) {
//        this.welcomeMenuController = welcomeMenuController;
//    }
}