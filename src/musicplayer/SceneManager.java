package musicplayer;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
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
        stage.centerOnScreen();

        stage.setOnCloseRequest(event ->  {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    File file = new File("tmp/");
                    for (File f : file.listFiles()) {
                        if (!f.isDirectory()) {
                            f.delete();
                        }
                    }
                }

            });
        });
    }
    public void changeSceneMenuBar(MenuBar menuBar, String fxmlFileName) throws IOException {
        Stage stage = (Stage) menuBar.getScene().getWindow();
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
    public void openPopupScene(Event e, String fxmlFileName ) throws IOException {
        Node node = (Node)e.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        stage.setTitle("Beatroot");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();

    }
    public void popUpWindow(Event e, String fxmlFileName) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        stage.setScene(new Scene(root));
        stage.setTitle("Beatroot");
        stage.show();
        stage.centerOnScreen();
    }
    public void changeSceneMenuItem(AnchorPane anchorPane, String fxmlFileName) throws IOException {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
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