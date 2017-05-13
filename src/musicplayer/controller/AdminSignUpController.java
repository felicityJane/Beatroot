package musicplayer.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import musicplayer.DB_Connector;
import musicplayer.model.Administrator;
import musicplayer.model.Country;
import musicplayer.model.Gender;

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
		// countryChoiceBox.setValue("Country");
		// for (int i = 0; i < Country.values().length; i++) {
		// countryMenuButton.getItems().addAll((MenuItem)
		// FXCollections.observableArrayList(new Countr);
		// }
	}

	public void signUpButonPressed(ActionEvent event) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date birthDate = sdf.parse(dateOfBirthPicker.getValue().toString());
			Date employmentStartDate = sdf.parse(startDatePicker.getValue().toString());
			float wage = Float.parseFloat(wageField.getText());
			float workHours = Float.parseFloat(contractHoursField.getText());
			DB_Connector connector = new DB_Connector(
					"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
			if (maleRadio.isSelected()) {
				connector.checkUserName(userNameField.getText(), warningText, event);
				Administrator admin = new Administrator(userNameField.getText(), displayNameField.getText(),
						passwordField.getText(), firstNameField.getText(), lastNameField.getText(), birthDate,
						emailAddressField.getText(), physicalAddressField.getText(), cityOfResidenceField.getText(),
						postalCodeField.getText(), countryChoiceBox.getValue(), Gender.MALE, phoneNumberField.getText(),
						employmentStartDate, wage, workHours, staffIdField.getText());

			}
			if (femaleRadio.isSelected()) {
				Administrator admin = new Administrator(userNameField.getText(), displayNameField.getText(),
						passwordField.getText(), firstNameField.getText(), lastNameField.getText(), birthDate,
						emailAddressField.getText(), physicalAddressField.getText(), cityOfResidenceField.getText(),
						postalCodeField.getText(), countryChoiceBox.getValue(), Gender.FEMALE,
						phoneNumberField.getText(), employmentStartDate, wage, workHours, staffIdField.getText());
			}
			if (otherRadio.isSelected()) {
				Administrator admin = new Administrator(userNameField.getText(), displayNameField.getText(),
						passwordField.getText(), firstNameField.getText(), lastNameField.getText(), birthDate,
						emailAddressField.getText(), physicalAddressField.getText(), cityOfResidenceField.getText(),
						postalCodeField.getText(), countryChoiceBox.getValue(), Gender.NOT_SPECIFIED,
						phoneNumberField.getText(), employmentStartDate, wage, workHours, staffIdField.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
