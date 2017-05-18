package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.model.MusicTrack;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Federica on 14/05/2017.
 */
public class CommentWindowController implements Initializable {

    @FXML private Label lblTitle;
    @FXML private TextArea txtAreaComment;
    @FXML private Button btnSend;
    private MusicTrack mt;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readMusicTrackFromBinaryFile();
        lblTitle.setText("Write a comment for " + mt.getTrackName());
    }

    @FXML
    private void onBtnSendPressed() {

        if (!txtAreaComment.getText().equals("")) {
            db_connector.insert("comment(message, music_track_track_id)", "('" + txtAreaComment.getText()
                    + "', " + Integer.toString(mt.getID()) + ")");
            DialogBoxManager.confirmationDialogBox("Thank you!", "Your feedback has been registered.");
            Stage stage = (Stage) btnSend.getScene().getWindow();
            stage.close();
        } else {
            DialogBoxManager.errorDialogBox("Empty comment", "Please enter text to send a comment for this track.");
        }

    }

    private void readMusicTrackFromBinaryFile() {

        try (FileInputStream fs = new FileInputStream("MusicTrack.bin"); ObjectInputStream os = new ObjectInputStream(fs)) {

            mt = (MusicTrack)os.readObject();

        } catch (FileNotFoundException fe) {
            DialogBoxManager.errorDialogBox("File not found", "File not found. Try again.");
        } catch (IOException ie) {
            DialogBoxManager.errorDialogBox("Cannot create file", "Error with creating file.");
        } catch (ClassNotFoundException ce) {
            DialogBoxManager.errorDialogBox("Cannot find class", "Error with reading file.");
        }

    }
}

