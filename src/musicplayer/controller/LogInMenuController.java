package musicplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.GlobalVariables;

public class LogInMenuController implements Initializable {

	@FXML private TextField userName;
	@FXML private PasswordField userPassword;
	@FXML private Button loginButton,signUpButton;
	@FXML private AnchorPane logInParentAnchorPane;
	@FXML private Label warningLabel;
	private javafx.scene.input.KeyEvent[] keyPressed = new KeyEvent[3];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GlobalVariables globalVariables = GlobalVariables.getInstance();
		globalVariables.setLogInMenuController(this);
		globalVariables.getMainMenuController().menuBarFitToParent(logInParentAnchorPane);
		logInParentAnchorPane.setFocusTraversable(true);
		logInParentAnchorPane.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ALT && keyPressed[1] == null && keyPressed[2] == null) {
					keyPressed[0] = event;
				} else if (event.getCode() == KeyCode.F && keyPressed[2] == null && keyPressed[0] != null) {
					keyPressed[1] = event;
				} else if (event.getCode() == KeyCode.V && keyPressed[0] != null && keyPressed[1] != null) {
					keyPressed[2] = event;
				}

				if (keyPressed[0] != null && keyPressed[1] != null && keyPressed[2] != null) {
					try {
						SceneManager.getInstance().changeScene(event, "view/AdminLogIn.fxml");
					} catch (IOException ie) {
						ie.printStackTrace();
					}
				}
		});
	}
	@FXML
	private void handleLoginButton(ActionEvent event) {
		try {
			String userNam=userName.getText();
			String password=userPassword.getText();

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
			SceneManager.getInstance().changeScene(event, "view/signUpMenu.fxml");
		} catch (Exception e) {
			DialogBoxManager.errorDialogBox("Error occurred", "Changing from login scene to sign up scene");
			e.printStackTrace();
		}

	}
}