package musicplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.Album;
import musicplayer.model.GlobalVariables;

public class AddAlbumController implements Initializable {
	@FXML
	private TextField albumNameField, albumCoverPathField, albumDebutYearField;
	private GlobalVariables variables = GlobalVariables.getInstance();
	private DB_Connector databaseConnector;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		variables.setAddAlbumcontroller(this);
		variables.getAdminMenuController().getCreateAlbumButton().setText("Main menu");
		variables.getAdminMenuController().getCreateAlbumButton().setOnAction(event -> {
			try {
				SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
		databaseConnector = new DB_Connector(
				"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
	}

	public void addButtonPressed() {
		try {
			Album album = new Album(albumNameField.getText(), new Image(albumCoverPathField.getText()),
					sdf.parse(albumDebutYearField.getText()));
			System.out.println(album.getAlbumCover().toString());
			System.out.println(albumCoverPathField.getText());
			System.out.println(albumCoverPathField.getText().toString());
			System.out
					.println("https://s-media-cache-ak0.pinimg.com/736x/5f/7e/82/5f7e820bb7bd2b60a731b3edf7a0c0c8.jpg");
			int id = databaseConnector.insertWithAutoIncrementKey(
					// INSERT INTO `beatroot`.`album` (`album_id`, `album_name`,
					// `album_cover_path`, `year_of_publication`,
					// `administrator_staff_id`) VALUES ('', 'dohentaida',
					// 'https://s-media-cache-ak0.pinimg.com/736x/5f/7e/82/5f7e820bb7bd2b60a731b3edf7a0c0c8.jpg',
					// 2005, 'beatroot');

					"`beatroot`.`album`(`album_name`,`album_cover_path`,`year_of_publication`,`administrator_staff_id`)",
					"('" + album.getAlbumName() + "','" + albumCoverPathField.getText() + "','"
							+ sdf.format(album.getDebutYear()) + "','" + variables.getAdministrator().getStaffID()
							+ "')");
			album.setAlbumID(id);
			Boolean bool = DialogBoxManager.confirmationDialogBox("Success",
					"The album has been added to the database.\nPress cancel to remove");
			if (!bool) {
				databaseConnector.delete("`beatroot`.`album`", "`album_id`='" + album.getAlbumID() + "'");
			} else {
				clearAll();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void clearButtonPressed() {
		clearAll();
	}

	public void clearAll() {
		albumCoverPathField.clear();
		albumNameField.clear();
	}
}
