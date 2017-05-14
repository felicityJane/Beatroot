package musicplayer.controller;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.Country;

public class SignUpMenuController implements Initializable {

	@FXML
	private TextField userName;
	@FXML
	private TextField userPassword;
	@FXML
	private TextField firstName, lastName, email, confirmEmail, phoneNumber, physicalAddress, postalCode, city;
	@FXML
	private RadioButton male, female, trialUser, premiumUser;
	@FXML
	private Button signUpButton;
	@FXML
	private Label warningText;
	@FXML
	private Label lblLogIn;
	@FXML
	private AnchorPane signUpParentAnchorPane;
	@FXML
	private MainMenuController mainMenuController;
	@FXML
	private ComboBox<String> countryBox;
	@FXML
	private ComboBox<Integer> dayBox, monthBox, yearBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainMenuController.init(this);
		mainMenuController.menuBarFitToParent(signUpParentAnchorPane);
		lblLogIn.setOnMouseEntered(event -> {

			Scene scene = lblLogIn.getScene();
			scene.setCursor(Cursor.HAND);
		});

		lblLogIn.setOnMouseExited(event -> {

			Scene scene = lblLogIn.getScene();
			scene.setCursor(Cursor.DEFAULT);
		});

		for (int d = 1; d < 32; d++) {
			dayBox.getItems().addAll(d);
			dayBox.setValue(1);
		}
		for (int m = 1; m < 13; m++) {
			monthBox.getItems().addAll(m);
			monthBox.setValue(1);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int y = year; y > year - 100; y--) {
			yearBox.getItems().addAll(y);
			yearBox.setValue(2017);
		}

		for (Country country : Country.values()) {
			countryBox.getItems().addAll(country.name());
			countryBox.setValue("Sverige");
		}
	}

	@FXML
	private void handleSignUpButton(ActionEvent event) throws Exception {
		try {

			String userNam = userName.getText();
			String userPass = userPassword.getText();
			String firstNam = firstName.getText().toLowerCase();
			String lastNam = lastName.getText().toLowerCase();
			String phoneNum = phoneNumber.getText();
			String physicalAdd = physicalAddress.getText().toLowerCase();
			String postalCo = postalCode.getText();
			String cit = city.getText().toLowerCase();
			String emal = email.getText();
			String userType = null;
			String country = countryBox.getValue();
			String numberRegex = "[0-9]+";
			String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+(.+[a-zA-Z0-9-.]+)+$";
			String nameRegex = "[A-Za-z]+";
			String genderId = null;

			if (userName.getText().length() < 4 || userName.getText().length() > 10) {
				warningText.setText("Invalid Username Value");
				userName.clear();
				throw new InputMismatchException();
			}
			if (userPassword.getText().length() < 6 || userPassword.getText().length() > 12) {
				warningText.setText("Invalid Password Value");
				userPassword.clear();
				throw new InputMismatchException();
			}
			if (!firstNam.matches(nameRegex)) {
				warningText.setText("Invalid Name");
				firstName.clear();
				throw new InputMismatchException();
			}
			if (!lastNam.matches(nameRegex)) {
				warningText.setText("Invalid Name");
				lastName.clear();
				throw new InputMismatchException();
			}
			if (!emal.matches(emailRegex)) {
				warningText.setText("Invalid Mail Address");
				email.clear();
				throw new InputMismatchException();
			}
			if (!confirmEmail.getText().matches(emal)) {
				warningText.setText("Invalid Mail Address");
				confirmEmail.clear();
				throw new InputMismatchException();
			}
			if (male.isSelected()) {
				genderId = "1";
			}
			if (female.isSelected()) {
				genderId = "2";
			}
			if (!female.isSelected() && !male.isSelected()) {
				genderId = "3";
			}
			if (!phoneNum.matches(numberRegex)) {
				warningText.setText("Invalid phone number");
				phoneNumber.clear();
				throw new InputMismatchException();
			}
			if (!postalCo.matches(numberRegex)) {
				warningText.setText("Invalid postal code");
				postalCode.clear();
				throw new InputMismatchException();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date convertedCurrentDate = sdf.parse(yearBox.getValue().toString() + "-" + monthBox.getValue().toString()
					+ "-" + dayBox.getValue().toString());
			String dateOfBirht = sdf.format(convertedCurrentDate);

			String trialMounth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 2);
			Date convertedTrialEndDate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + trialMounth + "-"
					+ Calendar.getInstance().get(Calendar.DATE));
			String freeTrialEndDate = sdf.format(convertedTrialEndDate);

			DB_Connector connector = new DB_Connector(
					"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
			if (trialUser.isSelected()) {
				userType = trialUser.getText();

				connector.checkTrialUserName(userNam, warningText, event);
				if (warningText.getText().isEmpty()) {
					connector.insert("user_link(user)", "('" + userNam + "')");
					connector.insert(
							"trial_user(user_name, password,display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, free_trial_end_date, gender_gender_id, playlist_link)",
							"('" + userNam + "','" + userPass + "','" + userNam + "','" + firstNam + "','" + lastNam
									+ "','" + dateOfBirht + "','" + emal + "','" + physicalAdd + "','" + cit + "','"
									+ postalCo + "','" + country + "','" + freeTrialEndDate + "','" + genderId + "','"
									+ userNam + "')");
					connector.logInTrial(userNam, userPass, event, warningText);
					warningText.setText("");

				}
				if (!warningText.getText().isEmpty()) {
					DialogBoxManager.errorDialogBox("Error occurred", "Username is already taken!!");
					warningText.setText("");
				}
				// create user
			}
			if (premiumUser.isSelected()) {
				userType = premiumUser.getText();
				connector.checkPremiumUserName(userNam, warningText, event);

			}
			Path path = Paths.get("PremiumUserInfo.bin");
			ArrayList<String> premiumUserInfo = new ArrayList<>();
			try {
				premiumUserInfo.add(0, userNam);
				premiumUserInfo.add(1, userPass);
				premiumUserInfo.add(2, userNam);
				premiumUserInfo.add(3, firstNam);
				premiumUserInfo.add(4, lastNam);
				premiumUserInfo.add(5, dateOfBirht);
				premiumUserInfo.add(6, emal);
				premiumUserInfo.add(7, physicalAdd);
				premiumUserInfo.add(8, cit);
				premiumUserInfo.add(9, postalCo);
				premiumUserInfo.add(10, country);
				premiumUserInfo.add(11, genderId);
				premiumUserInfo.add(12, userNam);
				premiumUserInfo.add(13, phoneNum);

				Files.write(path, premiumUserInfo, StandardOpenOption.CREATE);

			} catch (Exception e) {
				DialogBoxManager.errorDialogBox("Error occurred", "Premium User Info");
				System.out.println(e.toString());
			}

		} catch (InputMismatchException ie) {
			ie.printStackTrace();

		} catch (Exception e) {
			DialogBoxManager.errorDialogBox("Error occurred", "Changing from sign up scene to welcome scene");
			e.printStackTrace();
		}
	}

	@FXML
	private void clickOnLogInLabel(MouseEvent me) {
		try {
			SceneManager.sceneManager.changeScene(me, "view/logInMenu.fxml");

		} catch (Exception e) {
			DialogBoxManager.errorDialogBox("Error occurred", "Changing from sign up scene to log in scene");
			e.printStackTrace();
		}
	}
}