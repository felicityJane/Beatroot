package musicplayer.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		// countryChoiceBox.setValue("Country");
		countryChoiceBox.getItems().setAll(Country.values());
		countryChoiceBox.setValue(Country.SELECT_COUNTRY);
		// countryChoiceBox.setValue("Country");
		// for (int i = 0; i < Country.values().length; i++) {
		// countryMenuButton.getItems().addAll((MenuItem)
		// FXCollections.observableArrayList(new Countr);
		// }
	}

	public void signUpButonPressed() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date birthDate = sdf.parse(dateOfBirthPicker.getValue().toString());
			Date employmentStartDate = sdf.parse(startDatePicker.getValue().toString());
			float wage = Float.parseFloat(wageField.getText());
			float workHours = Float.parseFloat(contractHoursField.getText());
			if (maleRadio.isSelected()) {
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
		}
	}
}
