package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Created by felic on 9/05/2017.
 */
public class SettingsPageController {

    // have all app settings and personal settings in this scene
    @FXML private AnchorPane settingsAnchorPage;
    @FXML private Button backButton;
    @FXML private TextField editUserNameField;
    @FXML private Label editUserNameLabel;
    @FXML private TextField editPasswordField;
    @FXML private Label editPasswordLabel;
    @FXML private Button backgroundBlackButton;
    @FXML private Button backgroundWhiteButton;
    @FXML private Button saveChangesButton;


}
