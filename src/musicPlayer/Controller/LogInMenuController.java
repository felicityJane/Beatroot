package musicPlayer.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import musicPlayer.DialogBoxManager;
import musicPlayer.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class LogInMenuController implements Initializable{

    @FXML private TextField userName;
    @FXML private TextField userPassword;
    @FXML private MenuItem createNewPlaylistMenu;
    @FXML private MenuItem exitMenu;
    @FXML private Menu fileMenu;
    @FXML
    private AnchorPane logInRootAnchor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load menu bar onto scene
        try {
            SceneManager.sceneManager.loadMenuBar(logInRootAnchor);
        } catch (IOException e) {
            DialogBoxManager.errorDialogBox("Error occurred","Applying main menu to scene");
            e.printStackTrace();
        }
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