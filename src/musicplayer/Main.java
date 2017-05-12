package musicplayer;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import musicplayer.model.MusicArtist;

import java.net.URL;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            //Application.setUserAgentStylesheet(getClass().getResource("css/styleSheet.css").toExternalForm());
            Parent root = FXMLLoader.load(getClass().getResource("view/welcomeMenu.fxml"));
            primaryStage.setTitle("Beatroot");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            URL url = this.getClass().getResource("css/styleSheet.css");
            if (url==null){
                System.out.println("Not found");
                System.exit(0);
            }
            String css = url.toExternalForm();
            scene.getStylesheets().add(css);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }

}
