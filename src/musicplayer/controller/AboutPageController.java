package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by felic on 9/05/2017.
 */
public class AboutPageController implements Initializable {
    @FXML private MainMenuController mainMenuController;
    @FXML private AnchorPane aboutPageAnchorPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.menuBarFitToParent(aboutPageAnchorPane);
        mainMenuController.enableMenuItemsAboutPage();
    }
}
