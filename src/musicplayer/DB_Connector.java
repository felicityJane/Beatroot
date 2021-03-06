package musicplayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import musicplayer.model.Administrator;
import musicplayer.model.Album;
import musicplayer.model.Comment;
import musicplayer.model.Country;
import musicplayer.model.Gender;
import musicplayer.model.GlobalVariables;
import musicplayer.model.MusicArtist;
import musicplayer.model.MusicTrack;
import musicplayer.model.PaymentMethod;
import musicplayer.model.PremiumUser;
import musicplayer.model.Rating;
import musicplayer.model.TrialUser;

public class DB_Connector {
	private String urlOfDatabase;
	private Statement statement;
	private Connection connection;
	private ResultSet resultSet;
	private Administrator administrator;
	private TrialUser trialUser;
	private PremiumUser premiumUser;
	private GlobalVariables globalVariables = GlobalVariables.getInstance();

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

	public int insertWithAutoIncrementKey(String tableNameAndParameters, String values) {

		try {
			statement.executeUpdate("INSERT INTO " + tableNameAndParameters + " VALUES " + values,
					Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
			}
		} catch (MySQLIntegrityConstraintViolationException ignored) {
			// TODO
		} catch (SQLException sqlEx) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing insert query. Please try again.");
			sqlEx.printStackTrace();
		}
		try {
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Nothing found boss");
		return 0;
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

			ResultSet rs = statement.executeQuery("SELECT * FROM trial_user WHERE user_name = '" + userName + "'");

			if (rs.next()) {
				if (password.equals(rs.getString(2))) {
					Path path = Paths.get("UserName.bin");
					ArrayList<String> userNameAndType = new ArrayList<>();
					userNameAndType.add(0, userName);
					userNameAndType.add(1, "Trial");

					Files.write(path, userNameAndType, StandardOpenOption.CREATE);

					TrialUser trialUser = new TrialUser(rs.getString(1), rs.getString(3), rs.getString(2),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
							rs.getString(9), rs.getString(11), rs.getString(12), rs.getString(13),
							Country.fromString(rs.getString(14)), Gender.fromString(rs.getString(16)), rs.getString(10),
							rs.getDate(15));
					globalVariables.setTrialuser(trialUser);
					globalVariables.setPremiumUser(null);
					globalVariables.setAdministrator(null);

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
			ResultSet rs = statement.executeQuery("SELECT * FROM premium_user WHERE user_name='" + userName + "'");

			if (rs.next()) {
				if (password.equals(rs.getString(2))) {

					Path path = Paths.get("UserName.bin");
					ArrayList<String> userNameAndType = new ArrayList<>();
					userNameAndType.add(0, userName);
					userNameAndType.add(1, "Premium");
					userNameAndType.add(2, rs.getString(3));

					Files.write(path, userNameAndType, StandardOpenOption.CREATE);

					PremiumUser premiumUser = new PremiumUser(rs.getString(1), rs.getString(3), rs.getString(2),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
							rs.getString(9), rs.getString(11), rs.getString(12), rs.getString(13),
							Country.fromString(rs.getString(14)), Gender.values()[Integer.parseInt(rs.getString(24))],
							rs.getString(10), rs.getString(15), rs.getDate(16),
							PaymentMethod.valueOf(rs.getString(17).toUpperCase()), rs.getString(18), rs.getString(19),
							rs.getString(20), rs.getString(21), Country.fromString(rs.getString(22)), rs.getString(23));
					globalVariables.setPremiumUser(premiumUser);
					globalVariables.setAdministrator(null);
					globalVariables.setTrialuser(null);

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
					System.out.println(Gender.fromString(resultSet.getString(22)));
					// ResultSet rs = statement.executeQuery(
					// "select gender.gender from gender left join administrator
					// on gender.gender_id=administrator.gender_gender_id where
					// administrator.gender_gender_id='"
					// + resultSet.getInt(17) + "'");
					// if(rs.next()){
					// Gender gender = Gender.fromString(rs.getString(1));
					// System.out.println(gender.toString());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					administrator = new Administrator(resultSet.getString(1), resultSet.getString(2),
							resultSet.getString(4), resultSet.getString(3), resultSet.getString(5),
							resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
							resultSet.getDate(9), resultSet.getString(10), resultSet.getString(11),
							resultSet.getString(12), resultSet.getString(13),
							Country.fromString(resultSet.getString(14)), Gender.fromString(resultSet.getString(22)),
							resultSet.getString(15), resultSet.getDate(16), resultSet.getFloat(17),
							resultSet.getFloat(18));
					System.out.println(getAdministrator());
					System.out.println("('" + administrator.getStaffID() + "', '" + administrator.getUserName() + "','"
							+ administrator.getPassword() + "','" + administrator.getDisplayName() + "','"
							+ administrator.getFirstName() + "','" + administrator.getLastName() + "','"
							+ sdf.format(administrator.getDateOfBirth()) + "','" + administrator.getEmailAddress()
							+ "','" + administrator.getPhysicalAddress().getStreetNameAndNumber() + "','"
							+ administrator.getCityOfResidence() + "','" + administrator.getPostalCode() + "','"
							+ administrator.getCountry() + "','" + administrator.getPhoneNumber() + "','"
							+ sdf.format(administrator.getStartDate()) + "','" + administrator.getWage() + "','"
							+ administrator.getContractHours() + "','" + administrator.getGender().ordinal() + "','"
							+ administrator.getStaffID() + "')");
					globalVariables.setAdministrator(administrator);
					globalVariables.setTrialuser(null);
					globalVariables.setPremiumUser(null);
					System.out.println(globalVariables.getPremiumUser() + " " + globalVariables.getTrialuser());
					SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
				}
			}

		} catch (SQLException e) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing login query. Please try again.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public TrialUser getTrialUser() {
		return trialUser;
	}

	public void setTrialUser(TrialUser trialUser) {
		this.trialUser = trialUser;
	}

	public PremiumUser getPremiumUser() {
		return premiumUser;
	}

	public void setPremiumUser(PremiumUser premiumUser) {
		this.premiumUser = premiumUser;
	}

	public String changeDisplayNamePassword(String tableToUpdate, String displayName, String password, String username)
			throws SQLException {

		String sqlString = "";

		int rows = statement.executeUpdate("UPDATE " + tableToUpdate + " SET display_name = '" + displayName
				+ "' , password = '" + password + "' WHERE user_name = '" + username + "'");
		System.out.println("Updated rows: " + rows);

		return sqlString;
	}

	public void getAlbumDetails(Integer albumId) {

		try {
			ResultSet rs = statement.executeQuery(
					"SELECT album_id, album_name, album_cover_path FROM album WHERE album_id = '" + albumId + "'");

			if (rs.next()) {
				if (albumId.equals(rs.getInt(1))) {
					String albumName = rs.getString(2);
					String albumCover = rs.getString(3);
					Album album = new Album(albumName, new Image(albumCover));
					globalVariables.setAlbum(album);
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing album details query. Please try again.");
			ex.printStackTrace();
		}

	}

	public void getTrackDetails(Integer albumId) {
		ArrayList<MusicTrack> musicTrackArrayList = new ArrayList<>();
		try {
			ResultSet rs = statement.executeQuery(
					"SELECT album.album_id, music_track.track_id, music_track.track_name, music_track.track_length, music_track.track_url, music_track.administrator_staff_id, music_track.rating_id, music_track.year_of_publication FROM album_has_music_track\n"
							+ "JOIN album ON album_has_music_track.album_album_id = album.album_id\n"
							+ "JOIN music_track ON album_has_music_track.music_track_track_id = music_track.track_id WHERE album_has_music_track.album_album_id =  '"
							+ albumId + "'");

			while (rs.next()) {
				if (albumId.equals(rs.getInt(1))) {
					Integer trackId = rs.getInt(2);
					String trackName = rs.getString(3);
					String trackTime = rs.getString(4);
					String trackUrl = rs.getString(5);
					// String adminId = rs.getString(6);
					Integer ratingId = rs.getInt(7);
					Date publicationYear = rs.getDate(8);
					MusicTrack musicTrack = new MusicTrack(trackName, trackUrl);
					musicTrack.setID(trackId);
					musicTrack.setTrackTime(trackTime);
					musicTrack.setPublicationYear(publicationYear);
					// musicTrack.setTrackLength(trackDuration);
					musicTrack.setRatingId(ratingId);
					musicTrackArrayList.add(musicTrack);
					globalVariables.setMusicTracks(musicTrackArrayList);
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing get track details details query. Please try again.");
			ex.printStackTrace();
		}
	}

	public void getArtistDetails(Integer musicTrackId) {
		try {

			ResultSet rs = statement.executeQuery(
					"SELECT music_track.track_id, music_artist.artist_id, music_artist.stage_name, music_artist.administrator_staff_id, music_artist.rating_id\n"
							+ ", music_artist.year_of_foundation, music_artist.artist_description, music_artist.year_of_foundation FROM album_has_music_track\n"
							+ "JOIN music_track ON album_has_music_track.music_track_track_id = music_track.track_id\n"
							+ "JOIN music_artist ON music_track.music_artist_artist_id = music_artist.artist_id WHERE music_track.track_id = '"
							+ musicTrackId + "'");

			if (rs.next()) {
				if (musicTrackId.equals(rs.getInt(1))) {
					Integer artistId = rs.getInt(2);
					String stageName = rs.getString(3);
					// String adminId = rs.getString(4);
					Integer ratingId = rs.getInt(5);
					Date publicationYear = rs.getDate(6);
					String artistDesc = rs.getString(7);
					Date yearOfFoundation = rs.getDate(8);
					MusicArtist musicArtist = new MusicArtist(stageName);
					musicArtist.setArtistID(artistId);
					musicArtist.setPublicationYear(publicationYear);
					musicArtist.setArtistDescription(artistDesc);
					musicArtist.setRatingId(ratingId);
					musicArtist.setPublicationYear(yearOfFoundation);
					globalVariables.setMusicArtist(musicArtist);
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing album details query. Please try again.");
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing get artist details query. Please try again.");
			ex.printStackTrace();
		}
	}

	public void getRating(Integer ratingId) {
		try {
			ResultSet rs = statement
					.executeQuery("SELECT rating_id, final_rating FROM rating WHERE rating_id = '" + ratingId + "'");

			if (rs.next()) {
				if (ratingId.equals(rs.getInt(1))) {
					Double finalRating = rs.getDouble(2);
					Rating rating = new Rating(finalRating);
					globalVariables.setRating(rating);
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing get rating query. Please try again.");
			ex.printStackTrace();
		}
	}

	public Integer getMusicTrackInfo(String trackName) {
		Integer track_id = null;
		try {
			ResultSet rs = statement.executeQuery(
					"SELECT track_name, track_id, track_length, rating_id, year_of_publication FROM music_track WHERE track_name = '"
							+ trackName + "'");

			if (rs.next()) {
				if (trackName.equals(rs.getString(1))) {
					track_id = rs.getInt(2);
					String trackLength = rs.getString(3);
					Integer ratingId = rs.getInt(4);
					Date publicationYear = rs.getDate(5);
					MusicTrack musicTrack = new MusicTrack(trackName);
					musicTrack.setID(track_id);
					musicTrack.setTrackTime(trackLength);
					musicTrack.setRatingId(ratingId);
					musicTrack.setPublicationYear(publicationYear);
					globalVariables.setMusicTrack(musicTrack);
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing get music track info query. Please try again.");
			ex.printStackTrace();
		}
		return track_id;
	}

	public Integer getAlbumIdFromTrackId(Integer trackId) {
		Integer album_id = null;
		try {
			ResultSet rs = statement.executeQuery(
					"SELECT music_track_track_id, album_album_id FROM album_has_music_track WHERE music_track_track_id = '"
							+ trackId + "'");

			if (rs.next()) {
				if (trackId.equals(rs.getInt(1))) {
					album_id = rs.getInt(2);
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing get album id from track id query. Please try again.");
			ex.printStackTrace();
		}
		return album_id;
	}

	public void getComments(Integer trackId) {
		ArrayList<Comment> comments = new ArrayList<>();
		try {
			ResultSet rs = statement
					.executeQuery("SELECT music_track_track_id, message FROM comment WHERE music_track_track_id =  '"
							+ trackId + "'");

			while (rs.next()) {
				if (trackId.equals(rs.getInt(1))) {
					String message = rs.getString(2);
					Comment comment = new Comment(message);
					comments.add(comment);
					globalVariables.setComments(comments);
				}
			}
		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query",
					"Error on executing get comments query. Please try again.");
			ex.printStackTrace();
		}
	}

	public void getTrialUsersDetails() {
		try {
			resultSet = statement.executeQuery("SELECT * FROM trial_user");
			while (resultSet.next()) {
				globalVariables.getModifyUserController().getData()
						.add(new TrialUser(resultSet.getString("user_name"), resultSet.getString("display_name"),
								resultSet.getString("password"), resultSet.getString("user_description"),
								resultSet.getString("personal_picture_path"), resultSet.getString("first_name"),
								resultSet.getString("last_name"), resultSet.getDate("date_of_birth"),
								resultSet.getString("email_address"), resultSet.getString("physical_address"),
								resultSet.getString("city_of_residence"), resultSet.getString("postal_code"),
								Country.fromString(resultSet.getString("country")),
								Gender.values()[Integer.parseInt(resultSet.getString("gender_gender_id"))],
								resultSet.getString("phone_number"), resultSet.getDate("free_trial_end_date")));
				globalVariables.getModifyUserController().getTrialUsersTable()
						.setItems(globalVariables.getModifyUserController().getData());
				// globalVariables.getModifyUserController().getTrialUsersTable().set
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPremiumUsersDetails() {
		try {
			resultSet = statement.executeQuery("SELECT * FROM premium_user");
			while (resultSet.next()) {
				globalVariables.getModifyUserController().getPremiumData()
						.add(new PremiumUser(resultSet.getString("user_name"), resultSet.getString("display_name"),
								resultSet.getString("password"), resultSet.getString("user_description"),
								resultSet.getString("personal_picture_path"), resultSet.getString("first_name"),
								resultSet.getString("last_name"), resultSet.getDate("date_of_birth"),
								resultSet.getString("email_address"), resultSet.getString("physical_address"),
								resultSet.getString("city_of_residence"), resultSet.getString("postal_code"),
								Country.fromString(resultSet.getString("country")),
								Gender.values()[Integer.parseInt(resultSet.getString("gender_gender_id"))],
								resultSet.getString("phone_number"), resultSet.getString("bank_card_number"),
								resultSet.getDate("expiration_date"),
								PaymentMethod.fromString(resultSet.getString("card_type")),
								resultSet.getString("billing_account_owner_name"),
								resultSet.getString("billing_address"), resultSet.getString("billing_city"),
								resultSet.getString("billing_postal_code"),
								Country.fromString(resultSet.getString("billing_country")),
								resultSet.getString("billing_phone_number")));
				globalVariables.getModifyUserController().getPremiumUsersTable()
						.setItems(globalVariables.getModifyUserController().getPremiumData());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getContact(String userName) {
		PremiumUser premiumUser;
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM premium_user WHERE user_name='" + userName + "'");

			if (rs.next()) {
				premiumUser = new PremiumUser(rs.getString(1), rs.getString(3), rs.getString(2), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9),
						rs.getString(11), rs.getString(12), rs.getString(13), Country.fromString(rs.getString(14)),
						Gender.values()[Integer.parseInt(rs.getString(24))], rs.getString(10), rs.getString(15),
						rs.getDate(16), PaymentMethod.valueOf(rs.getString(17).toUpperCase()), rs.getString(18),
						rs.getString(19), rs.getString(20), rs.getString(21), Country.fromString(rs.getString(22)),
						rs.getString(23));
				// globalVariables.getContactList().clear();
				if (premiumUser != null && !globalVariables.getContactList().contains(premiumUser)) {
					globalVariables.getContactList().add(premiumUser);
				}
			}
		} catch (SQLException sq) {
			DialogBoxManager.errorDialogBox("Error while running sql select query",
					"Error from method getContact in DB_Connector");

		}
	}

	public void findPremiumUser(String userName) {
		PremiumUser premiumUser = null;
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM premium_user WHERE user_name='" + userName + "'");

			if (rs.next()) {
				premiumUser = new PremiumUser(rs.getString(1), rs.getString(3), rs.getString(2), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9),
						rs.getString(11), rs.getString(12), rs.getString(13), Country.fromString(rs.getString(14)),
						Gender.values()[Integer.parseInt(rs.getString(24))], rs.getString(10), rs.getString(15),
						rs.getDate(16), PaymentMethod.valueOf(rs.getString(17).toUpperCase()), rs.getString(18),
						rs.getString(19), rs.getString(20), rs.getString(21), Country.fromString(rs.getString(22)),
						rs.getString(23));
			}
			if (premiumUser != null) {
				GlobalVariables.getInstance().setContactSelected(premiumUser);
			}
		} catch (SQLException sq) {
			DialogBoxManager.errorDialogBox("Error while running sql select query",
					"Error from method findPremiumUser in DB_Connector");

		}
	}

	public void findTrialUser(String userName) {
		TrialUser trialUser = null;
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM trial_user WHERE user_name='" + userName + "'");

			if (rs.next()) {
				trialUser = new TrialUser(rs.getString(1), rs.getString(3), rs.getString(2), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9),
						rs.getString(11), rs.getString(12), rs.getString(13), Country.fromString(rs.getString(14)),
						Gender.fromString(rs.getString(16)), rs.getString(10), rs.getDate(15));
			}
			if (trialUser != null) {
				GlobalVariables.getInstance().setContactSelected(trialUser);
			}
		} catch (SQLException sq) {
			DialogBoxManager.errorDialogBox("Error while running sql select query",
					"Error from method findTrialUser in DB_Connector");

		}
	}

}
