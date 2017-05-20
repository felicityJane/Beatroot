package musicplayer;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import musicplayer.model.PremiumUser;

/**
 * Created by Federica on 18/05/2017.
 */
public class ImageTextCellContacts extends ListCell<PremiumUser> {

    @Override
    protected void updateItem(PremiumUser item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        }else {

            HBox hBox = new HBox(4.0);
            hBox.setAlignment(Pos.CENTER_LEFT);

            Image thumbNailImage = new Image(item.getProfilePicturePath());
            Circle thumbNail = new Circle();
            thumbNail.setFill(new ImagePattern(thumbNailImage));
            thumbNail.setCenterX(60.0f);
            thumbNail.setCenterY(60.0f);
            thumbNail.setRadius(20.0f);
            hBox.getChildren().add(thumbNail);

            Label l = new Label(item.getDisplayName());
            l.setWrapText(true);
            l.setTextAlignment(TextAlignment.CENTER);
            hBox.getChildren().add(l);

            setGraphic(hBox);
            setPrefWidth(USE_PREF_SIZE);
        }
    }
}
