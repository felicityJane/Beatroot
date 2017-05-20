package musicplayer.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.Main;
import musicplayer.SceneManager;
import musicplayer.model.GlobalVariables;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import static musicplayer.SceneManager.sceneManager;

public class SettingsPageController implements Initializable {

    @FXML private AnchorPane settingsAnchorPage;
    @FXML private TextField editDisplayNameField, editPasswordField;
    @FXML private Label editDisplayNameLabel, editPasswordLabel, warningLabel;
    @FXML private Button darkThemeButton, lightThemeButton;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    GlobalVariables globalVariables = GlobalVariables.getInstance();
    private String userName, newPassWord, newDisplayName;
    int user;
    private AnchorPane welcomeParent, welcomeParentTwo, aboutParent, adminLogInParent, adminSignUpParent, albumParent, artistParent, FAQSParent, helpParent, logInParent,
    paymentParent, signUpParent, songParent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalVariables.setSettingsPageController(this);
        globalVariables.getMainMenuController().menuBarFitToParent(settingsAnchorPage);
        globalVariables.getMainMenuController().disableMenuItems();

        if(globalVariables.getTrialuser()!= null && globalVariables.getPremiumUser() == null && globalVariables.getAdministrator() == null) {
            editDisplayNameLabel.setText("Current Display :  " + globalVariables.getTrialuser().getDisplayName());
            editPasswordLabel.setText("Current Password :  " + globalVariables.getTrialuser().getPassword());
            userName = globalVariables.getTrialuser().getUserName();
            user = 1;
            System.out.println("type trial ");
        } else if (globalVariables.getPremiumUser() != null && globalVariables.getTrialuser() == null && globalVariables.getAdministrator() == null) {
            editDisplayNameLabel.setText("Current Display : " + globalVariables.getPremiumUser().getDisplayName());
            editPasswordLabel.setText("Current Password : " + globalVariables.getPremiumUser().getPassword());
            userName = globalVariables.getPremiumUser().getUserName();
            user = 2;
            System.out.println("type premium");
        } else if (globalVariables.getAdministrator() != null && globalVariables.getPremiumUser() == null && globalVariables.getTrialuser() == null) {
            editDisplayNameLabel.setText("Current Display :  " + globalVariables.getAdministrator().getDisplayName());
            editPasswordLabel.setText("Current Password :  " + globalVariables.getAdministrator().getPassword());
            userName = globalVariables.getTrialuser().getUserName();
            user = 3;
            System.out.println(" type admin");
        }
    }
    @FXML private void onSaveButton() {

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
                        DialogBoxManager.errorDialogBox("An error has occurred", "check username and password and try again");
                        System.out.println("error trial");
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
                        DialogBoxManager.errorDialogBox("An error has occurred", "check username and password and try again");
                        System.out.println("error premium");
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
                        DialogBoxManager.errorDialogBox("An error has occurred", "check username and password and try again");
                        System.out.println("error admin");
                        e.printStackTrace();
                    }
                }

            }
        }else{
            DialogBoxManager.errorDialogBox("Error", "Display name and password must be 6-12 characters");
        }
    }
}
