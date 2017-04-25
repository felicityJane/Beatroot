package musicPlayer.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import musicPlayer.DialogBoxManager;
import java.net.URL;
import java.util.ResourceBundle;

public class MembershipMenuController implements Initializable{



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void handleGetFreeButton(){
        try {

        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to welcome scene");
            e.printStackTrace();
        }

    }

    @FXML
    private void handleGetPremiumButton() {
        try {


        }catch (Exception e){
            DialogBoxManager.errorDialogBox("Error occurred","Changing from sign up scene to log in scene");
            e.printStackTrace();
        }
    }
}