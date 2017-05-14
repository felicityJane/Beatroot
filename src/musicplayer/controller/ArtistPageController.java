package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import musicplayer.DB_Connector;
import musicplayer.Server_Connector;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtistPageController implements Initializable {
    @FXML
    private MainMenuController mainMenuController;
    @FXML private AnchorPane artistPageAnchorPane;
    @FXML private ImageView imageView;
    @FXML private Label albumLabel;
    @FXML private ListView listView;
    private URL url;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private Server_Connector connector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.menuBarFitToParent(artistPageAnchorPane);
        mainMenuController.enableMenuItems();

        String imgUrl = db_connector.search("album_cover_path", "album", "album_id = " + Integer.toString(1));
        imageView.setImage(new Image(imgUrl));
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imageView.setEffect(dropShadow);

        int albumId = Integer.parseInt(db_connector.search("album_id", "album", "album_cover_path = " + "'" + imgUrl + "'"));
        String albumName = db_connector.search("album_name", "album", "album_id = " + Integer.toString(albumId));
        albumLabel.setText("Album Name: " + albumName);
    }

}
