package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.GlobalVariables;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable{

    @FXML private MenuBar menuBar;
    @FXML private MenuItem createNewPlaylistMenu, searchMenu, logoutMenu, settingsMenu, aboutMenu,faqsMenu;
    @FXML AnchorPane mainMenu;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GlobalVariables.getInstance().setMainMenuController(this);
    }
    public void enableMenuItemsAboutPage(){
        createNewPlaylistMenu.setDisable(false);
        searchMenu.setDisable(false);
        logoutMenu.setDisable(false);
        settingsMenu.setDisable(false);
        aboutMenu.setDisable(true);
    }
    public void enableMenuItems(){
        createNewPlaylistMenu.setDisable(false);
        searchMenu.setDisable(false);
        logoutMenu.setDisable(false);
        settingsMenu.setDisable(false);
    }
    public void enableMenuItemsFaqsPage(){
        createNewPlaylistMenu.setDisable(false);
        searchMenu.setDisable(false);
        logoutMenu.setDisable(false);
        settingsMenu.setDisable(false);
        faqsMenu.setDisable(true);
    }
    public void enableMenuItemsSettingsPage(){
        createNewPlaylistMenu.setDisable(false);
        searchMenu.setDisable(false);
        logoutMenu.setDisable(false);
    }
    //TODO <fx:include fx:id="adminMenu" source="AdminMenu.fxml"/>
    void menuBarFitToParent(AnchorPane parentAnchor){
        menuBar.prefWidthProperty().bind(parentAnchor.widthProperty());
    }
    @FXML
    private void createNewPlaylistMenuOption(){
        //refer to createNewPlaylist method here
    }
    @FXML
    private void searchMenuOption(){
        //refer to search method here
    }
    @FXML
    private void logOutMenuOption(){
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
    private void settingsMenuOption(){
        try {
            SceneManager.sceneManager.changeSceneMenuBar(menuBar,"view/settingsPage.fxml");
        } catch (IOException e) {
            DialogBoxManager.errorDialogBox("Error occured","Switching to settings page from menu bar selection");
            e.printStackTrace();
        }
    }
    @FXML
    private void aboutMenuOption(){
        try {
            SceneManager.sceneManager.changeSceneMenuBar(menuBar,"view/aboutPage.fxml");
        } catch (IOException e) {
            DialogBoxManager.errorDialogBox("Error occured","Switching to about page from menu bar selection");
            e.printStackTrace();
        }
    }
    @FXML
    private void faqsMenuOption(){
        try {
            SceneManager.sceneManager.changeSceneMenuBar(menuBar,"view/faqsPage.fxml");
        } catch (IOException e) {
            DialogBoxManager.errorDialogBox("Error occured","Switching to faqs page from menu bar selection");
            e.printStackTrace();
        }
    }
}