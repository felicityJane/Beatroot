package musicplayer;

import java.io.IOException;
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
import musicplayer.model.Administrator;
import musicplayer.model.Country;
import musicplayer.model.Gender;
import musicplayer.model.PaymentMethod;
import musicplayer.model.PremiumUser;
import musicplayer.model.TrialUser;
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
					String phoneNumber = "";
					String displayName = rs.getString(3);
					String firstName = rs.getString(4);
					String lastName = rs.getString(5);
					Date birthDay = rs.getDate(6);
					String emailAddress = rs.getString(7);
					String physicalAddress = rs.getString(8);
					String city = rs.getString(9);
					String postalCode = rs.getString(10);
					Country country = Country.valueOf(rs.getString(11));
					Date freeTrialEndDate = rs.getDate(12);
					Gender gender = Gender.values()[Integer.parseInt(rs.getString(13))];
					String playListLink = rs.getString(14);

					TrialUser user = new TrialUser(userName, displayName, password, firstName, lastName, birthDay,
							emailAddress, physicalAddress, city, postalCode, country, gender, phoneNumber,
							freeTrialEndDate);
					SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
					// call well come menucontroller setuser(User user);
					// this.user=user;
				} else {
					warningLabel.setText("Invalid username or password!!");
				}
			}

		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing login query. Please try again.");
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logInPremium(String userName, String password, ActionEvent event, Label warningLabel) {

		try {
			ResultSet rs = statement.executeQuery(
					"SELECT user_name, password,display_name, first_name, last_name, date_of_birth, email_address, city_of_residence, postal_code, country, bank_card_number, expiration_date, card_type, billing_account_owner_name, billing_city, billing_postal_code,billing_country,billing_phone_number, gender_gender_id, playlist_link FROM premium_user WHERE user_name='"
							+ userName + "'");

			if (rs.next()) {
				if (password.equals(rs.getString(2))) {
					String phoneNumber = rs.getString(18);
					String displayName = rs.getString(3);
					String firstName = rs.getString(4);
					String lastName = rs.getString(5);
					Date birthDay = rs.getDate(6);
					String emailAddress = rs.getString(7);
					String physicalAddress = "";
					String city = rs.getString(8);
					String postalCode = rs.getString(9);
					Country country = Country.valueOf(rs.getString(10));
					String bankCardNumber = rs.getString(11);
					Date expiratinDate = rs.getDate(12);
					PaymentMethod paymentMethod = PaymentMethod.valueOf(rs.getString(13).toUpperCase());
					String accountOwnerName = rs.getString(14);
					String billingCity = rs.getString(15);
					String billingPostalCode = rs.getString(16);
					Country billingCountry = Country.valueOf(rs.getString(17));
					String billingPhoneNumber = rs.getString(18);
					Gender gender = Gender.values()[Integer.parseInt(rs.getString(19))];
					String playListLink = rs.getString(20);

					// New Premium User
					PremiumUser premiumUser = new PremiumUser(userName, displayName, password, firstName, lastName,
							birthDay, emailAddress, physicalAddress, city, postalCode, country, gender, phoneNumber,
							bankCardNumber, expiratinDate, paymentMethod, accountOwnerName, physicalAddress,
							billingCity, billingPostalCode, billingCountry, billingPhoneNumber);
					SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
				} else {
					warningLabel.setText("Invalid username or password!!");
				}
			}
		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing login query. Please try again.");
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	// public void logInUser(String userName, String password, ActionEvent
	// event, Label warningLabel) {
	// try {
	// DatabaseMetaData metaData = connection.getMetaData();
	// ResultSet rs = metaData.getTables(null, null, "%", null);
	// String tableNames = "";
	// while (rs.next()) {
	// tableNames = tableNames + " " + rs.getString(3);
	// }
	// rs = statement.executeQuery("SELECT user_name FROM administrator WHERE
	// user_name='" + userName + "'"
	// + " UNION ALL SELECT user_name FROM trial_user UNION ALL SELECT user_name
	// FROM premium_user WHERE user_name='"
	// + userName + "'");
	// if (rs.next()) {
	// if (password.equals(rs.getString(2))) {
	//
	// }
	// }
	// } catch (SQLException e) {
	//
	// }
	// }

	public void checkTrialUserName(String userName, Label warningLabel, ActionEvent event) {
		try {
			ResultSet rs = statement.executeQuery("SELECT user FROM user_link WHERE user='" + userName + "'");
			if (rs.next()) {
				warningLabel.setText("Username is already taken");
				warningLabel.setVisible(false);
			} else {
				SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
			}
		} catch (SQLException ex) {
			DialogBoxManager.errorDialogBox("Cannot run query", "Error on User Name. Please try again.");
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public void checkPremiumlUserName(String userName, Label warningLabel, ActionEvent event) {
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

	public String getUrlOfDatabase() {
		return urlOfDatabase;
	}

	public User getUser() {
		return user;
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

}
