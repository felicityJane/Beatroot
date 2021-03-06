package musicplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import musicplayer.SceneManager;
import musicplayer.model.GlobalVariables;

public class AdminMenuController implements Initializable {

	@FXML
	private Button uploadButton, createAlbumButton, addArtistButton, modifyUsersButton, modifyAlbumButton,
			modifySongsButton;
	private GlobalVariables variables = GlobalVariables.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		variables.setAdminMenuController(this);
	}

	public Button getModifyUsersButton() {
		return modifyUsersButton;
	}

	public void setModifyUsersButton(Button modifyUsersButton) {
		this.modifyUsersButton = modifyUsersButton;
	}

	public void modifyUsersButtonPressed(ActionEvent ev) {
		try {
			SceneManager.sceneManager.changeScene(ev, "view/ModifyUserPanel.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Button getAddArtistButton() {
		return addArtistButton;
	}

	public void setAddArtistButton(Button addArtistButton) {
		this.addArtistButton = addArtistButton;
	}

	public void addArtistButtonPressed(ActionEvent ev) {
		try {
			SceneManager.sceneManager.changeScene(ev, "view/AddArtistPanel.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createAlbumButtonPressed(ActionEvent ev) {
		try {
			SceneManager.sceneManager.changeScene(ev, "view/AlbumCreationPanel.fxml");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Button getCreateAlbumButton() {
		return createAlbumButton;
	}

	public void setCreateAlbumButton(Button createAlbumButton) {
		this.createAlbumButton = createAlbumButton;
	}
}
