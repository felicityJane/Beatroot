package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import musicplayer.DB_Connector;
import musicplayer.model.GlobalVariables;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Federica on 17/05/2017.
 */
public class FriendRequestWindowController implements Initializable{

    @FXML private Label lblDisplayNameAndText;
    @FXML private ImageView imgContactPicture;
    private ArrayList<String> contactsUserName = new ArrayList<>();
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private String senderNameInFriendRequest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GlobalVariables globalVariables = GlobalVariables.getInstance();

        String receiverNameInFriendRequest = db_connector.search("contact_contact_name", "friend_request", "contact_contact_name = '"
                + globalVariables.getPremiumUser().getUserName() + "'");

        senderNameInFriendRequest = db_connector.search("premium_user_user_name", "friend_request",
                "contact_contact_name = '" + globalVariables.getPremiumUser().getUserName() + "'");

        for (String s : db_connector.searchMultipleResults("contact_contact_name", "premium_user_has_contact",
                "premium_user_user_name = '" + globalVariables.getPremiumUser().getUserName() + "'")) {
            if (!s.equals("")) {
                contactsUserName.add(s);
            }
        }

        //if you have a contact request from a user that is not already your contact
        if (!receiverNameInFriendRequest.equals("")) {
            int counter = 0;
            for (String s : contactsUserName) {
                if (s.equals(senderNameInFriendRequest)) {
                    counter++;
                }
            }

            if (counter == 0) {
                String senderDisplayName = db_connector.search("display_name", "premium_user",
                        "user_name = '" + senderNameInFriendRequest + "'");
                String senderImgUrl = db_connector.search("personal_picture_path", "premium_user",
                        "user_name = '" + senderNameInFriendRequest + "'");
                lblDisplayNameAndText.setText(senderDisplayName + " has sent you a contact request");
            }

        }

    }

    @FXML
    private void onBtnAcceptPressed() {

        if (db_connector.search("contact_name", "contact", "contact_name = '" +
        senderNameInFriendRequest + "'").equals("")) {
            db_connector.insert("contact(contact_name)", "('" + senderNameInFriendRequest + "')");
        }
        db_connector.insert("premium_user_has_contact(premium_user_user_name, contact_contact_name)",
                "('" + GlobalVariables.getInstance().getPremiumUser().getUserName() + "', '" +
        senderNameInFriendRequest + "')");
        db_connector.delete("friend_request", "contact_contact_name = '" +
        GlobalVariables.getInstance().getPremiumUser().getUserName() + "'");
        Stage stage = (Stage) lblDisplayNameAndText.getScene().getWindow();
        stage.setOnHidden(event -> {
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        stage.close();
    }

    @FXML
    private void onBtnDeclinePressed() {
        db_connector.delete("friend_request", "contact_contact_name = '" +
                GlobalVariables.getInstance().getPremiumUser().getUserName() + "'");
        Stage stage = (Stage) lblDisplayNameAndText.getScene().getWindow();
        stage.setOnHidden(event -> {
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        stage.close();
    }
}
