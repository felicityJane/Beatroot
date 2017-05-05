package musicplayer2;
import com.mysql.jdbc.Connection;

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

            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing search query. Please try again.");
            ex.printStackTrace();
        }
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

        } catch (SQLException sqlEx) {
            DialogBoxManager.errorDialogBox("Cannot run query","Error on executing delete query. Please try again.");
            sqlEx.printStackTrace();
        }
    }
}
