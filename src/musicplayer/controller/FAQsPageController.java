package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import musicplayer.model.GlobalVariables;
import java.net.URL;
import java.util.ResourceBundle;


public class FAQsPageController implements Initializable {
    @FXML private AnchorPane faqsPageAnchorPane;
    @FXML private TextArea textAreaFaqs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        globalVariables.setFaQsPageController(this);
        globalVariables.getMainMenuController().menuBarFitToParent(faqsPageAnchorPane);
        globalVariables.getMainMenuController().enableMenuItemsFaqsPage();
        textAreaFaqs.setWrapText(true);
        textAreaFaqs.setText("What is a beatroot?\n" +
                "beat: a main accent or rhythmic unit in music or poetry;\n" +
                "root: establish deeply and firmly.\n" +
                "\n" +
                "How long is the trial period?\n" +
                "30 days.\n" +
                "\n" +
                "How much is a premium memebership?\n" +
                "99.99:- per month.");
    }
}
