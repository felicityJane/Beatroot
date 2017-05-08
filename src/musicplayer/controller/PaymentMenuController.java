package musicplayer.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PaymentMenuController implements Initializable{


    @FXML private TextField cardHolderName,bankCardNumber,phoneNumber,billingAddress,billingCity,
    billingPostalCode;
    @FXML private Button getAddress,confirmPayment;
    @FXML private ComboBox<String> countryBox;
    @FXML private ComboBox<Integer>monthBox,yearBox;
    @FXML private RadioButton visaButton,masterButton;
    @FXML private Label warningLabel;
    @FXML private MainMenuController mainMenuController;
    @FXML private AnchorPane paymentParentAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainMenuController.init(this);
        mainMenuController.menuBarFitToParent(paymentParentAnchorPane);

        String []locales= Locale.getISOCountries();
        for (String countryList : locales) {
            Locale obj = new Locale("",countryList);
            ObservableList<String> list= FXCollections.observableArrayList();
            list.add(obj.getDisplayCountry());
            for (int i=0; i<list.size(); i++) {
                countryBox.getItems().add(list.get(i));
                countryBox.setValue("Sverige");
            }
        }
        for (int m=1; m<13; m++){
            monthBox.getItems().addAll(m);
            monthBox.setValue(1);
        }
        for (int y=2017; y<2030; y++){
            yearBox.getItems().addAll(y);
            yearBox.setValue(2017);
        }

    }
    @FXML
    private void handlePaymentButton(ActionEvent event)throws Exception{
        try {
            String cardHolder=cardHolderName.getText();
            long bankCardNum=Long.parseLong(bankCardNumber.getText());
            String phoneNum=phoneNumber.getText();
            String billingAdd=billingAddress.getText();
            String billingCit=billingCity.getText();
            String billingPostalCod=billingPostalCode.getText();
            String country=countryBox.getValue();
            String cardType="";
            String nameRegex="[A-Za-z]+";
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
            }if (!billingCit.matches(nameRegex)){
                warningLabel.setText("Invalid Value");
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date convertedCurrentDate = sdf.parse(yearBox.getValue().toString()+"-"+monthBox.getValue().toString());
            String expritationDate=sdf.format(convertedCurrentDate );
            System.out.println(expritationDate);

          DB_Connector connector=new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
            connector.insert("premium(bank_card_number, expiration_date,card_type,billing_account_owner_name, billing_city, billing_postal_code,billing_country,billing_phone_number)", "'"+bankCardNum+"','"+expritationDate+"','"+cardType+"','"+cardHolder+"','"+billingCit+"','"+billingPostalCod+"','"+country+"','"+phoneNum+"')");

        }catch (InputMismatchException ie){
            System.out.println(ie.toString());

        } catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Invalid Value");
            e.printStackTrace();
        }

    }

}