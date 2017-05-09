package musicplayer.controller;
/**
 * Created by felic on 10/04/2017.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable{

    @FXML private MenuBar menuBar;
    @FXML private MenuItem createNewPlaylistMenu;
    @FXML private MenuItem searchMenu;
    @FXML private MenuItem logoutMenu;
    @FXML private MenuItem settingsMenu;
    @FXML private MenuItem aboutMenu;
    private LogInMenuController logInMenuController;
    private WelcomeMenuController welcomeMenuController;
    private SignUpMenuController signUpMenuController;
    private PaymentMenuController paymentMenuController;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void init(LogInMenuController logInMenuController) {
        this.logInMenuController = logInMenuController;
    }
    public void init(WelcomeMenuController welcomeMenuController) {
        this.welcomeMenuController = welcomeMenuController;
    }
    public void init(SignUpMenuController signUpMenuController) {
        this.signUpMenuController = signUpMenuController;
    }
    public void init(PaymentMenuController paymentMenuController) {
        this.paymentMenuController = paymentMenuController;
    }
    public void setDisabledMenuItemsWelcomeScene(){
       createNewPlaylistMenu.setDisable(false);
       searchMenu.setDisable(false);
       logoutMenu.setDisable(false);
       searchMenu.setDisable(false);
       settingsMenu.setDisable(false);
       aboutMenu.setDisable(false);
    }

    void menuBarFitToParent(AnchorPane parentAnchor){
        menuBar.prefWidthProperty().bind(parentAnchor.widthProperty());
    }
    @FXML
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
               SceneManager.sceneManager.changeSceneMenuBar(menuBar,"view/logInMenu.fxml");
            }catch (Exception e){
                DialogBoxManager.errorDialogBox("Error occurred","logging out");
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void exitMenuOption(){
        System.exit(0);
    }
    @FXML
    private void settingsMenuOption(ActionEvent event){
        try {
            SceneManager.sceneManager.changeScene(event,"view/settingsPage.fxml");
        } catch (IOException e) {
            DialogBoxManager.errorDialogBox("Error occured","Switching to settings page from menu bar selection");
            e.printStackTrace();
        }
    }
    @FXML
    private void aboutMenuOption(ActionEvent event){
        try {
            SceneManager.sceneManager.changeScene(event,"view/aboutPage.fxml");
        } catch (IOException e) {
            DialogBoxManager.errorDialogBox("Error occured","Switching to about page from menu bar selection");
            e.printStackTrace();
        }
    }
    @FXML
    private void faqsMenuOption(ActionEvent event){
        try {
            SceneManager.sceneManager.changeScene(event,"view/faqsPage.fxml");
        } catch (IOException e) {
            DialogBoxManager.errorDialogBox("Error occured","Switching to faqs page from menu bar selection");
            e.printStackTrace();
        }
    }
}