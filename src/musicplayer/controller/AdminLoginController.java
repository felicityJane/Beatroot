package musicplayer.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import musicplayer.DB_Connector;
import musicplayer.SceneManager;
import musicplayer.model.User;

public class AdminLoginController {
	@FXML
	private TextField userNameField, staffIdField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button signUpButton, logInButton;
	@FXML
	private Label warningLabel;
	private User user;

	public void signUpButtonPress(ActionEvent event) {
		try {
			SceneManager.sceneManager.changeScene(event, "view/AdminSignUp.fxml");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void logInButtonPress(ActionEvent e) {
		try {

			DB_Connector connector = new DB_Connector(
					"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
			connector.getUrlOfDatabase();
			connector.logInAdministrator(staffIdField.getText(), userNameField.getText(), passwordField.getText(), e,
					warningLabel);
			SceneManager.sceneManager.changeScene(e, "view/welcomeMenu.fxml");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
