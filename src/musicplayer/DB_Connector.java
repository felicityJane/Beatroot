package musicplayer;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import musicplayer.model.*;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DB_Connector {

    private String urlOfDatabase;
    private Statement statement;

    public DB_Connector(String urlOfDatabase) {

        try {
            this.urlOfDatabase = urlOfDatabase;
            Connection c = (Connection) DriverManager.getConnection(urlOfDatabase);
            statement = c.createStatement();

        } catch (SQLException sqlEx) {
            DialogBoxManager.errorDialogBox("Unable to access specified database","Error while accessing the database. Please try again.");
            sqlEx.printStackTrace();

        }
    }

    public String search(String parameterToSearch, String tableName, String whereStatement) {

        String sqlString = "";
        try {
            ResultSet rs = statement.executeQuery("SELECT " + parameterToSearch +
                    " FROM " + tableName + " WHERE " + whereStatement);
            while (rs.next()) {
               sqlString = rs.getString(1);
            }
        }
        catch (SQLException ex) {

            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing search query. Please try again.");
            ex.printStackTrace();
        }

        return sqlString;
    }

    public ArrayList<String> searchMultipleResults(String parameterToSearch, String tableName, String whereStatement) {


        ArrayList<String> sqlArrayList = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT " + parameterToSearch +
                    " FROM " + tableName + " WHERE " + whereStatement);
            while (rs.next()) {
                sqlArrayList.add(rs.getString(1));
            }
        }
        catch (SQLException ex) {

            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing multiple search query. Please try again.");
            ex.printStackTrace();
        }

        return sqlArrayList;
    }

    public void update(String tableToUpdate, String parameterToUpdate, String newParameter, String whereStatement) {

        try {
            int rows = statement.executeUpdate("UPDATE " + tableToUpdate + " SET " + parameterToUpdate + " = " + newParameter
                    + " WHERE " + whereStatement);
            System.out.println("Updated rows: " + rows);
        } catch (SQLException sqlEx) {
            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing update query. Please try again.");
            sqlEx.printStackTrace();
        }
    }

    public void insert(String tableNameAndParameters, String values) {

        try {
            statement.executeUpdate("INSERT INTO " + tableNameAndParameters + " VALUES "
                    + values);

        }catch (MySQLIntegrityConstraintViolationException ignored){

        }catch (SQLException sqlEx) {
            DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing insert query. Please try again.");
            sqlEx.printStackTrace();
        }
    }

    public void delete(String tableToDeleteFrom, String whereStatement) {

        try {
            statement.executeUpdate("DELETE FROM " + tableToDeleteFrom + " WHERE "
            + whereStatement);

        }catch (SQLException sqlEx) {
            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing delete query. Please try again.");
            sqlEx.printStackTrace();
        }
    }
    public void logInTrial(String userName, String password, ActionEvent event, Label warningLabel) {
        try {
            ResultSet rs = statement.executeQuery("SELECT user_name, password,display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, free_trial_end_date, gender_gender_id, playlist_link FROM trial_user WHERE user_name = '" + userName + "'");

            if (rs.next()) {
                if (password.equals(rs.getString(2))) {
                    String phoneNumber="";
                    String displayName=rs.getString(3);
                    String firstName=rs.getString(4);
                    String lastName=rs.getString(5);
                    Date birthDay=rs.getDate(6);
                    String emailAddress=rs.getString(7);
                    String physicalAddress=rs.getString(8);
                    String city=rs.getString(9);
                    String postalCode=rs.getString(10);
                    Country country=Country.valueOf(rs.getString(11));
                    Date freeTrialEndDate=rs.getDate(12);
                    Gender gender=Gender.values()[Integer.parseInt(rs.getString(13))];
                    String playListLink=rs.getString(14);

                    TrialUser user = new TrialUser(userName,displayName,password,firstName,lastName,birthDay,emailAddress,physicalAddress,city,postalCode,country,gender,phoneNumber,freeTrialEndDate);
                    SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
                    //call well come menucontroller setuser(User user);
                    //this.user=user;
                } else {
                    warningLabel.setText("Invalid username or password!!");
                }
            }

        } catch (SQLException ex) {
            DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing login query. Please try again.");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logInPremium(String userName, String password, ActionEvent event, Label warningLabel) {

        try {
            ResultSet rs = statement.executeQuery("SELECT user_name, password,display_name, first_name, last_name, date_of_birth, email_address, city_of_residence, postal_code, country, bank_card_number, expiration_date, card_type, billing_account_owner_name, billing_city, billing_postal_code,billing_country,billing_phone_number, gender_gender_id, playlist_link FROM premium_user WHERE user_name='" + userName + "'");

            if (rs.next()) {
                if (password.equals(rs.getString(2))) {
                    String phoneNumber=rs.getString(18);
                    String displayName=rs.getString(3);
                    String firstName=rs.getString(4);
                    String lastName=rs.getString(5);
                    Date birthDay=rs.getDate(6) ;
                    String emailAddress=rs.getString(7);
                    String physicalAddress="";
                    String city=rs.getString(8);
                    String postalCode=rs.getString(9);
                    Country country=Country.valueOf(rs.getString(10));
                    String bankCardNumber=rs.getString(11);
                    Date expiratinDate=rs.getDate(12);
                    PaymentMethod paymentMethod=PaymentMethod.valueOf(rs.getString(13).toUpperCase());
                    String accountOwnerName=rs.getString(14);
                    String billingCity=rs.getString(15);
                    String billingPostalCode=rs.getString(16);
                    Country billingCountry=Country.valueOf(rs.getString(17));
                    String billingPhoneNumber=rs.getString(18);
                    Gender gender=Gender.values()[Integer.parseInt(rs.getString(19))];
                    String playListLink=rs.getString(20);

                    //New Premium User
                    PremiumUser premiumUser=new PremiumUser(userName,displayName,password,firstName,lastName,birthDay,emailAddress,physicalAddress,city,postalCode,country,gender,phoneNumber,bankCardNumber,expiratinDate,paymentMethod,accountOwnerName,physicalAddress,billingCity,billingPostalCode,billingCountry,billingPhoneNumber);
                    SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
                } else {
                    warningLabel.setText("Invalid username or password!!");
                }
            }
        }catch (SQLException ex) {

            DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing login query. Please try again.");
            ex.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkTrialUserName(String userName, Label warningLabel, ActionEvent event) {
        try {
            ResultSet rs = statement.executeQuery("SELECT user FROM user_link WHERE user='" + userName + "'");
            if (rs.next()) {
                warningLabel.setText("Username is already taken");
                warningLabel.setVisible(false);
            }else {
                SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
            }
        }catch (SQLException ex) {
            DialogBoxManager.errorDialogBox("Cannot run query", "Error on User Name. Please try again.");
            ex.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void checkPremiumlUserName(String userName, Label warningLabel,ActionEvent event) {
        try {
            ResultSet rs = statement.executeQuery("SELECT user FROM user_link WHERE user='" + userName + "'");
            if (rs.next()) {
                warningLabel.setText("Username is already taken");
            }else {
                SceneManager.sceneManager.changeScene(event, "view/paymentMenu.fxml");
            }
        }catch (SQLException ex) {
            DialogBoxManager.errorDialogBox("Cannot run query", "Error on User Name. Please try again.");
            ex.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
