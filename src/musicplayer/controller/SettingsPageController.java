package musicplayer.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.GlobalVariables;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingsPageController implements Initializable {

    @FXML private AnchorPane settingsAnchorPage;
    @FXML private TextField editDisplayNameField, editPasswordField;
    @FXML private Label editDisplayNameLabel, editPasswordLabel;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    GlobalVariables globalVariables = GlobalVariables.getInstance();
    private String userName, newPassWord, newDisplayName;
    int user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalVariables.getMainMenuController().menuBarFitToParent(settingsAnchorPage);
        globalVariables.getMainMenuController().disableMenuItems();

        if(globalVariables.getTrialuser()!= null && globalVariables.getPremiumUser() == null && globalVariables.getAdministrator() == null) {
            editDisplayNameLabel.setText("Current Display :  " + globalVariables.getTrialuser().getDisplayName());
            editPasswordLabel.setText("Current Password :  " + globalVariables.getTrialuser().getPassword());
            editDisplayNameField.setText(globalVariables.getTrialuser().getDisplayName());
            editPasswordField.setText(globalVariables.getTrialuser().getPassword());
            userName = globalVariables.getTrialuser().getUserName();
            user = 1;
            System.out.println("type trial ");
        } else if (globalVariables.getPremiumUser() != null && globalVariables.getTrialuser() == null && globalVariables.getAdministrator() == null) {
            editDisplayNameLabel.setText("Current Display : " + globalVariables.getPremiumUser().getDisplayName());
            editPasswordLabel.setText("Current Password : " + globalVariables.getPremiumUser().getPassword());
            editDisplayNameField.setText(globalVariables.getPremiumUser().getDisplayName());
            editPasswordField.setText(globalVariables.getPremiumUser().getPassword());
            userName = globalVariables.getPremiumUser().getUserName();
            user = 2;
        } else if (globalVariables.getAdministrator() != null && globalVariables.getPremiumUser() == null && globalVariables.getTrialuser() == null) {
            editDisplayNameLabel.setText("Current Display :  " + globalVariables.getAdministrator().getDisplayName());
            editPasswordLabel.setText("Current Password :  " + globalVariables.getAdministrator().getPassword());
            editDisplayNameField.setText(globalVariables.getAdministrator().getDisplayName());
            editPasswordField.setText(globalVariables.getAdministrator().getPassword());
            userName = globalVariables.getTrialuser().getUserName();
            user = 3;
        }
    }
    @FXML
    private void onDarkTheme(){
        SceneManager.getInstance().setBrightMode(false);
        settingsAnchorPage.getScene().getStylesheets().remove("musicplayer/css/brightAndBubbly.css");
        SceneManager.getInstance().applyCurrentCss(settingsAnchorPage.getScene());
        AnchorPane anchorPane = GlobalVariables.getInstance().getWelcomeMenuController().getParent();
        anchorPane.getScene().getStylesheets().remove("musicplayer/css/brightAndBubbly.css");
        SceneManager.getInstance().applyCurrentCss(anchorPane.getScene());
    }
    @FXML
    private void onLightTheme(){
        SceneManager.getInstance().setBrightMode(true);
        settingsAnchorPage.getScene().getStylesheets().remove("musicplayer/css/darkAndGloomy.css");
        SceneManager.getInstance().applyCurrentCss(settingsAnchorPage.getScene());
        AnchorPane anchorPane = GlobalVariables.getInstance().getWelcomeMenuController().getParent();
        anchorPane.getScene().getStylesheets().remove("musicplayer/css/darkAndGloomy.css");
        SceneManager.getInstance().applyCurrentCss(anchorPane.getScene());
    }
    @FXML
    private void onSaveButton() {

        if (editDisplayNameField.getText().length() > 5 && editDisplayNameField.getText().length() < 12 && editPasswordField.getText().length() > 5 && editPasswordField.getText().length() < 12) {
            newDisplayName = editDisplayNameField.getText();
            newPassWord = editPasswordField.getText();

            String message = "Please confirm changes\nDisplay name : " + newDisplayName + "\nNew password : " + newPassWord;
            String confirmationMessage = "\nDisplay name : " + newDisplayName + "\nNew password : " + newPassWord;

            Boolean confirm = DialogBoxManager.confirmationDialogBox(message, "Click ok to continue");

            if (confirm) {
                if (user == 1) {
                    try {
                        db_connector.changeDisplayNamePassword(" trial_user ", newDisplayName, newPassWord, userName);
                        System.out.println("update trial");
                        DialogBoxManager.notificationDialogBox("Changes Confirmed", confirmationMessage);
                        globalVariables.getTrialuser().setDisplayName(newDisplayName);
                        globalVariables.getTrialuser().setPassword(newPassWord);
                    } catch (SQLException e) {
                        DialogBoxManager.errorDialogBox("An error has occurred", "check trial username and password and try again");
                        e.printStackTrace();
                    }
                } else if (user == 2) {
                    try {
                        db_connector.changeDisplayNamePassword("premium_user", newDisplayName, newPassWord, userName);
                        System.out.println("update premium");
                        DialogBoxManager.notificationDialogBox("Changes Confirmed", confirmationMessage);
                        globalVariables.getPremiumUser().setDisplayName(newDisplayName);
                        globalVariables.getPremiumUser().setPassword(newPassWord);
                    } catch (SQLException e) {
                        DialogBoxManager.errorDialogBox("An error has occurred", "check premium username and password and try again");
                        e.printStackTrace();
                    }
                } else if (user == 3) {
                    try {
                        db_connector.changeDisplayNamePassword("administrator", newDisplayName, newPassWord, userName);
                        System.out.println("update admin");
                        DialogBoxManager.notificationDialogBox("Changes Confirmed", confirmationMessage);
                        globalVariables.getAdministrator().setDisplayName(newDisplayName);
                        globalVariables.getAdministrator().setPassword(newPassWord);
                    } catch (SQLException e) {
                        DialogBoxManager.errorDialogBox("An error has occurred", "check administrator username and password and try again");
                        e.printStackTrace();
                    }
                }
            }
        }else{
            DialogBoxManager.errorDialogBox("Error", "Display name and password must be 6-12 characters, try again");
        }
    }
}
