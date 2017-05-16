package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.model.Playlist;
import musicplayer.model.PrivacyLevel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Federica on 15/05/2017.
 */
public class PlaylistChoiceWindowController implements Initializable {

    @FXML private ListView lstPlaylists;
    @FXML private Button btnChoosePlaylist;
    private String userName;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private ArrayList<Playlist> userPlaylists = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readUserFromBinaryFile();
        setPlaylists();
    }

    private void readUserFromBinaryFile() {

        try {
            Path path = Paths.get("UserName.bin");
            java.util.List<String> userInfo = Files.readAllLines(path);
            userName = userInfo.get(0);
        } catch (IOException ie) {
            DialogBoxManager.errorDialogBox("Cannot read user info", "Cannot access user info from UserName.bin");
        }
    }

    private void setPlaylists() {
        int counter = 0;
        for (String s : db_connector.searchMultipleResults("playlist_id", "playlist", "owner = '" + userName
                + "'")) {
            userPlaylists.add(new Playlist(db_connector.search("name", "playlist", "owner = '" + userName + "' AND playlist_id = " + s), PrivacyLevel.values()[Integer.parseInt(db_connector.search("privacy_level_privacy_id", "playlist",
                    "owner = '" + userName + "' AND playlist_id = " + s))]));
            userPlaylists.get(counter).setId(Integer.parseInt(s));
            counter++;
        }

        for (Playlist p : userPlaylists) {
            p.setNumberOfEntries(Integer.parseInt(db_connector.search("number_of_entries", "playlist",
                    "playlist_id = " + Integer.toString(p.getId()))));
        }
        fillUpLstPlaylists();
    }

    private void fillUpLstPlaylists() {

        for (Playlist p : userPlaylists) {
            lstPlaylists.getItems().add(p.getName());
        }
    }

    @FXML
    private  void onBtnChoosePlaylistPressed() {
        if (lstPlaylists.getSelectionModel().getSelectedItem() != null) {
            writePlaylistIdToBinaryFile(userPlaylists.get(lstPlaylists.getSelectionModel().getSelectedIndex()));
            Stage stage = (Stage) btnChoosePlaylist.getScene().getWindow();
            stage.setOnHidden(event -> {
                stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            });
           stage.close();
        } else {
            DialogBoxManager.errorDialogBox("Select an item", "Please select an item from the list!");
        }
    }

    private  void writePlaylistIdToBinaryFile(Playlist p) {
        try (FileOutputStream fs = new FileOutputStream("PlaylistId.bin"); ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(p);
        } catch (FileNotFoundException fe) {
            DialogBoxManager.errorDialogBox("File not found", "File not found. Try again.");
        } catch (IOException ie) {
            DialogBoxManager.errorDialogBox("Cannot create file", "Error with creating file.");
        }
    }
}
