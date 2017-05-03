package musicplayer;
import javafx.scene.control.Alert;

import java.sql.*;

/**
 * Created by Federica on 19/04/2017.
 */
public class DB_Connector {

    private String urlOfDatabase;
    private Statement statement;

    public DB_Connector(String urlOfDatabase) {

        try {
            this.urlOfDatabase = urlOfDatabase;
            Connection c = (Connection) DriverManager.getConnection(urlOfDatabase);
            statement = c.createStatement();

        } catch (SQLException sqlEx) {

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setContentText("Error while accessing the database. Please try again.");
            dialog.setHeaderText("Unable to access specified database");
            dialog.setTitle("Error");
            dialog.showAndWait();
        }
    }

    public void search(String parameterToSearch, String tableName, String whereStatement) {
        try {
            ResultSet rs = statement.executeQuery("SELECT " + parameterToSearch +
                    " FROM " + tableName + " WHERE " + whereStatement);
            while (rs.next()) {
                System.out.println("The " + rs.getString(2) + " has " + parameterToSearch + " = " + rs.getString(1) +
                        " for " + whereStatement + ".");
            }
        }
        catch (SQLException ex) {

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setContentText("Error on executing the query!");
            dialog.setHeaderText("Unable to run query");
            dialog.setTitle("Error");
            dialog.showAndWait();
        }
    }

    public void update(String tableToUpdate, String parameterToUpdate, String newParameter, String whereStatement) {

        try {
            int rows = statement.executeUpdate("UPDATE " + tableToUpdate + " SET " + parameterToUpdate + " = " + newParameter
                    + " WHERE " + whereStatement);
            System.out.println("Updated rows: " + rows);
        } catch (SQLException sqlEx) {

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setContentText("Error on executing the query!");
            dialog.setHeaderText("Unable to run query");
            dialog.setTitle("Error");
            dialog.showAndWait();
        }
    }

    public void insert(String tableNameAndParameters,String values) {

        try {
            statement.executeQuery("INSERT INTO" + tableNameAndParameters  +" VALUES "
            + values);

        } catch (SQLException sqlEx) {

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setContentText("Error on executing the query!");
            dialog.setHeaderText("Unable to run insert query");
            dialog.setTitle("Error");
            dialog.showAndWait();
            sqlEx.printStackTrace();
        }
    }

    public void delete(String tableToDeleteFrom, String whereStatement) {

        try {
            statement.executeQuery("DELETE FROM " + tableToDeleteFrom + " WHERE "
            + whereStatement);

        } catch (SQLException sqlEx) {

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setContentText("Error on executing the query!");
            dialog.setHeaderText("Unable to run query");
            dialog.setTitle("Error");
            dialog.showAndWait();
        }
    }



}
