package musicplayer;

/**
 * Created by felic on 19/04/2017.
 */
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    public static SceneManager sceneManager = new SceneManager();

    public void changeScene(Event e, String fxmlFileName ) throws IOException {
        Node node = (Node)e.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        stage.setTitle("Beatroot");
        stage.setScene(scene);
    }

    public void changeSceneMenuBar(MenuBar menuBar, String fxmlFileName) throws IOException {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        stage.setTitle("Beatroot");
        stage.setScene(scene);
    }
}