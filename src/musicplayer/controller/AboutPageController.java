package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import musicplayer.model.GlobalVariables;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by felic on 9/05/2017.
 */
public class AboutPageController implements Initializable {
    @FXML private AnchorPane aboutPageAnchorPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        globalVariables.setAboutPageController(this);
        globalVariables.getMainMenuController().menuBarFitToParent(aboutPageAnchorPane);
        globalVariables.getMainMenuController().enableMenuItemsAboutPage();
    }
}
