package musicplayer;
import com.mysql.jdbc.Connection;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        } catch (SQLException sqlEx) {
            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing insert query. Please try again.");
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
            ResultSet rs = statement.executeQuery("SELECT user_name,password FROM trial_user WHERE user_name='"+userName+"'");

           if (rs.next()) {
                if (password.equals(rs.getString(2)) ){
                    SceneManager.sceneManager.changeScene(event,"view/welcomeMenu.fxml");
                }else {
                   warningLabel.setText("Invalid username or password!!");
                }
            }if (!rs.next()){
                warningLabel.setText("Invalid Username or password!!");
            }
        }catch (SQLException ex) {

            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing login query. Please try again.");
            ex.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logInPremium(String userName, String password, ActionEvent event, Label warningLabel) {

        try {
            ResultSet rs = statement.executeQuery("SELECT user_name,password FROM premium_user WHERE user_name='"+userName+"'");

            if (rs.next()) {
                if (password.equals(rs.getString(2)) ){
                    SceneManager.sceneManager.changeScene(event,"view/welcomeMenu.fxml");
                }else {
                    warningLabel.setText("Invalid username or password!!");
                }
            }if (!rs.next()){
                warningLabel.setText("Invalid Username or password!!");
            }
        }catch (SQLException ex) {

            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing login query. Please try again.");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
