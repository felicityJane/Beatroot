package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;


public class FAQsPageController implements Initializable {
    @FXML private MainMenuController mainMenuController;
    @FXML private AnchorPane faqsPageAnchorPane;
    @FXML private TextArea textAreaFaqs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.menuBarFitToParent(faqsPageAnchorPane);
        mainMenuController.enableMenuItemsFaqsPage();

        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        textAreaFaqs.setEffect(dropShadow);
    }
}
