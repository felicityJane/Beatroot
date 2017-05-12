package musicplayer;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            ResultSet rs = statement.executeQuery("SELECT user_name, password, display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, free_trial_end_date, gender_gender_id, playlist_link FROM trial_user WHERE user_name='" + userName + "'");

            if (rs.next()) {
                if (password.equals(rs.getString(2))) {
                    Path path=Paths.get("UserName.bin");
                    ArrayList<String>userNameAndType=new ArrayList<>();
                    userNameAndType.add(0,userName);
                    userNameAndType.add(1,"TrialUser");

                    Files.write(path,userNameAndType, StandardOpenOption.CREATE);

                    SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");

                } else {
                    warningLabel.setText("Invalid username or password!!");
                }
            }
            if (!rs.next()) {
                warningLabel.setText("Invalid Username or password!!");
            }
        } catch (SQLException ex) {
            DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing trial login query. Please try again.");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logInPremium(String userName, String password, ActionEvent event, Label warningLabel){

        try {
            ResultSet rs = statement.executeQuery("SELECT user_name, password,display_name, first_name, last_name, date_of_birth, email_address, physical_address, city_of_residence, postal_code, country, bank_card_number, expiration_date, card_type, billing_account_owner_name, billing_address, billing_city, billing_postal_code,billing_country,billing_phone_number, gender_gender_id, playlist_link FROM premium_user WHERE user_name='" + userName + "'");

            if (rs.next()) {
                if (password.equals(rs.getString(2))) {

                    Path path=Paths.get("UserName.bin");
                    ArrayList<String>userNameAndType=new ArrayList<>();
                    userNameAndType.add(0,userName);
                    userNameAndType.add(1,"PremiumUser");

                    Files.write(path,userNameAndType, StandardOpenOption.CREATE);

                    SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");

                }else {
                    warningLabel.setText("Invalid username or password!!");
                }
            }
            if (!rs.next()) {
                warningLabel.setText("Invalid Username or password!!");
            }
        }catch (SQLException ex) {
            DialogBoxManager.errorDialogBox("Cannot run query", "Error on executing premium login query. Please try again.");
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
            }
        }catch (SQLException ex) {
            DialogBoxManager.errorDialogBox("Cannot run query", "Error on User Name. Please try again.");
            ex.printStackTrace();
        }
    }
    public void checkPremiumUserName(String userName, Label warningLabel, ActionEvent event) {
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
