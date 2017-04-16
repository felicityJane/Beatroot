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
    @FXML private Circle btnPause;
    @FXML private Circle btnStop;
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
        Image img = new Image("PlayNormal.jpg");
        btnPlay.setFill(new ImagePattern(img));
        Image img1 = new Image("StopNormal.jpg");
        btnStop.setFill(new ImagePattern(img1));
        Image img2 = new Image("PauseNormal.jpg");
        btnPause.setFill(new ImagePattern(img2));
        Path path = Paths.get("ace of base - all that she wants.mp3");


        media = new Media(path.toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        lblElapsedTime.setText("00:00");
        lblTimeLeft.setText(formatTime(mediaPlayer.getTotalDuration().toSeconds()));


        mediaPlayer.setOnReady(new Runnable() {

                @Override
                public void run() {
                sliderPlay.setMin(0.0);
                sliderPlay.setValue(0.0);
                sliderPlay.setMax(mediaPlayer.getTotalDuration().toSeconds());


                mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) ->  {
                        sliderPlay.setValue(newValue.toSeconds());
                        lblElapsedTime.setText(formatTime(mediaPlayer.getCurrentTime().toSeconds()));
                        lblTimeLeft.setText(formatTime(mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer.getCurrentTime().toSeconds()));
                    }
                );
                sliderPlay.valueProperty().addListener( observable -> {
                        if (sliderPlay.isPressed()) {
                            mediaPlayer.seek(Duration.seconds(sliderPlay.getValue()));
                        }
                    }
            );
            }
        });


    }

    @FXML
    private void clickOnPlayButton() {

        MediaPlayer.Status currentStatus = mediaPlayer.getStatus();

        if (currentStatus == MediaPlayer.Status.PAUSED || currentStatus == MediaPlayer.Status.STOPPED || currentStatus == MediaPlayer.Status.READY) {
           mediaPlayer.play();
        }

    }

    @FXML
    private void clickOnPauseButton() {
        MediaPlayer.Status currentStatus = mediaPlayer.getStatus();

        if (currentStatus == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }

    }

    @FXML
    private void clickOnStopButton() {
        MediaPlayer.Status currentStatus = mediaPlayer.getStatus();

        if (currentStatus == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }

    }

    @FXML
    private void hoverOnPlayButton() {
        Image img = new Image("PlayHover.jpg");
        btnPlay.setFill(new ImagePattern(img));
    }

    @FXML
    private void hoverOnPauseButton() {
        Image img = new Image("PauseHover.jpg");
        btnPause.setFill(new ImagePattern(img));
    }

    @FXML
    private void hoverOnStopButton() {
        Image img = new Image("StopHover.jpg");
        btnStop.setFill(new ImagePattern(img));
    }

    @FXML
    private void leavePlayButton() {
        Image img = new Image("PlayNormal.jpg");
        btnPlay.setFill(new ImagePattern(img));
    }

    @FXML
    private void leavePauseButton() {
        Image img = new Image("PauseNormal.jpg");
        btnPause.setFill(new ImagePattern(img));
    }

    @FXML
    private void leaveStopButton() {
        Image img = new Image("StopNormal.jpg");
        btnStop.setFill(new ImagePattern(img));
    }

    @FXML
    private void pressOnPlayButton() {
        Image img = new Image("PlayPressed.jpg");
        btnPlay.setFill(new ImagePattern(img));
    }

    @FXML
    private void pressOnPauseButton() {
        Image img = new Image("PausePressed.jpg");
        btnPause.setFill(new ImagePattern(img));
    }

    @FXML
    private void pressOnStopButton() {
        Image img = new Image("StopPressed.jpg");
        btnStop.setFill(new ImagePattern(img));
    }

    private String formatTime(double time) {

        int numOfMinutes = 0;
        int numOfSeconds = 0;

        double temp = 0;

        numOfSeconds = (int)(time % 60);
        numOfMinutes = (int)(time / 60);
        return String.format("%02d:%02d", numOfMinutes, numOfSeconds);
    }
}

