package musicplayer;
/**
 * Created by felic on 19/04/2017.
 */
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class DialogBoxManager {

    public static void errorDialogBox( String header, String context){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Error");
        dialog.setHeaderText(header);
        dialog.setContentText(context);
        dialog.showAndWait();
    }
    public static boolean confirmationDialogBox( String header, String context){
        boolean okButton = false;
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Confirmation");
        dialog.setHeaderText(header);
        dialog.setContentText(context);
        Optional<ButtonType> result = dialog.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
             okButton = true;
        }
        return okButton;
    }
    public static void notificationDialogBox( String header, String context){
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Confirmation");
        dialog.setHeaderText(header);
        dialog.setContentText(context);
        dialog.showAndWait();
    }
}