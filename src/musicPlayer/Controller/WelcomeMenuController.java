package musicPlayer.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class WelcomeMenuController implements Initializable {

    @FXML private Circle btnPlay;
    @FXML private Label lblTrackName;
    @FXML private Label lblElapsedTime;
    @FXML private Label lblTimeLeft;
    @FXML private Slider sliderPlay;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = new Image("Play.jpg");
        btnPlay.setFill(new ImagePattern(img));
        Path path = Paths.get("ace of base - all that she wants.mp3");

        media = new Media(path.toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView = new MediaView(mediaPlayer);
        Duration duration = mediaPlayer.getMedia().getDuration();

    }

    @FXML
    private void clickOnPlayButton() {

        MediaPlayer.Status currentStatus = mediaPlayer.getStatus();

        if (currentStatus == MediaPlayer.Status.PAUSED || currentStatus == MediaPlayer.Status.STOPPED) {
            Image img = new Image("Pause.jpg");
            btnPlay.setFill(new ImagePattern(img));
            mediaPlayer.play();

        } else {
            Image img = new Image("Play.jpg");
            btnPlay.setFill(new ImagePattern(img));
            mediaPlayer.pause();
        }

    }

}

