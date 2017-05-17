package musicplayer;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import musicplayer.model.MusicTrack;


/**
 * Created by Federica on 17/05/2017.
 */
public class ImageTextCell extends ListCell<MusicTrack> {

    @Override
    protected void updateItem(MusicTrack item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        }else {

            HBox hBox = new HBox(4.0);
            hBox.setAlignment(Pos.CENTER_LEFT);

            Image thumbNailImage = new Image(item.getAlbumCoverPath());
            Circle thumbNail = new Circle();
            thumbNail.setFill(new ImagePattern(thumbNailImage));
            thumbNail.setCenterX(60.0f);
            thumbNail.setCenterY(60.0f);
            thumbNail.setRadius(20.0f);
            hBox.getChildren().add(thumbNail);

            Label l = new Label(item.getTrackName());
            l.setWrapText(true);
            l.setTextAlignment(TextAlignment.CENTER);
            hBox.getChildren().add(l);

            setGraphic(hBox);
            setPrefWidth(USE_PREF_SIZE);
        }
    }
}
