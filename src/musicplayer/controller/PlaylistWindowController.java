package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.model.GlobalVariables;
import musicplayer.model.Playlist;
import musicplayer.model.PremiumUser;
import musicplayer.model.PrivacyLevel;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Federica on 15/05/2017.
 */
public class PlaylistWindowController implements Initializable {

    @FXML private TextField txtPlaylistName;
    @FXML private RadioButton rdPrivate;
    @FXML private RadioButton rdContact;
    @FXML private RadioButton rdPublic;
    @FXML private Button btnCreatePlaylist;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private String owner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readFromBinaryFile();
    }

    @FXML
    private void onBtnCreatePlaylistPressed() {

        if (!txtPlaylistName.getText().equals("")) {
            if (db_connector.search("playlist_link", "premium_user", "user_name = '" + owner + "'").equals(db_connector.search("user",
                    "user_link", "user = '" + owner + "'"))) {
                if (rdPrivate.isSelected()) {
                    Playlist playlist = new Playlist(txtPlaylistName.getText(), PrivacyLevel.PRIVATE);
                    db_connector.insert("playlist(name, number_of_entries, total_duration, privacy_level_privacy_id, owner)",
                            "('" + txtPlaylistName.getText() + "', 0, '00:00:00', 0, '" + owner + "')");
                } else if (rdContact.isSelected()) {
                    Playlist playlist = new Playlist(txtPlaylistName.getText(), PrivacyLevel.CONTACT);
                    db_connector.insert("playlist(name, number_of_entries, total_duration, privacy_level_privacy_id, owner)",
                            "('" + txtPlaylistName.getText() + "', 0, '00:00:00', 2, '" + owner + "')");
                } else if (rdPublic.isSelected()) {
                    Playlist playlist = new Playlist(txtPlaylistName.getText(), PrivacyLevel.PUBLIC);
                    db_connector.insert("playlist(name, number_of_entries, total_duration, privacy_level_privacy_id, owner)",
                            "('" + txtPlaylistName.getText() + "', 0, '00:00:00', 1, '" + owner + "')");
                }

                DialogBoxManager.confirmationDialogBox("Success!", "Playlist successfully created.");
                Stage stage = (Stage) btnCreatePlaylist.getScene().getWindow();
                stage.setOnHidden(event -> {
                    stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
                });
                stage.close();
            } else {
                DialogBoxManager.errorDialogBox("Error", "The user does not have a corresponding user link. Please create one.");
            }
        } else {
            DialogBoxManager.errorDialogBox("Empty name", "Please enter a name to create a playlist.");
        }


    }

    private void readFromBinaryFile() {
        try {
            Path path = Paths.get("UserName.bin");
            List<String> userInfo = Files.readAllLines(path);
            owner = userInfo.get(0);
        } catch (IOException ie) {
            DialogBoxManager.errorDialogBox("Cannot read user info", "Cannot access user info from UserName.bin");
        }
    }
}
