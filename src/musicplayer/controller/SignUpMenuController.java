package musicplayer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.Country;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class SignUpMenuController implements Initializable{

@FXML private TextField userName;
@FXML private TextField userPassword;
@FXML private TextField firstName,lastName,email,confirmEmail,phoneNumber,physicalAddress,postalCode,city;
@FXML private RadioButton male,female,trialUser,premiumUser;
@FXML private Button signUpButton;
@FXML private Label warningText;
@FXML private Label lblLogIn;
@FXML private AnchorPane signUpParentAnchorPane;
@FXML private MainMenuController mainMenuController;
@FXML private ComboBox<String> countryBox;
@FXML private ComboBox<Integer> dayBox,monthBox,yearBox;

@Override
public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.menuBarFitToParent(signUpParentAnchorPane);
        lblLogIn.setOnMouseEntered(event ->  {

        Scene scene = lblLogIn.getScene();
        scene.setCursor(Cursor.HAND);
        });

        lblLogIn.setOnMouseExited(event -> {

        Scene scene = lblLogIn.getScene();
        scene.setCursor(Cursor.DEFAULT);
        });

        for (int d=1; d<32; d++){
        dayBox.getItems().addAll(d);
        dayBox.setValue(1);
        }
        for (int m=1; m<13; m++){
        monthBox.getItems().addAll(m);
        monthBox.setValue(1);
        }
        int year= Calendar.getInstance().get(Calendar.YEAR);
        for (int y=year; y>year-100; y--){
        yearBox.getItems().addAll(y);
        yearBox.setValue(2017);
        }

    for (Country country: Country.values()) {
        countryBox.getItems().addAll(country.name());
        countryBox.setValue("Sverige");
        }
    }
@FXML
private void handleSignUpButton(ActionEvent event) throws Exception{
        try {

        String userNam=userName.getText();
        String userPass=userPassword.getText();
        String firstNam=firstName.getText();
        String lastNam=lastName.getText();
        String phoneNum=phoneNumber.getText();
        String physicalAdd=physicalAddress.getText();
        String postalCo=postalCode.getText();
        String cit=city.getText();
        String emal=email.getText();
        String userType=null;
        String country=countryBox.getValue();
        String numberRegex="[0-9]+";
        String emailRegex ="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+(.+[a-zA-Z0-9-.]+)+$";
        String nameRegex="[A-Za-z]+";
        String tableName="trial_user";
        String genderId = null;

        if (userName.getText().length()<4 || userName.getText().length()>8){
        warningText.setText("Invalid Username Value");
        userName.clear();
        throw new InputMismatchException();
        }if (userPassword.getText().length()<6 || userPassword.getText().length()>12){
        warningText.setText("Invalid Password Value");
        userPassword.clear();
        throw  new InputMismatchException();
        }if (!firstNam.matches(nameRegex) ){
        warningText.setText("Invalid Name");
        firstName.clear();
        throw  new InputMismatchException();
        }if (!lastNam.matches(nameRegex) ) {
        warningText.setText("Invalid Name");
        lastName.clear();
        throw new InputMismatchException();
        }if (!emal.matches(emailRegex)){
        warningText.setText("Invalid Mail Address");
        email.clear();
        throw new InputMismatchException();
        }if (!confirmEmail.getText().matches(emal)){
        warningText.setText("Invalid Mail Address");
        confirmEmail.clear();
        throw new InputMismatchException();
        }if (male.isSelected()) {
        genderId ="0";
        }if (female.isSelected()){
        genderId="1";
        }if (!female.isSelected() && !male.isSelected()){
        warningText.setText("Gender is not selected!!");
        throw new InputMismatchException();
        }if (!phoneNum.matches(numberRegex)){
        warningText.setText("Invalid phone number");
        phoneNumber.clear();
        throw new InputMismatchException();
        }if (!postalCo.matches(numberRegex)) {
        warningText.setText("Invalid postal code");
        postalCode.clear();
        throw new InputMismatchException();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = sdf.parse(yearBox.getValue().toString()+"-"+monthBox.getValue().toString()+"-"+dayBox.getValue().toString());
        String dateOfBirht=sdf.format(convertedCurrentDate );
        String freeTrialEndDate=String.valueOf(Calendar.DATE);

            if (trialUser.isSelected()){
                userType=trialUser.getText();
                DB_Connector connector =new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");

                connector.checkUserName(userNam,warningText,event);
                connector.insert("user_link(user)","('"+userNam+"')");
                connector.insert(tableName+"(user_name, password,display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, free_trial_end_date, gender_gender_id, playlist_link)", "('"+userNam+"','"+userPass+"','"+userNam+"','"+firstNam+"','"+lastNam+"','"+dateOfBirht+"','"+emal+"','"+physicalAdd+"','"+cit+"','"+postalCo+"','"+country+"','"+dateOfBirht+"','"+genderId+"','"+userNam+"')");
                //create user
            }if (premiumUser.isSelected()){
                userType=premiumUser.getText();
            }

        }catch (InputMismatchException ie){
        System.out.println(ie.toString());

        }catch (Exception e){
        DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to welcome scene");
        System.out.println(e.toString());
        }

    }

@FXML
private void clickOnLogInLabel(MouseEvent me) {
        try {
        SceneManager.sceneManager.changeScene(me,"view/logInMenu.fxml");

        }catch (Exception e){
        DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to log in scene");
        e.printStackTrace();
        }
    }
}