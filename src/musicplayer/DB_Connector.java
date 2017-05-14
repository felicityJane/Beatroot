package musicplayer;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import musicplayer.model.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import musicplayer.model.Administrator;
import musicplayer.model.Country;
import musicplayer.model.Gender;
import musicplayer.model.User;

public class DB_Connector {

	private String urlOfDatabase;
	private Statement statement;
	private Connection connection;
	private ResultSet resultSet;
	// private Administrator admin;
	// private TrialUser trialUser;
	// private PremiumUser premiumUser;
	private User user;

	public DB_Connector(String urlOfDatabase) {

		try {
			this.urlOfDatabase = urlOfDatabase;
			connection = (Connection) DriverManager.getConnection(urlOfDatabase);
			statement = connection.createStatement();
		} catch (SQLException sqlEx) {
			DialogBoxManager.errorDialogBox("Unable to access specified database",
					"Error while accessing the database. Please try again.");
			sqlEx.printStackTrace();

		}
	}

	public String search(String parameterToSearch, String tableName, String whereStatement) {

		String sqlString = "";
		try {
			ResultSet rs = statement
					.executeQuery("SELECT " + parameterToSearch + " FROM " + tableName + " WHERE " + whereStatement);
			while (rs.next()) {
				sqlString = rs.getString(1);
			}
		} catch (SQLException ex) {

			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing search query. Please try again.");
			ex.printStackTrace();
		}

		return sqlString;
	}

	public ArrayList<String> searchMultipleResults(String parameterToSearch, String tableName, String whereStatement) {

		ArrayList<String> sqlArrayList = new ArrayList<>();
		try {
			ResultSet rs = statement
					.executeQuery("SELECT " + parameterToSearch + " FROM " + tableName + " WHERE " + whereStatement);
			while (rs.next()) {
				sqlArrayList.add(rs.getString(1));
			}
		} catch (SQLException ex) {

			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing multiple search query. Please try again.");
			ex.printStackTrace();
		}

		return sqlArrayList;
	}

	public void update(String tableToUpdate, String parameterToUpdate, String newParameter, String whereStatement) {

		try {
			int rows = statement.executeUpdate("UPDATE " + tableToUpdate + " SET " + parameterToUpdate + " = "
					+ newParameter + " WHERE " + whereStatement);
			System.out.println("Updated rows: " + rows);
		} catch (SQLException sqlEx) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing update query. Please try again.");
			sqlEx.printStackTrace();
		}
	}

	public void insert(String tableNameAndParameters, String values) {

		try {
			statement.executeUpdate("INSERT INTO " + tableNameAndParameters + " VALUES " + values);

		} catch (MySQLIntegrityConstraintViolationException ignored) {
			// TODO
		} catch (SQLException sqlEx) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing insert query. Please try again.");
			sqlEx.printStackTrace();
		}
	}

	public void delete(String tableToDeleteFrom, String whereStatement) {

		try {
			statement.executeUpdate("DELETE FROM " + tableToDeleteFrom + " WHERE " + whereStatement);

		} catch (SQLException sqlEx) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing delete query. Please try again.");
			sqlEx.printStackTrace();
		}
	}

	public void logInTrial(String userName, String password, ActionEvent event, Label warningLabel) {
		try {
			ResultSet rs = statement.executeQuery(
					"SELECT user_name, password,display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, free_trial_end_date, gender_gender_id, playlist_link FROM trial_user WHERE user_name = '"
							+ userName + "'");

			if (rs.next()) {
				if (password.equals(rs.getString(2))) {
					Path path = Paths.get("UserName.bin");
					ArrayList<String> userNameAndType = new ArrayList<>();
					userNameAndType.add(0, userName);
					userNameAndType.add(1, "TrialUser");

					Files.write(path, userNameAndType, StandardOpenOption.CREATE);

					SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");

				} else {
					warningLabel.setText("Invalid username or password!!");
				}
			}
			if (!rs.next()) {
				warningLabel.setText("Invalid Username or password!!");
			}
		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing trial login query. Please try again.");
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logInPremium(String userName, String password, ActionEvent event, Label warningLabel) {

		try {
			ResultSet rs = statement.executeQuery(
					"SELECT user_name, password,display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, bank_card_number, expiration_date, card_type, billing_account_owner_name, billing_address, billing_city, billing_postal_code,billing_country,billing_phone_number, gender_gender_id, playlist_link FROM premium_user WHERE user_name='"
							+ userName + "'");

			if (rs.next()) {
				if (password.equals(rs.getString(2))) {

					Path path = Paths.get("UserName.bin");
					ArrayList<String> userNameAndType = new ArrayList<>();
					userNameAndType.add(0, userName);
					userNameAndType.add(1, "PremiumUser");

					Files.write(path, userNameAndType, StandardOpenOption.CREATE);

					SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");

				} else {
					warningLabel.setText("Invalid username or password!!");
				}
			}
			if (!rs.next()) {
				warningLabel.setText("Invalid Username or password!!");
			}
		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing premium login query. Please try again.");
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkTrialUserName(String userName, Label warningLabel, ActionEvent event) {
		try {
			ResultSet rs = statement.executeQuery("SELECT user FROM user_link WHERE user='" + userName + "'");
			if (rs.next()) {
				warningLabel.setText("Username is already taken");
				warningLabel.setVisible(false);
			}
		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on User Name. Please try again.");
			ex.printStackTrace();
		}
	}

	public void checkPremiumUserName(String userName, Label warningLabel, ActionEvent event) {
		try {
			ResultSet rs = statement.executeQuery("SELECT user FROM user_link WHERE user='" + userName + "'");
			if (rs.next()) {
				warningLabel.setText("Username is already taken");
			} else {
				SceneManager.sceneManager.changeScene(event, "view/paymentMenu.fxml");
			}
		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on User Name. Please try again.");
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** @author Viktor */

	public boolean checkUserName(String userName, Label warningLabel, ActionEvent event) {
		boolean exists = false;
		try {
			ResultSet rs = statement.executeQuery("SELECT user FROM user_link WHERE user='" + userName + "'");
			if (rs.next()) {
				warningLabel.setText("Username is already taken");
				warningLabel.setVisible(false);
				exists = true;
				return exists;
			}
		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on User Name. Please try again.");
			ex.printStackTrace();
		}

		return exists;
	}

	public String getUrlOfDatabase() {
		return urlOfDatabase;
	}

	public User getUser() {
		return user;
	}

	/**
	 * @author Viktor
	 */
	// Patch method only for admins
	/*
	 *
	 * Staff ID column 1; user name col 2; display name col 4; password col 3;
	 * first name col 5; last name col 6; date of birth col 7; email address col
	 * 8; physical address col 9;city of residence col 10; postal code col 11;
	 * country col 12; gender 17; phone num col 13; start date col 14; wage col
	 * 15; contract hours col 16; playlist link col 18;
	 *
	 * @param warningLabel
	 */

	public void logInAdministrator(String staffId, String userName, String password, ActionEvent event,
			Label warningLabel) {
		try {
			resultSet = statement.executeQuery(
					"select * from administrator left join gender on administrator.gender_gender_id=gender.gender_id where user_name='"
							+ userName + "' and staff_id='" + staffId + "'");
			// "select * from administrator where user_name='" + userName + "'
			// and staff_id='" + staffId + "'");

			if (resultSet.next()) {
				if (password.equals(resultSet.getString(3))) {
					System.out.println(Gender.fromString(resultSet.getString(20)));
					// ResultSet rs = statement.executeQuery(
					// "select gender.gender from gender left join administrator
					// on gender.gender_id=administrator.gender_gender_id where
					// administrator.gender_gender_id='"
					// + resultSet.getInt(17) + "'");
					// if(rs.next()){
					// Gender gender = Gender.fromString(rs.getString(1));
					// System.out.println(gender.toString());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					this.user = new Administrator(resultSet.getString(1), resultSet.getString(2),
							resultSet.getString(4), resultSet.getString(3), resultSet.getString(5),
							resultSet.getString(6), resultSet.getDate(7), resultSet.getString(8),
							resultSet.getString(9), resultSet.getString(10), resultSet.getString(11),
							Country.fromString(resultSet.getString(12)), Gender.fromString(resultSet.getString(20)),
							resultSet.getString(13), resultSet.getDate(14), resultSet.getFloat(15),
							resultSet.getFloat(16));
					System.out.println(getUser());
					System.out.println("('" + ((Administrator) user).getStaffID() + "', '" + user.getUserName() + "','"
							+ user.getPassword() + "','" + user.getDisplayName() + "','" + user.getFirstName() + "','"
							+ user.getLastName() + "','" + sdf.format(user.getDateOfBirth()) + "','"
							+ user.getEmailAddress() + "','" + user.getPhysicalAddress().getStreetNameAndNumber()
							+ "','" + user.getCityOfResidence() + "','" + user.getPostalCode() + "','"
							+ user.getCountry() + "','" + user.getPhoneNumber() + "','"
							+ sdf.format(((Administrator) user).getStartDate()) + "','"
							+ ((Administrator) user).getWage() + "','" + ((Administrator) user).getContractHours()
							+ "','" + user.getGender().ordinal() + "','" + ((Administrator) user).getStaffID() + "')");
					SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
				}
			}

		} catch (SQLException e) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing login query. Please try again.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public Administrator getAdmin() {
	// return admin;
	// }
	//
	// public void setAdmin(Administrator admin) {
	// this.admin = admin;
	// }
	//
	// public TrialUser getTrialUser() {
	// return trialUser;
	// }
	//
	// public void setTrialUser(TrialUser trialUser) {
	// this.trialUser = trialUser;
	// }
	//
	// public PremiumUser getPremiumUser() {
	// return premiumUser;
	// }
	//
	// public void setPremiumUser(PremiumUser premiumUser) {
	// this.premiumUser = premiumUser;
	// }
	public String searchUser(String parameterToSearch, String tableName, String whereStatement, String input) {

		String sqlString = "";
		try {
			ResultSet rs = statement.executeQuery("SELECT " + parameterToSearch + " FROM " + tableName + " WHERE " + whereStatement + input);
			while (rs.next()) {
				sqlString = rs.getString(1);
			}
		}
		catch (SQLException ex) {

			DialogBoxManager.errorDialogBox("Cannot run query","Error on executing search query. Please try again.");
			ex.printStackTrace();
		}

		return sqlString;
	}
	public String changeDisplayName(String displayName, String userName) {

		String sqlString = "";
		try {
			int rows = statement.executeUpdate("UPDATE premium_user SET display_name = " + displayName + " WHERE user_name  = " + userName);
			System.out.println("Updated rows: " + rows);
		}
		catch (SQLException ex) {

			DialogBoxManager.errorDialogBox("Cannot run query","Error on executing search query. Please try again.");
			ex.printStackTrace();
		}

		return sqlString;
	}
	public Album getAlbumDetails(Integer albumId) {
		Album album = null;
		try {
			ResultSet rs = statement.executeQuery("SELECT album_id, album_name, album_cover_path FROM album WHERE album_id = '" + albumId + "'");

			if (rs.next()) {
				if (albumId.equals(rs.getInt(1))) {
					String albumName = rs.getString(2);
					String albumCover = rs.getString(3);
					album = new Album(albumName, new Image(albumCover));
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing album details query. Please try again.");
			ex.printStackTrace();
		}
		return album;
	}
	public ArrayList<MusicTrack> getTrackDetails(Integer albumId) {
		ArrayList<MusicTrack> musicTrackArrayList = new ArrayList<MusicTrack>();
		try {
			ResultSet rs = statement.executeQuery("SELECT album.album_id, music_track.track_id, music_track.track_name, music_track.track_length, music_track.track_url from album_has_music_track\n" +
					"JOIN album ON album_has_music_track.album_album_id = album.album_id\n" +
					"JOIN music_track ON album_has_music_track.music_track_track_id = music_track.track_id WHERE album_has_music_track.album_album_id =  '" + albumId + "'");

			while (rs.next()) {
				if (albumId.equals(rs.getInt(1))) {
					Integer trackId = rs.getInt(2);
					String trackName = rs.getString(3);
					Time trackDuration = rs.getTime(4);
					String trackUrl = rs.getString(5);
					MusicTrack musicTrack = new MusicTrack(trackName,trackUrl);
					musicTrack.setID(trackId);
					//musicTrack.setTrackLength(trackDuration);
					musicTrackArrayList.add(musicTrack);
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing album details query. Please try again.");
			ex.printStackTrace();
		}
		return musicTrackArrayList;
	}
	public MusicArtist getArtistDetails(Integer musicTrackId) {
		MusicArtist musicArtist = null;
		try {
			ResultSet rs = statement.executeQuery("SELECT music_track.track_id, music_artist.artist_id, music_artist.stage_name from album_has_music_track\n" +
					"JOIN music_track ON album_has_music_track.music_track_track_id = music_track.track_id\n" +
					"JOIN music_artist ON music_track.music_artist_artist_id = music_artist.artist_id WHERE music_track.track_id = '" + musicTrackId + "'");

			if (rs.next()) {
				if (musicTrackId.equals(rs.getInt(1))) {
					Integer artistId = rs.getInt(2);
					String stageName = rs.getString(3);
					musicArtist = new MusicArtist(stageName);
					musicArtist.setArtistID(artistId);
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing album details query. Please try again.");
			ex.printStackTrace();
		}
		return musicArtist;
	}

}
