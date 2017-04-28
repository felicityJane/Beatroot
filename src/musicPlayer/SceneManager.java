package musicPlayer;

/**
 * Created by felic on 19/04/2017.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    public static SceneManager sceneManager = new SceneManager();

    public void changeScene(ActionEvent e, String fxmlFileName ) throws IOException {
        Node node = (Node)e.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        stage.setTitle("Beatroot");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeScene(MouseEvent me, String fxmlFileName) throws IOException {
        Node node = (Node)me.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        stage.setTitle("Beatroot");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}