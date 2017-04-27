package musicplayer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpMenuController implements Initializable{

    @FXML private TextField userName;
    @FXML private TextField userPassword;
    @FXML private Label lblLogIn;
    @FXML private MainMenuController mainMenuController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        lblLogIn.setOnMouseEntered(event ->  {

                Scene scene = lblLogIn.getScene();
                scene.setCursor(Cursor.HAND);
        });

        lblLogIn.setOnMouseExited(event -> {

            Scene scene = lblLogIn.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
    }
    @FXML
    private void handleSignUpButton(ActionEvent event){
        try {
            //change scene location name to pay scene possibly
            SceneManager.sceneManager.changeScene(event,"view/welcomeMenu.fxml");
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to welcome scene");
            e.printStackTrace();
        }

    }
    @FXML
    private void clickOnLogInLabel(MouseEvent me) {
        try {

            SceneManager.sceneManager.changeScene(me,"view/logInMenu.fxml");
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to log in scene");
            e.printStackTrace();
        }
    }
}