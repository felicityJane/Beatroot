package musicplayer.controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by felic on 10/04/2017.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.User;

public class MainMenuController implements Initializable {

	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem createNewPlaylistMenu;
	@FXML
	private MenuItem searchMenu;
	@FXML
	private MenuItem logoutMenu;
	@FXML
	private MenuItem personalInfoMenu;
	@FXML
	private MenuItem settingsMenu;
	@FXML
	private MenuItem aboutMenu;
	@FXML
	private Button uploadSongsButton, createAlbumButton, addArtistButton, usersPanelButton, modifyAlbumButton,
			modifySongButton;
	@FXML
	private VBox adminMenu;
	private LogInMenuController logInMenuController;
	private WelcomeMenuController welcomeMenuController;
	private SignUpMenuController signUpMenuController;
	private User user;

	// TODO <fx:include fx:id="adminMenu" source="AdminMenu.fxml" />
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// if (!(user instanceof Administrator)) {
		// adminMenu.setVisible(false);
		// }
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

	public void setDisabledMenuItemsWelcomeScene() {
		createNewPlaylistMenu.setDisable(false);
		searchMenu.setDisable(false);
		logoutMenu.setDisable(false);
		personalInfoMenu.setDisable(false);
		searchMenu.setDisable(false);
		settingsMenu.setDisable(false);
		aboutMenu.setDisable(false);
	}

	void menuBarFitToParent(AnchorPane parentAnchor) {
		menuBar.prefWidthProperty().bind(parentAnchor.widthProperty());
	}

	@FXML
	private void createNewPlaylistMenuOption() {
		// refer to create playlist method here
	}

	@FXML
	private void searchMenuOption() {
		// refer to search method here
	}

	@FXML
	private void logOutMenuOption(ActionEvent event) {
		boolean answer = DialogBoxManager.confirmationDialogBox("Are you sure you want to log out?",
				"click ok to continue");
		if (answer) {
			try {
				SceneManager.sceneManager.changeSceneMenuBar(menuBar, "view/logInMenu.fxml");
			} catch (Exception e) {
				DialogBoxManager.errorDialogBox("Error occurred", "logging out");
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void exitMenuOption() {
		System.exit(0);
	}

	@FXML
	private void personalInfoMenuOption() {
		// some kind of options to change personal info here
	}

	@FXML
	private void settingsMenuOption() {
		// some kind of settings menu here
	}

	@FXML
	private void aboutMenuOption() {
		// some information about Beatroot here
	}

	@FXML
	private void faqsMenuOption() {
		// Link to FAQ's here
	}
}