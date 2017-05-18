package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import musicplayer.DB_Connector;
import musicplayer.model.GlobalVariables;

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

    private DB_Connector connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");

    GlobalVariables globalVariables = GlobalVariables.getInstance();
    private String tableName;
    private String userName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        globalVariables.setUserDescriptionController(this);
        if (globalVariables.getPremiumUser() != null && globalVariables.getTrialuser() == null) {
            displayNameLabel.setText(" : " + globalVariables.getPremiumUser().getDisplayName());
            genderLabel.setText(" : " + globalVariables.getPremiumUser().getGender());
            userTypeLabel.setText(" : Premium user");
            emailAddressLabel.setText(" : " + globalVariables.getPremiumUser().getEmailAddress());
            playlistLabel.setText(": "+String.valueOf(connector.searchMultipleResults("name","playlist", "owner='"+globalVariables.getPremiumUser().getUserName()+"'")));
            descriptionLabel.setText("- "+String.valueOf(connector.search("user_description","Premium_user", "user_name='"+globalVariables.getPremiumUser().getUserName()+"'")));
            descriptionTextArea.setVisible(false);
            addButton.setVisible(false);
            tableName="premium_user";
            userName=globalVariables.getPremiumUser().getUserName();

        } else if (globalVariables.getTrialuser() != null && globalVariables.getPremiumUser() == null) {
            displayNameLabel.setText(" : " + globalVariables.getTrialuser().getDisplayName());
            genderLabel.setText(" : " + globalVariables.getTrialuser().getGender());
            userTypeLabel.setText(" : Trial user");
            emailAddressLabel.setText(" : " + globalVariables.getTrialuser().getEmailAddress());
            playlistLabel.setText(" : "+String.valueOf(connector.searchMultipleResults("name","playlist", "owner='"+globalVariables.getTrialuser().getUserName()+"'")));
            descriptionLabel.setText("- "+String.valueOf(connector.search("user_description","Trial_user", "user_name='"+globalVariables.getTrialuser().getUserName()+"'")));
            descriptionTextArea.setVisible(false);
            saveButton.setVisible(false);
            //addButton.setVisible(false);
            tableName="trial_user";
            userName=globalVariables.getTrialuser().getUserName();

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
               addButton.setVisible(false);
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

}

