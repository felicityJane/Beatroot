package musicPlayer.Controller;
/**
 * Created by felic on 10/04/2017.
 */
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import musicPlayer.DialogBoxManager;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable{

    @FXML private MenuBar menuBar;
    @FXML private MenuItem createNewPlaylistMenu;
    @FXML private MenuItem exitMenu;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        exitMenu.setDisable(true);
    }
    private void createNewPlaylistMenuOption(){
        //refer to create playlist method here
    }
    @FXML
    private void searchMenuOption(){
        //refer to search method here
    }
    @FXML
    private void logOutMenuOption(ActionEvent event){
        boolean answer = DialogBoxManager.confirmationDialogBox("Are you sure you want to log out?","click ok to continue");
        if (answer){
            try {
                Stage stage = (Stage) menuBar.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../View/logInMenu.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Beatroot");
                stage.setScene(scene);
            }catch (Exception e){
                DialogBoxManager.errorDialogBox("Error occurred","logging out");
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void exitMenuOption(){
        Platform.exit();
    }
    @FXML
    private void personalInfoMenuOption(){
        //some kind of options to change personal info here
    }
    @FXML
    private void settingsMenuOption(){
        //some kind of settings menu here
    }
    @FXML
    private void aboutMenuOption(){
        //some information about Beatroot here
    }
    @FXML
    private void faqsMenuOption(){
        // Link to FAQ's here
    }
}