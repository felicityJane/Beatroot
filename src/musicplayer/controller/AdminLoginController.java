package musicplayer.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import musicplayer.SceneManager;

public class AdminLoginController {
	@FXML
	private TextField userNameField, passwordField;
	@FXML
	private Button signUpButton, logInButton;

	public void signUpButtonPress(ActionEvent event) {
		try {
			SceneManager.sceneManager.changeScene(event, "view/AdminSignUp.fxml");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void logInButtonPress(ActionEvent e) {
		/**
		 * TODO try { SceneManager.sceneManager.changeScene(e,
		 * "view/AdminMainMenu.fxml"); } catch (IOException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */
	}

}
