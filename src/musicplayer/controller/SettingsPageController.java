package musicplayer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import musicplayer.DB_Connector;
import musicplayer.model.GlobalVariables;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SettingsPageController implements Initializable {

    @FXML private AnchorPane settingsAnchorPage;
    @FXML private Button backButton;
    @FXML private TextField editDisplayNameField;
    @FXML private Label editDisplayNameLabel;
    @FXML private TextField editPasswordField;
    @FXML private Label editPasswordLabel;
    @FXML private Button backgroundBlackButton;
    @FXML private Button backgroundWhiteButton;
    @FXML private Button saveChangesButton;
    private String userName = "Misstery";
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private Statement statement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GlobalVariables globalVariables = GlobalVariables.getInstance();
        globalVariables.setSettingsPageController(this);
        globalVariables.getMainMenuController().menuBarFitToParent(settingsAnchorPage);
        globalVariables.getMainMenuController().enableMenuItemsSettingsPage();

        editDisplayNameLabel.setText( db_connector.searchUser("display_name", "premium_user", "user_name = ","'"+userName+"'" ));
        editPasswordLabel.setText( db_connector.searchUser("password","premium_user","user_name = ", "'"+userName+"'"));
    }
    @FXML private void onButton(ActionEvent event){
        settingsAnchorPage.getStyleClass().add("greyBackground");
    }
    @FXML private void offButton(ActionEvent event){
        settingsAnchorPage.getStyleClass().removeAll("greyBackground");
    }
    @FXML private void editDisplayName(){

        String displayName = editDisplayNameField.getText();
        String userName = editPasswordField.getText();

        DB_Connector connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
        connector.changeDisplayName("'"+displayName+"'","'"+userName+"'");
    }
    @FXML private void editPassword(){


    }
}
