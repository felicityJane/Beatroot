package musicPlayer.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import musicPlayer.DialogBoxManager;
import musicPlayer.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;
public class LogInMenuController implements Initializable{

    @FXML private TextField userName;
    @FXML private TextField userPassword;
    @FXML private Button loginButton,signUpButton;
    @FXML private MainMenuController mainMenuController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
    }
    @FXML
    private void handleLoginButton(ActionEvent event) {
        try {
            SceneManager.sceneManager.changeScene(event,"View/welcomeMenu.fxml");
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from login scene to welcome scene");
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSignUpButton(ActionEvent event){
        try {
            SceneManager.sceneManager.changeScene(event,"View/signUpMenu.fxml");
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from login scene to sign up scene");
            e.printStackTrace();
        }

    }
}