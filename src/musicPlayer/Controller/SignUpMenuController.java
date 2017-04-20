package musicPlayer.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import musicPlayer.DialogBoxManager;
import musicPlayer.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpMenuController implements Initializable{

    @FXML private TextField userName;
    @FXML private TextField userPassword;

    @FXML private MainMenuController mainMenuController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
    }
    @FXML
    private void handleSignUpButton(ActionEvent event){
        try {
            //change scene location name to pay scene possibly
            SceneManager.sceneManager.changeScene(event,"View/welcomeMenu.fxml");
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to welcome scene");
            e.printStackTrace();
        }

    }
}