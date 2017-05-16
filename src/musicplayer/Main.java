package musicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            //Application.setUserAgentStylesheet(getClass().getResource("css/styleSheet.css").toExternalForm());
            Parent root = FXMLLoader.load(getClass().getResource("view/logInMenu.fxml"));
            primaryStage.setTitle("Beatroot");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            addCSS(scene);
            primaryStage.show();
        }

	public static void main(String[] args) {
		launch(args);
	}
	public void addCSS(Scene scene){
        URL url = this.getClass().getResource("css/styleSheet.css");
        if (url==null){
            System.out.println("Not found");
            System.exit(0);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
    }
}
