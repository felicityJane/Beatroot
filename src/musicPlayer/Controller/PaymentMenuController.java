package musicPlayer.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
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

        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to welcome scene");
            e.printStackTrace();
        }

    }

}