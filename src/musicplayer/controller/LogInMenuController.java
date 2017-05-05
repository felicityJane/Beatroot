package musicplayer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import java.net.URL;
import java.util.ResourceBundle;
public class LogInMenuController implements Initializable{

    @FXML private TextField userName;
    @FXML private TextField userPassword;
    @FXML private Button loginButton,signUpButton;
    @FXML private MainMenuController mainMenuController;
    @FXML private AnchorPane logInParentAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.menuBarFitToParent(logInParentAnchorPane);
    }
    @FXML
    private void handleLoginButton(ActionEvent event) {
        try {
            SceneManager.sceneManager.changeScene(event,"view/welcomeMenu.fxml");
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from login scene to welcome scene");
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSignUpButton(ActionEvent event){
        try {
            SceneManager.sceneManager.changeScene(event,"view/signUpMenu.fxml");
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from login scene to sign up scene");
            e.printStackTrace();
        }

    }
}