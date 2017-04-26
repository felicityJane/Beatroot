package musicPlayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.nio.file.Path;

/**
 * Created by fatih on 2017-04-15.
 */
public class Main extends Application {

    static final String loginMenuPath = "View/logInMenu.fxml";
        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource(loginMenuPath));
            primaryStage.setTitle("");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
