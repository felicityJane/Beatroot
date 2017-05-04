package musicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by fatih on 2017-04-15.
 */

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            //always begin in the logInMenu scene
            Parent root = FXMLLoader.load(getClass().getResource("view/logInMenu.fxml"));
            primaryStage.setTitle("Beatroot");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
