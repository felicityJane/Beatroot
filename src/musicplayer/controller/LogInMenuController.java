package musicplayer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;

public class LogInMenuController implements Initializable {

	@FXML
	private TextField userName;
	@FXML
	private PasswordField userPassword;
	@FXML
	private Button loginButton, signUpButton;
	@FXML
	private MainMenuController mainMenuController;
	@FXML
	private AnchorPane logInParentAnchorPane;
	@FXML
	private Label warningLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainMenuController.init(this);
		mainMenuController.menuBarFitToParent(logInParentAnchorPane);
	}

	@FXML
	private void handleLoginButton(ActionEvent event) {
		try {
			String userNam = userName.getText();
			String password = userPassword.getText();

			DB_Connector connector = new DB_Connector(
					"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
			connector.logInTrial(userNam, password, event, warningLabel);
			connector.logInPremium(userNam, password, event, warningLabel);
			// Create User

		} catch (Exception e) {
			DialogBoxManager.errorDialogBox("Error occurred", "Changing from login scene to welcome scene");
			e.printStackTrace();
		}
	}

	@FXML
	private void handleSignUpButton(ActionEvent event) {
		try {
			SceneManager.sceneManager.changeScene(event, "view/signUpMenu.fxml");
		} catch (Exception e) {
			DialogBoxManager.errorDialogBox("Error occurred", "Changing from login scene to sign up scene");
			e.printStackTrace();
		}

	}

	private void createUser() {
		DB_Connector connector = new DB_Connector(
				"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");

	}
}