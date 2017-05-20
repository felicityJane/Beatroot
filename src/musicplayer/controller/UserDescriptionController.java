package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.model.GlobalVariables;
import musicplayer.model.PremiumUser;
import musicplayer.model.TrialUser;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDescriptionController implements Initializable {

    @FXML
    private Label displayNameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label userTypeLabel;
    @FXML
    private Label emailAddressLabel;
    @FXML
    private Label playlistLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ImageView addButton;
    @FXML
    private ImageView saveButton;
    @FXML
    private ImageView uploadButton;
    @FXML
    private Button btnAddContact;
    @FXML
    private ImageView userImage;

    private DB_Connector connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");

    GlobalVariables globalVariables = GlobalVariables.getInstance();
    private String tableName;
    private String userName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (globalVariables.getOwnUserDescriptionController() != null && globalVariables.getContactDescriptionController() == null) {
            if (globalVariables.getPremiumUser() != null && globalVariables.getTrialuser() == null) {
                userImage.setImage(new Image(globalVariables.getPremiumUser().getProfilePicturePath()));
                displayNameLabel.setText(" : " + globalVariables.getPremiumUser().getDisplayName());
                genderLabel.setText(" : " + globalVariables.getPremiumUser().getGender());
                userTypeLabel.setText(" : Premium user");
                emailAddressLabel.setText(" : " + globalVariables.getPremiumUser().getEmailAddress());
                playlistLabel.setText(": " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getPremiumUser().getUserName() + "'")));
                descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "Premium_user", "user_name='" + globalVariables.getPremiumUser().getUserName() + "'")));
                descriptionTextArea.setVisible(false);
                addButton.setVisible(true);
                saveButton.setVisible(false);
                tableName = "premium_user";
                userName = globalVariables.getPremiumUser().getUserName();
                btnAddContact.setVisible(false);
            } else if (globalVariables.getTrialuser() != null && globalVariables.getPremiumUser() == null) {
                userImage.setImage(new Image(globalVariables.getTrialuser().getProfilePicturePath()));
                displayNameLabel.setText(" : " + globalVariables.getTrialuser().getDisplayName());
                genderLabel.setText(" : " + globalVariables.getTrialuser().getGender());
                userTypeLabel.setText(" : Trial user");
                emailAddressLabel.setText(" : " + globalVariables.getTrialuser().getEmailAddress());
                playlistLabel.setText(" : " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getTrialuser().getUserName() + "'")));
                descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "Trial_user", "user_name='" + globalVariables.getTrialuser().getUserName() + "'")));
                descriptionTextArea.setVisible(false);
                saveButton.setVisible(false);
                addButton.setVisible(true);
                tableName = "trial_user";
                userName = globalVariables.getTrialuser().getUserName();
                btnAddContact.setVisible(false);
            }
        }
        if (globalVariables.getOwnUserDescriptionController() == null && globalVariables.getContactDescriptionController() != null) {

            if (globalVariables.getTrialuser() != null && globalVariables.getPremiumUser() == null) {
                btnAddContact.setVisible(false);
                if (globalVariables.getContactSelected() instanceof PremiumUser) {
                    userImage.setImage(new Image(globalVariables.getContactSelected().getProfilePicturePath()));
                    displayNameLabel.setText(" : " + globalVariables.getContactSelected().getDisplayName());
                    genderLabel.setText(" : " + globalVariables.getContactSelected().getGender());
                    emailAddressLabel.setText(" : " + globalVariables.getContactSelected().getEmailAddress());
                    playlistLabel.setText(": " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getContactSelected().getUserName() + "' AND" +
                            " privacy_level_privacy_id = 1")));

                    descriptionTextArea.setVisible(false);
                    addButton.setVisible(false);
                    userName = globalVariables.getContactSelected().getUserName();
                    userTypeLabel.setText(" : Premium user");
                    tableName = "premium_user";
                    descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "premium_user", "user_name='" + globalVariables.getContactSelected().getUserName() + "'")));
                } else if (globalVariables.getContactSelected() instanceof TrialUser) {
                    userImage.setImage(new Image(globalVariables.getContactSelected().getProfilePicturePath()));
                    displayNameLabel.setText(" : " + globalVariables.getContactSelected().getDisplayName());
                    genderLabel.setText(" : " + globalVariables.getContactSelected().getGender());
                    emailAddressLabel.setText(" : " + globalVariables.getContactSelected().getEmailAddress());
                    playlistLabel.setText(": " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getContactSelected().getUserName() + "'" +
                            "AND privacy_level_privacy_id = 1")));

                    descriptionTextArea.setVisible(false);
                    addButton.setVisible(false);
                    userName = globalVariables.getContactSelected().getUserName();
                    userTypeLabel.setText(" : Premium user");
                    tableName = "premium_user";
                    descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "trial_user", "user_name='" + globalVariables.getContactSelected().getUserName() + "'")));
                }
            }else if (globalVariables.getPremiumUser() != null && globalVariables.getTrialuser() == null) {
                if (globalVariables.getContactSelected() instanceof PremiumUser) {
                    int counter = 0;
                    for (String s : connector.searchMultipleResults("contact_contact_name", "premium_user_has_contact",
                            "contact_contact_name = '" + globalVariables.getContactSelected().getUserName() +
                                    "' AND premium_user_user_name = '" + globalVariables.getPremiumUser().getUserName() + "'")) {
                        if (!s.equals("")) {
                            counter++;
                        }
                    }
                    for (String s : connector.searchMultipleResults("premium_user_user_name", "premium_user_has_contact",
                            "contact_contact_name = '" + globalVariables.getPremiumUser().getUserName() +
                                    "' AND premium_user_user_name = '" + globalVariables.getContactSelected().getUserName() + "'")) {
                        if (!s.equals("")) {
                            counter++;
                        }
                    }
                    if (counter == 0) {
                        int counter2 = 0;
                        for (String s : connector.searchMultipleResults("contact_contact_name", "friend_request",
                                "contact_contact_name = '" + globalVariables.getContactSelected().getUserName() + "'")) {
                            if (!s.equals("")) {
                                counter2++;
                            }
                        }
                        if (counter2 > 0) { //the selected user has a pending request from another user
                            btnAddContact.setVisible(true);
                            btnAddContact.setDisable(true);
                            btnAddContact.setText("Request pending");
                            descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "trial_user", "user_name='" + globalVariables.getContactSelected().getUserName() + "'")));
                            playlistLabel.setText(": " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getContactSelected().getUserName() + "'" +
                                    "AND privacy_level_privacy_id = 1")));
                        } else {
                            String s = connector.search("premium_user_user_name", "friend_request",
                                    "premium_user_user_name = '" + globalVariables.getContactSelected().getUserName()
                                            + "' AND contact_contact_name = '" + globalVariables.getPremiumUser().getUserName() + "'");
                            if (s.equals("")) { //the selected contact does not have a pending request from us
                                btnAddContact.setVisible(true);
                                btnAddContact.setDisable(false);
                                btnAddContact.setText("Add as a contact");
                                descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "trial_user", "user_name='" + globalVariables.getContactSelected().getUserName() + "'")));
                                playlistLabel.setText(": " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getContactSelected().getUserName() + "'" +
                                        "AND privacy_level_privacy_id = 1")));
                            } else { //the selected contact has a pending request from us
                                btnAddContact.setVisible(true);
                                btnAddContact.setDisable(true);
                                btnAddContact.setText("Request pending");
                                descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "trial_user", "user_name='" + globalVariables.getContactSelected().getUserName() + "'")));
                                playlistLabel.setText(": " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getContactSelected().getUserName() + "'" +
                                        "AND privacy_level_privacy_id = 1")));
                            }
                        }
                    } else {
                        btnAddContact.setVisible(true);
                        btnAddContact.setText("✓ Contacts");
                        descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "trial_user", "user_name='" + globalVariables.getContactSelected().getUserName() + "'")));
                        playlistLabel.setText(": " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getContactSelected().getUserName() + "'" +
                                "AND (privacy_level_privacy_id = 1 OR privacy_level_privacy_id = 2)")));
                        btnAddContact.setDisable(true);
                    }
                    userTypeLabel.setText(" : Premium user");
                    tableName = "premium_user";
                    descriptionLabel.setText("- " + String.valueOf(connector.search("user_description", "premium_user", "user_name='" + globalVariables.getContactSelected().getUserName() + "'")));
                } else {
                    btnAddContact.setVisible(true);
                    btnAddContact.setDisable(true);
                    btnAddContact.setText("Trial user");
                    userTypeLabel.setText(" : Trial user");
                    tableName = "trial_user";
                    playlistLabel.setText(": " + String.valueOf(connector.searchMultipleResults("name", "playlist", "owner='" + globalVariables.getContactSelected().getUserName() + "'" + "AND privacy_level_privacy_id = 1")));
                }
                userImage.setImage(new Image(globalVariables.getContactSelected().getProfilePicturePath()));
                displayNameLabel.setText(" : " + globalVariables.getContactSelected().getDisplayName());
                genderLabel.setText(" : " + globalVariables.getContactSelected().getGender());
                emailAddressLabel.setText(" : " + globalVariables.getContactSelected().getEmailAddress());
                descriptionTextArea.setVisible(false);
                addButton.setVisible(false);
                userName = globalVariables.getContactSelected().getUserName();
            }
        }
        addButton.setImage(new Image("images/add.png"));
        saveButton.setImage(new Image("images/save.png"));
        uploadButton.setImage(new Image("images/upload.png"));
        addButton.setOnMouseEntered(event -> {
            Scene scene = addButton.getScene();
            scene.setCursor(Cursor.HAND);
        });

        addButton.setOnMouseExited(event -> {
            Scene scene = addButton.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
        addButton.setOnMouseClicked(event -> {
            descriptionTextArea.setVisible(true);
            saveButton.setVisible(true);
        });
        saveButton.setOnMouseEntered(event -> {
            Scene scene = saveButton.getScene();
            scene.setCursor(Cursor.HAND);
        });
        saveButton.setOnMouseExited(event -> {
            Scene scene = saveButton.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
       saveButton.setOnMouseClicked(event -> {
           try {
               //UPDATE trial_user SET user_description ='add again' where user_name='fatih20';
               connector.update(tableName,"user_description","'"+descriptionTextArea.getText()+"'","user_name='"+userName+"'");
               descriptionTextArea.setVisible(false);
               addButton.setVisible(true);
               descriptionLabel.setText("- "+descriptionTextArea.getText());
           }catch (Exception e){
               e.printStackTrace();
           }

        });
        uploadButton.setOnMouseEntered(event -> {
            Scene scene = uploadButton.getScene();
            scene.setCursor(Cursor.HAND);
        });

        uploadButton.setOnMouseExited(event -> {
            Scene scene = uploadButton.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
        uploadButton.setOnMouseClicked(event -> {

        });

    }

    @FXML
    private void onBtnAddContactPressed() {

        try {
            connector.insert("contact(contact_name)", "('" + globalVariables.getContactSelected().getUserName() + "')");
            connector.insert("friend_request(text, premium_user_user_name, contact_contact_name)",
                    "('Hello', '" + globalVariables.getPremiumUser().getUserName() + "', '" +
                            globalVariables.getContactSelected().getUserName() + "')");
            DialogBoxManager.confirmationDialogBox("Contact sent", "Your contact request has been to sent to the user");
            btnAddContact.setText("Request pending");
            btnAddContact.setDisable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

