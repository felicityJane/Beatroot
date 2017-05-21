package musicplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.GlobalVariables;
import musicplayer.model.MusicArtist;
import musicplayer.model.Rating;

public class AddArtistController implements Initializable {
	@FXML
	private Button addButton, clearButton;
	@FXML
	private TextField artistNameField, foundationYearField;
	@FXML
	private TextArea artistDescriptionArea;

	private GlobalVariables variables = GlobalVariables.getInstance();
	private DB_Connector databaseConnector;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		variables.setAddArtistController(this);
		variables.getAdminMenuController().getAddArtistButton().setText("Main page");
		variables.getAdminMenuController().getAddArtistButton().setOnAction(event -> {
			try {
				SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
		databaseConnector = new DB_Connector(
				"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
	}

	public void addArtistToDatabase() {
		// INSERT INTO `beatroot`.`music_artist` (`artist_id`,
		// `stage_name`, `administrator_staff_id`, `rating_id`,
		// `year_of_foundation`, `artist_description`) VALUES ('16', 'asd',
		// 'qwe', '23', 1771, 'asdqwe');
		try {
			System.out.println("Here");
			int i = databaseConnector.insertWithAutoIncrementKey(
					"`beatroot`.`rating`(rating_id,sum_from_all_voters,final_rating)", "(default,default,default)");
			System.out.println(i);
			Date debutYear = sdf.parse(foundationYearField.getText());
			System.out.println(sdf.parse(foundationYearField.getText()));
			MusicArtist musicArtist = new MusicArtist(artistNameField.getText(), debutYear,
					artistDescriptionArea.getText());
			System.out.println(musicArtist.getStageName());
			Rating rating = new Rating(musicArtist, i);
			System.out.println(rating.getRatingID());
			// musicArtist.set
			/*
			 * INSERT INTO `beatroot`.`music_artist` (`stage_name`,
			 * `administrator_staff_id`, `rating_id`, `year_of_foundation`,
			 * `artist_description`) VALUES ('dfger43', 'beatroot', '80', 1997,
			 * 'qwrtw6');
			 */
			databaseConnector.insert(
					"`beatroot`.`music_artist`(`stage_name`,`administrator_staff_id`,`rating_id`,`year_of_foundation`,`artist_description`)",
					"('" + artistNameField.getText() + "','" + variables.getAdministrator().getStaffID() + "','"
							+ rating.getRatingID() + "'," + sdf.format(musicArtist.getPublicationYear()) + ",'"
							+ artistDescriptionArea.getText() + "')");
			DialogBoxManager.notificationDialogBox("Success", "The music artist has been added to the database");
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
	}

	public void clear() {
		artistDescriptionArea.clear();
		artistNameField.clear();
		foundationYearField.clear();
	}

}
