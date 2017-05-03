package musicplayer.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignUpMenuController implements Initializable{

    @FXML private TextField userName;
    @FXML private TextField userPassword;
    @FXML private TextField firstName,lastName,email,confirmEmail,phoneNumber,physicalAddress,postalCode,city;
    @FXML private RadioButton male,female,trialUser,premiumUser;
    @FXML private Button signUpButton;
    @FXML private Label lblLogIn, warningText;
    @FXML private MainMenuController mainMenuController;
    @FXML private ComboBox<String> countryBox;
    @FXML private ComboBox<Integer> dayBox,monthBox,yearBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
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
        for (int y=2017; y>1930; y--){
            yearBox.getItems().addAll(y);
            yearBox.setValue(2017);
        }

        String []locales= Locale.getISOCountries();
        for (String countryList : locales) {
            Locale obj = new Locale("",countryList);


            ObservableList<String>list= FXCollections.observableArrayList();
            list.add(obj.getDisplayCountry());
            for (int i=0; i<list.size(); i++) {
               countryBox.getItems().add(list.get(i));
               countryBox.setValue("Sverige");
            }
        }

    }
    @FXML
    private void handleSignUpButton(ActionEvent event) throws Exception{
        try {

            String userNam=userName.getText();
            String userPass=userPassword.getText();
            String firstNam=firstName.getText();
            String lastNam=lastName.getText();
            Integer phoneNum=Integer.valueOf(phoneNumber.getText());
            String physicalAdd=physicalAddress.getText();
            Integer postalCo=Integer.valueOf(postalCode.getText());
            String cit=city.getText();
            String emal=email.getText();
            String gender=null;
            String userType=null;
            String tableName = null;
            String country=countryBox.getValue();



            if (userName.getText().length()<4 || userName.getText().length()>8){
                warningText.setText("Invalid Username Value");
                userName.clear();
                throw new InputMismatchException();
            }
            if (userPassword.getText().length()<6 || userPassword.getText().length()>12){
                warningText.setText("Invalid Password Value");
                userPassword.clear();
                throw  new InputMismatchException();
            }
            String nameRegex="[A-Za-z]+";
            if (!firstNam.matches(nameRegex) ){
                warningText.setText("Invalid Name");
               firstName.clear();
                throw  new InputMismatchException();
            }
            if (!lastNam.matches(nameRegex) ) {
                warningText.setText("Invalid Name");
                lastName.clear();
                throw new InputMismatchException();
            }
            String emailRegex ="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+(.+[a-zA-Z0-9-.]+)+$";
            if (!emal.matches(emailRegex)){
                warningText.setText("Invalid Mail Address");
                email.clear();
                throw new InputMismatchException();
            }
            if (!confirmEmail.getText().matches(emal)){
                warningText.setText("Invalid Mail Address");
                confirmEmail.clear();
                throw new InputMismatchException();
            }
            if (male.isSelected()) {
                gender = male.getText();
            }
            if (female.isSelected()){
                gender=female.getText();
            }
            if (!female.isSelected() && !male.isSelected()){
                warningText.setText("Gender is not selected!!");
                throw new InputMismatchException();
            }
            if (trialUser.isSelected()){
                userType=trialUser.getText();
                tableName="trial_user";
            }
            if (premiumUser.isSelected()){
                userType=premiumUser.getText();
                tableName="premium_user";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedCurrentDate = sdf.parse(yearBox.getValue().toString()+"-"+monthBox.getValue().toString()+"-"+dayBox.getValue().toString());
            String dateOfBirht=sdf.format(convertedCurrentDate );

            DB_Connector connector=new DB_Connector("jdbc:mysql://127.0.0.1:3306/mydb?user=root&password=root&useSSL=false");

            //SceneManager.sceneManager.changeScene(event,"View/welcomeMenu.fxml");
            if (premiumUser.isSelected()){
                SceneManager.sceneManager.changeScene(event,"View/paymentMenu.fxml");
            }

            //change scene location name to pay scene possibly
            //SceneManager.sceneManager.changeScene(event,"View/welcomeMenu.fxml");

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
            SceneManager.sceneManager.changeScene(me,"View/logInMenu.fxml");
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to log in scene");
            e.printStackTrace();
        }
    }
}