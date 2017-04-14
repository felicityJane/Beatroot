package musicPlayer.Controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

import static java.lang.Math.floor;

public class WelcomeMenuController implements Initializable {

    @FXML private Circle btnPlay;
    @FXML private Label lblTrackName;
    @FXML private Label lblElapsedTime;
    @FXML private Label lblTimeLeft;
    @FXML private Slider sliderPlay;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    Duration currentTime;
    Duration duration;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = new Image("Play.jpg");
        btnPlay.setFill(new ImagePattern(img));
        Path path = Paths.get("ace of base - all that she wants.mp3");


        media = new Media(path.toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);


        mediaPlayer.setOnReady(new Runnable() {

            @Override
            public void run() {
                sliderPlay.setMin(0.0);
                sliderPlay.setValue(0.0);
                sliderPlay.setMax(mediaPlayer.getTotalDuration().toSeconds());


                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {

                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration current) {
                        sliderPlay.setValue(current.toSeconds());
                    }
                });
                sliderPlay.valueProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        if (sliderPlay.isPressed()) {
                            mediaPlayer.seek(Duration.seconds(sliderPlay.getValue()));
                        }
                    }
                });
            }
        });


    }

    @FXML
    private void clickOnPlayButton() {

        MediaPlayer.Status currentStatus = mediaPlayer.getStatus();

        if (currentStatus == MediaPlayer.Status.PAUSED || currentStatus == MediaPlayer.Status.STOPPED || currentStatus == MediaPlayer.Status.READY) {
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

