package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Federica on 14/05/2017.
 */
public class CommentWindowController implements Initializable {

    @FXML private Label lblTitle;
    @FXML private TextArea txtAreaComment;
    @FXML private Button btnSend;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onBtnSendPressed() {

    }
}
