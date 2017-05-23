package musicplayer;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SceneManager {

    private static SceneManager sceneManager;
    private String cssFile;

    private SceneManager() {
        this.setBrightMode(false);
    }

    public static SceneManager getInstance(){
        if (sceneManager == null){
            sceneManager = new SceneManager();
        }
        return sceneManager;
    }

    public void changeSceneMain(Stage stage, String fxml) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(fxml));
            Scene scene = new Scene(root);
            this.applyCurrentCss(scene);
            stage.setScene(scene);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public void applyCurrentCss(Scene scene) {
        scene.getStylesheets().add(this.cssFile);
    }
    public void setBrightMode(boolean active) {
        if(active) {
            this.cssFile = "musicplayer/css/brightAndBubbly.css";
        }else {
            this.cssFile = "musicplayer/css/darkAndGloomy.css";
        }
    }

    public void changeScene(Event e, String fxmlFileName ) throws IOException {
        Node node = (Node)e.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        this.applyCurrentCss(scene);
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
    public void changeSceneMenuBar(MenuBar menuBar, String fxmlFileName) throws IOException {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        this.applyCurrentCss(scene);
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
    public void popUpWindow(String fxmlFileName) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        this.applyCurrentCss(scene);
        stage.setTitle("Beatroot");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.centerOnScreen();
    }
    public void openNewWindow(String fxmlFileName, String title) throws IOException {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene scene = new Scene(root);
            this.applyCurrentCss(scene);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.centerOnScreen();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public Stage openNewWindowReturnStage(String fxmlFileName, String title) throws IOException {
        Stage stage;
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene scene = new Scene(root);
            this.applyCurrentCss(scene);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.centerOnScreen();
        } catch(Exception ex) {
            ex.printStackTrace();
            stage = null;
        }
        return stage;
    }
}