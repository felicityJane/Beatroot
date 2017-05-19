package musicplayer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.Administrator;
import musicplayer.model.Country;
import musicplayer.model.Gender;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminSignUpController implements Initializable {

	@FXML
	private DatePicker dateOfBirthPicker, startDatePicker;
	@FXML
	private ChoiceBox<Country> countryChoiceBox;
	@FXML
	private RadioButton maleRadio, femaleRadio, otherRadio;
	@FXML
	private Button cancelButton, registerButton;
	@FXML
	private TextField userNameField, displayNameField, passwordField, firstNameField, lastNameField, emailAddressField,
			physicalAddressField, cityOfResidenceField, postalCodeField, phoneNumberField, wageField,
			contractHoursField, staffIdField;
	// TODO @FXML
	private Label warningText;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		// countryChoiceBox.setValue("Country");
		String pattern = "yyyy/MM/dd";
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
		dateOfBirthPicker.setConverter(converter);
		dateOfBirthPicker.setPromptText(pattern.toLowerCase());
		startDatePicker.setConverter(converter);
		startDatePicker.setPromptText(pattern.toLowerCase());

		countryChoiceBox.getItems().setAll(Country.values());
		countryChoiceBox.setValue(Country.SELECT_COUNTRY);
	}

	public void registerButtonPressed(ActionEvent event) {
		try {
			DB_Connector connector = new DB_Connector(
					"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
			if (connector.checkUserName(userNameField.getText(), warningText, event) == false) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// String birthDayString =
				// dateOfBirthPicker.getValue().toString();
				// String startDayString =
				// startDatePicker.getValue().toString();
				// // System.out.println(birthDayString + startDayString);
				Date birthDate = sdf.parse(dateOfBirthPicker.getValue().toString());
				Date employmentStartDate = sdf.parse(startDatePicker.getValue().toString());
				// DateFormat outputFormatter = new
				// SimpleDateFormat("yyyy/MM/dd");
				float wage = Float.parseFloat(wageField.getText());
				float workHours = Float.parseFloat(contractHoursField.getText());
				if (maleRadio.isSelected()) {
					Administrator admin = new Administrator(staffIdField.getText(), userNameField.getText(),
							displayNameField.getText(), passwordField.getText(), firstNameField.getText(),
							lastNameField.getText(), birthDate, emailAddressField.getText(),
							physicalAddressField.getText(), cityOfResidenceField.getText(), postalCodeField.getText(),
							countryChoiceBox.getValue(), Gender.MALE, phoneNumberField.getText(), employmentStartDate,
							wage, workHours);
					connector.insert("user_link(user)", "('" + admin.getStaffID() + "')");
					connector.insert("playlist(playlist_owner)", "('" + admin.getStaffID() + "')");
					connector.insert(
							"administrator(staff_id, user_name, password, display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, phone_number, start_date, wage, contract_hours, gender_gender_id, playlist_link)",
							"('" + admin.getStaffID() + "', '" + admin.getUserName() + "','" + admin.getPassword()
									+ "','" + admin.getDisplayName() + "','" + admin.getFirstName() + "','"
									+ admin.getLastName() + "','" + sdf.format(admin.getDateOfBirth()) + "','"
									+ admin.getEmailAddress() + "','"
									+ admin.getPhysicalAddress().getStreetNameAndNumber() + "','"
									+ admin.getCityOfResidence() + "','" + admin.getPostalCode() + "','"
									+ admin.getCountry() + "','" + admin.getPhoneNumber() + "','"
									+ sdf.format(admin.getStartDate()) + "','" + admin.getWage() + "','"
									+ admin.getContractHours() + "','" + admin.getGender().ordinal() + "','"
									+ admin.getStaffID() + "')");
					// connector.insert(("playlist()");
					// System.out.println("('" + admin.getStaffID() + "', '" +
					// admin.getUserName() + "','"
					// + admin.getPassword() + "','" + admin.getDisplayName() +
					// "','" + admin.getFirstName()
					// + "','" + admin.getLastName() + "','" +
					// sdf.format(admin.getDateOfBirth()) + "','"
					// + admin.getEmailAddress() + "','" +
					// admin.getPhysicalAddress().getStreetNameAndNumber()
					// + "','" + admin.getCityOfResidence() + "','" +
					// admin.getPostalCode() + "','"
					// + admin.getCountry() + "','" + admin.getPhoneNumber() +
					// "','"
					// + sdf.format(admin.getStartDate()) + "','" +
					// admin.getWage() + "','"
					// + admin.getContractHours() + "','" +
					// admin.getGender().ordinal() + "','"
					// + admin.getStaffID() + "')");
					DialogBoxManager.confirmationDialogBox("Registration successful",
							"The user " + admin.getUserName() + " has been successfully registered");
					SceneManager.sceneManager.changeScene(event, "view/AdminLogIn.fxml");
				}
				// 23:42:44 insert into administrator(staff_id, user_name,
				// password, display_name, first_name, last_name, date_of_birth,
				// email_address, physical_address, city_of_residence,
				// postal_code, country, phone_number, start_date, wage,
				// contract_hours, gender_gender_id, playlist_link) values
				// ('Psychosneeded',
				// 'ViktorSN','3463d','ViktorSN','Viktor','Nikolov','1991-06-13','viktors.nikolov@gmail.com','Festningsgatan
				// 2','Kstad','291 34','Sverige','+46
				// 737181299','2017-05-12','12000.0','32.0','0','ViktorSN')
				// Error Code: 1452. Cannot add or update a child row: a foreign
				// key constraint fails (`beatroot`.`administrator`, CONSTRAINT
				// `fk_administrator_user_link1` FOREIGN KEY (`playlist_link`)
				// REFERENCES `user_link` (`user`) ON DELETE NO ACTION ON UPDATE
				// NO ACTION) 0.015 sec

				if (femaleRadio.isSelected()) {
					Administrator admin = new Administrator(staffIdField.getText(), userNameField.getText(),
							displayNameField.getText(), passwordField.getText(), firstNameField.getText(),
							lastNameField.getText(), birthDate, emailAddressField.getText(),
							physicalAddressField.getText(), cityOfResidenceField.getText(), postalCodeField.getText(),
							countryChoiceBox.getValue(), Gender.FEMALE, phoneNumberField.getText(), employmentStartDate,
							wage, workHours);
					connector.insert("user_link(user)", "('" + admin.getStaffID() + "')");
					connector.insert("playlist(playlist_owner)", "('" + admin.getStaffID() + "')");
					connector.insert(
							"administrator(staff_id, user_name, password, display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, phone_number, start_date, wage, contract_hours, gender_gender_id, playlist_link)",
							"('" + admin.getStaffID() + "', '" + admin.getUserName() + "','" + admin.getPassword()
									+ "','" + admin.getDisplayName() + "','" + admin.getFirstName() + "','"
									+ admin.getLastName() + "','" + sdf.format(admin.getDateOfBirth()) + "','"
									+ admin.getEmailAddress() + "','"
									+ admin.getPhysicalAddress().getStreetNameAndNumber() + "','"
									+ admin.getCityOfResidence() + "','" + admin.getPostalCode() + "','"
									+ admin.getCountry() + "','" + admin.getPhoneNumber() + "','"
									+ sdf.format(admin.getStartDate()) + "','" + admin.getWage() + "','"
									+ admin.getContractHours() + "','" + admin.getGender().ordinal() + "','"
									+ admin.getStaffID() + "')");
					DialogBoxManager.confirmationDialogBox("Registration successful",
							"The user " + admin.getUserName() + " has been successfully registered");
					SceneManager.sceneManager.changeScene(event, "view/AdminLogIn.fxml");
				}
				if (otherRadio.isSelected()) {
					Administrator admin = new Administrator(staffIdField.getText(), userNameField.getText(),
							displayNameField.getText(), passwordField.getText(), firstNameField.getText(),
							lastNameField.getText(), birthDate, emailAddressField.getText(),
							physicalAddressField.getText(), cityOfResidenceField.getText(), postalCodeField.getText(),
							countryChoiceBox.getValue(), Gender.NOT_SPECIFIED, phoneNumberField.getText(),
							employmentStartDate, wage, workHours);
					connector.insert("user_link(user)", "('" + admin.getStaffID() + "')");
					connector.insert("playlist(playlist_owner)", "('" + admin.getStaffID() + "')");
					connector.insert(
							"administrator(staff_id, user_name, password, display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, phone_number, start_date, wage, contract_hours, gender_gender_id, playlist_link)",
							"('" + admin.getStaffID() + "', '" + admin.getUserName() + "','" + admin.getPassword()
									+ "','" + admin.getDisplayName() + "','" + admin.getFirstName() + "','"
									+ admin.getLastName() + "','" + sdf.format(admin.getDateOfBirth()) + "','"
									+ admin.getEmailAddress() + "','"
									+ admin.getPhysicalAddress().getStreetNameAndNumber() + "','"
									+ admin.getCityOfResidence() + "','" + admin.getPostalCode() + "','"
									+ admin.getCountry() + "','" + admin.getPhoneNumber() + "','"
									+ sdf.format(admin.getStartDate()) + "','" + admin.getWage() + "','"
									+ admin.getContractHours() + "','" + admin.getGender().ordinal() + "','"
									+ admin.getStaffID() + "')");
					DialogBoxManager.confirmationDialogBox("Registration successful",
							"The user " + admin.getUserName() + " has been successfully registered");
					SceneManager.sceneManager.changeScene(event, "view/AdminLogIn.fxml");
				}
			} else {
				DialogBoxManager.errorDialogBox("Registration error", "Username is already taken");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancelButtonPressed(ActionEvent e) {
		try {
			SceneManager.sceneManager.changeScene(e, "view/AdminLogIn.fxml");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
// =======
// package musicplayer.controller;
//
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.*;
// import javafx.util.StringConverter;
// import musicplayer.DB_Connector;
// import musicplayer.model.Administrator;
// import musicplayer.model.Country;
// import musicplayer.model.Gender;
//
// import java.net.URL;
// import java.text.SimpleDateFormat;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.Date;
// import java.util.ResourceBundle;
//
// public class AdminSignUpController implements Initializable {
//
// @FXML
// private DatePicker dateOfBirthPicker, startDatePicker;
// @FXML
// private ChoiceBox<Country> countryChoiceBox;
// @FXML
// private RadioButton maleRadio, femaleRadio, otherRadio;
// @FXML
// private Button cancelButton, registerButton;
// @FXML
// private TextField userNameField, displayNameField, passwordField,
// firstNameField, lastNameField, emailAddressField,
// physicalAddressField, cityOfResidenceField, postalCodeField,
// phoneNumberField, wageField,
// contractHoursField, staffIdField;
// // TODO @FXML
// private Label warningText;
//
// /*
// * (non-Javadoc)
// *
// * @see javafx.fxml.Initializable#initialize(java.net.URL,
// * java.util.ResourceBundle)
// */
// @Override
// public void initialize(URL url, ResourceBundle resources) {
// // countryChoiceBox.setValue("Country");
// String pattern = "yyyy/MM/dd";
// StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
// DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
//
// @Override
// public String toString(LocalDate date) {
// if (date != null) {
// return dateFormatter.format(date);
// } else {
// return "";
// }
// }
//
// @Override
// public LocalDate fromString(String string) {
// if (string != null && !string.isEmpty()) {
// return LocalDate.parse(string, dateFormatter);
// } else {
// return null;
// }
// }
// };
// dateOfBirthPicker.setConverter(converter);
// dateOfBirthPicker.setPromptText(pattern.toLowerCase());
// startDatePicker.setConverter(converter);
// startDatePicker.setPromptText(pattern.toLowerCase());
//
// countryChoiceBox.getItems().setAll(Country.values());
// countryChoiceBox.setValue(Country.SELECT_COUNTRY);
// // countryChoiceBox.setValue("Country");
// // for (int i = 0; i < Country.values().length; i++) {
// // countryMenuButton.getItems().addAll((MenuItem)
// // FXCollections.observableArrayList(new Countr);
// // }
// }
//
// public void signUpButonPressed(ActionEvent event) {
// try {
// SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
// Date birthDate = sdf.parse(dateOfBirthPicker.getValue().toString());
// Date employmentStartDate = sdf.parse(startDatePicker.getValue().toString());
// float wage = Float.parseFloat(wageField.getText());
// float workHours = Float.parseFloat(contractHoursField.getText());
// DB_Connector connector = new DB_Connector(
// "jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
// if (maleRadio.isSelected()) {
// connector.checkUserName(userNameField.getText(), warningText, event);
// Administrator admin = new Administrator(userNameField.getText(),
// displayNameField.getText(),
// passwordField.getText(), firstNameField.getText(), lastNameField.getText(),
// birthDate,
// emailAddressField.getText(), physicalAddressField.getText(),
// cityOfResidenceField.getText(),
// postalCodeField.getText(), countryChoiceBox.getValue(), Gender.MALE,
// phoneNumberField.getText(),
// employmentStartDate, wage, workHours, staffIdField.getText());
//
// }
// if (femaleRadio.isSelected()) {
// Administrator admin = new Administrator(userNameField.getText(),
// displayNameField.getText(),
// passwordField.getText(), firstNameField.getText(), lastNameField.getText(),
// birthDate,
// emailAddressField.getText(), physicalAddressField.getText(),
// cityOfResidenceField.getText(),
// postalCodeField.getText(), countryChoiceBox.getValue(), Gender.FEMALE,
// phoneNumberField.getText(), employmentStartDate, wage, workHours,
// staffIdField.getText());
// }
// if (otherRadio.isSelected()) {
// Administrator admin = new Administrator(userNameField.getText(),
// displayNameField.getText(),
// passwordField.getText(), firstNameField.getText(), lastNameField.getText(),
// birthDate,
// emailAddressField.getText(), physicalAddressField.getText(),
// cityOfResidenceField.getText(),
// postalCodeField.getText(), countryChoiceBox.getValue(), Gender.NOT_SPECIFIED,
// phoneNumberField.getText(), employmentStartDate, wage, workHours,
// staffIdField.getText());
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }
// >>>>>>> branch 'master' of https://github.com/felicityJane/Beatroot.git
