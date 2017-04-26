package musicPlayer.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import musicPlayer.DB_Connector;
import musicPlayer.DialogBoxManager;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentMenuController implements Initializable{


    @FXML private TextField cardHolderName,bankCardNumber,dateMonth,dateYear,phoneNumber,billingAddress,billingCity,
    billingPostalCode;
    @FXML private Button getAddress,confirmPayment;
    @FXML private MenuButton country;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void handleConfirmPaymentButton(){
        try {
            String cardHolder=cardHolderName.getText();
            Integer bankCardNum=Integer.valueOf(bankCardNumber.getText());
            Integer exMonth=Integer.valueOf(dateMonth.getText());
            Integer exYear=Integer.valueOf(dateYear.getText());
            Integer phoneNum=Integer.valueOf(phoneNumber.getText());
            String billingAdd=billingAddress.getText();
            String billingCit=billingCity.getText();
            Integer billingPostalCod=Integer.valueOf(billingPostalCode.getText());

            //country MenuButton

            DB_Connector connector=new DB_Connector("urlOfDatabase");
            connector.insert("UserPayment", cardHolder);
            connector.insert("UserPayment", String.valueOf(bankCardNum));
            connector.insert("UserPayment", String.valueOf(exMonth));
            connector.insert("UserPayment", String.valueOf(exYear));
            connector.insert("UserPayment", String.valueOf(phoneNum));
            connector.insert("UserPayment", billingAdd);
            connector.insert("UserPayment", billingCit);
            connector.insert("UserPayment", String.valueOf(billingPostalCod));


        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to welcome scene");
            e.printStackTrace();
        }

    }

}