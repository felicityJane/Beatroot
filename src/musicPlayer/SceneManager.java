package musicPlayer;

/**
 * Created by felic on 19/04/2017.
 */
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
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

        stage.setOnCloseRequest(event ->  {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    File file = new File("tmp");
                    for (File f : file.listFiles()) {
                        if (!f.isDirectory()) {
                            f.delete();
                        }
                    }
                }

            });
        });
    }

    public void changeScene(MouseEvent me, String fxmlFileName) throws IOException {
        Node node = (Node)me.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        stage.setTitle("Beatroot");
        stage.setScene(scene);
        stage.centerOnScreen();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                File file = new File("tmp");
                for (File f : file.listFiles()) {
                    if (!f.isDirectory()) {
                        f.delete();
                    }
                }
            }

        });
    }
}