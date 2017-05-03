package musicplayer.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;

import java.net.URL;
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
    private String cardType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String []locales= Locale.getISOCountries();
        for (String countryList : locales) {
            Locale obj = new Locale("",countryList);

            //System.out.println(obj.getDisplayCountry());
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
            long bankCardNum=Long.valueOf(bankCardNumber.getText());
            Integer phoneNum=Integer.valueOf(phoneNumber.getText());
            String billingAdd=billingAddress.getText();
            String billingCit=billingCity.getText();
            Integer billingPostalCod=Integer.valueOf(billingPostalCode.getText());

            String nameRegex="[A-Za-z]+";
            if (!cardHolder.matches(nameRegex)) {
                warningLabel.setText("ddddd");
                cardHolderName.clear();
                throw new Exception();

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
            }

          DB_Connector connector=new DB_Connector("jdbc:mysql://127.0.0.1:3306/mydb?user=root&password=root&useSSL=false");


        }catch (InputMismatchException ie){
            System.out.println(ie.toString());

        } catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to welcome scene");
            e.printStackTrace();
        }

    }

}