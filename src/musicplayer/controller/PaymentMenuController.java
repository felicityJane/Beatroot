package musicplayer.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.model.Country;
import musicplayer.model.GlobalVariables;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class PaymentMenuController implements Initializable{


    @FXML private TextField cardHolderName,bankCardNumber,phoneNumber,billingAddress,billingCity,
    billingPostalCode;
    @FXML private Button getAddress,confirmPayment;
    @FXML private ComboBox<String> countryBox;
    @FXML private ComboBox<Integer>monthBox,yearBox;
    @FXML private RadioButton visaButton,masterButton;
    @FXML private Label warningLabel;
    @FXML private AnchorPane paymentParentAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        globalVariables.setPaymentMenuController(this);
        globalVariables.getMainMenuController().menuBarFitToParent(paymentParentAnchorPane);

        for (Country country: Country.values()) {
            countryBox.getItems().addAll(country.name());
            countryBox.setValue("Sverige");
        }
        for (int m=1; m<13; m++){
            monthBox.getItems().addAll(m);
            monthBox.setValue(1);
        }
        int year= Calendar.getInstance().get(Calendar.YEAR);
        for (int y=year; y<year+20; y++){
            yearBox.getItems().addAll(y);
            yearBox.setValue(2017);
        }

    }
    @FXML
    private void handleGetAddressButton(ActionEvent event) throws Exception{
        try {
            Path path = Paths.get("PremiumUserInfo.bin");
            ArrayList<String> premiumUserInfo = (ArrayList<String>) Files.readAllLines(path);
            phoneNumber.setText(premiumUserInfo.get(13));
            billingAddress.setText(premiumUserInfo.get(7));
            billingCity.setText(premiumUserInfo.get(8));
            billingPostalCode.setText(premiumUserInfo.get(9));
            countryBox.setValue(premiumUserInfo.get(10));
        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","GetAddress");
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePaymentButton(ActionEvent event)throws Exception{
        try {
            Path path = Paths.get("PremiumUserInfo.bin");
            ArrayList<String> premiumUserInfo = (ArrayList<String>) Files.readAllLines(path);

            String userName=premiumUserInfo.get(0);
            String password=premiumUserInfo.get(1);
            String displayName=premiumUserInfo.get(2);
            String firstName=premiumUserInfo.get(3);
            String lastName=premiumUserInfo.get(4);
            String birthDay=premiumUserInfo.get(5);
            String email=premiumUserInfo.get(6);
            String cityOfResidence=premiumUserInfo.get(8);
            String postalCode=premiumUserInfo.get(9);
            String country=countryBox.getValue();
            String genderId=premiumUserInfo.get(11);
            String playListLink=premiumUserInfo.get(12);
            String cardHolder=cardHolderName.getText().toLowerCase();
            long bankCardNum=Long.parseLong(bankCardNumber.getText());
            String phoneNum=phoneNumber.getText();
            String billingAdd=billingAddress.getText().toLowerCase();
            String billingCit=billingCity.getText().toLowerCase();
            String billingPostalCod=billingPostalCode.getText();
            String cardType="";
            String nameRegex="[A-Za-zöäå]+$";
            String numberRegex="[0-9]+";

            if (!cardHolder.matches(nameRegex)) {
                warningLabel.setText("Invalid Name");
                cardHolderName.clear();
                throw new InputMismatchException();
            }if (bankCardNumber.getText().length()!=16 ) {
                warningLabel.setText("Invalid Card Number!!");
                bankCardNumber.clear();
                throw new InputMismatchException();
            }if (visaButton.isSelected()){
                cardType=visaButton.getText();
            }if (masterButton.isSelected()){
                cardType=masterButton.getText();
            }if (!visaButton.isSelected() && !masterButton.isSelected()){
                warningLabel.setText("Card type is not selected!!");
                throw new InputMismatchException();
            }if (!billingCity.getText().matches(nameRegex)){
                warningLabel.setText("Invalid City Name");
                billingCity.clear();
                throw new InputMismatchException();
            }if (!phoneNum.matches(numberRegex)){
                warningLabel.setText("Invalid phone number");
                phoneNumber.clear();
                throw new InputMismatchException();
            }if (!billingPostalCod.matches(numberRegex)) {
                warningLabel.setText("Invalid postal code");
                billingPostalCode.clear();
                throw new InputMismatchException();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedCurrentDate = sdf.parse(yearBox.getValue()+"-"+monthBox.getValue()+1+"-01");
            String expritationDate=sdf.format(convertedCurrentDate );



          DB_Connector connector=new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
            connector.insert("user_link(user)","('"+userName+"')");
            connector.insert("premium_user(user_name, password,display_name, first_name, last_name, date_of_birth, email_address, city_of_residence, postal_code, country, bank_card_number, expiration_date, card_type, billing_account_owner_name, billing_city, billing_postal_code,billing_country,billing_phone_number, gender_gender_id, playlist_link)", "('"+userName+"','"+password+"','"+displayName+"','"+firstName+"','"+lastName+"','"+birthDay+"','"+email+"','"+cityOfResidence+"','"+postalCode+"','"+country+"','"+bankCardNum+"','"+expritationDate+"','"+cardType+"','"+cardHolder+"','"+billingCit+"','"+billingPostalCod+"','"+country+"','"+phoneNum+"','"+genderId+"','"+playListLink+"')");
            connector.logInPremium(userName,password,event,warningLabel);

        }catch (InputMismatchException ie){
            System.out.println(ie.toString());

        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Invalid Value");
            e.printStackTrace();
        }

    }

}