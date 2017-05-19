package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import musicplayer.model.GlobalVariables;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutPageController implements Initializable {
    @FXML private AnchorPane aboutPageAnchorPane;
    @FXML private Label aboutLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        globalVariables.setAboutPageController(this);
        globalVariables.getMainMenuController().menuBarFitToParent(aboutPageAnchorPane);
        globalVariables.getMainMenuController().disableMenuItems();

        aboutLabel.setWrapText(true);
        aboutLabel.setText("Beatroot is a music track streaming service, officially launched on 21 May 2017. " +
                "It is developed by startup 3FV in Kristianstad, Sweden. It provides digital rights management-protected content from record labels and media companies. " +
                "Beatroot is a freemium service, meaning that basic features are free with advertisements, while additional features, including improved streaming quality and " +
                "offline music downloads, are offered via paid subscriptions.\n" +
                "\n" +
                "Beatroot is available in most of Europe, most of the Americas, Australia, New Zealand and parts of Asia. It is available for most modern devices, " +
                "including Windows, macOS, and Linux computers, as well as iOS and Android smartphones and tablets. Music can be browsed or searched for via various parameters, " +
                "such as artist, album or playlist. Users can create, edit and share playlists, share tracks on social media, and make playlists with other users. " +
                "Beatroot provides access to over 69 songs. As of May 2017, it has 5 active users, and as of May 2017, it has 3 paying subscribers.\n" +
                "\n" +
                "Unlike physical or download sales, which pay artists a fixed price per song or album sold, Beatroot pays royalties based on the number of artists' " +
                "streams as a proportion of total songs streamed on the service. They distribute approximately 70% of total revenue to rights holders, who then pay artists " +
                "based on their individual agreements.");
    }
}
