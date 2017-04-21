package musicPlayer.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import musicPlayer.DialogBoxManager;
import musicPlayer.SceneManager;
import musicPlayer.TemporaryAlbumClass;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    @FXML private Label lblTrackArtist;
    @FXML private Slider sliderPlay;
    @FXML private Slider sliderVolume;
    @FXML private ToggleButton tglLoop;
    @FXML private ImageView imgVolume;
    @FXML private ImageView imgMain;
    @FXML private ListView<String> lstMainTracks;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private String temp = "";
    private String selectedItem = "";

    @FXML private MainMenuController mainMenuController;
    static final String loginMenuPath = "View/logInMenu.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.menuItemsWelcomeScene();

        Image img = new Image("PlayNormal.jpg");
        btnPlay.setFill(new ImagePattern(img));
        Image img1 = new Image("StopNormal.jpg");
        btnStop.setFill(new ImagePattern(img1));
        Image img2 = new Image("PauseNormal.jpg");
        btnPause.setFill(new ImagePattern(img2));
        tglLoop.setText("⟳");

        TemporaryAlbumClass tempAlbum = new TemporaryAlbumClass();
        tempAlbum.getTracks().add("01. Celldweller - Faction 04 .mp3");
        tempAlbum.getTracks().add("02. Celldweller - Down to Earth .mp3");
        tempAlbum.getTracks().add("03. Celldweller - Heart On .mp3");
        tempAlbum.getTracks().add("04. Celldweller - Faction 05 .mp3");
        tempAlbum.getTracks().add("05. Celldweller - Faction 06 .mp3");
        tempAlbum.setAlbumCover(new Image("Celldweller_EoaE_BG_LOVE.jpg"));
        Path path = Paths.get(tempAlbum.getTracks().get(0));
        lstMainTracks.getItems().addAll(tempAlbum.getTracks());
        imgVolume.setImage(new Image("VolumeHigh.png"));
        imgMain.setImage(tempAlbum.getAlbumCover());

        media = new Media(path.toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        sliderVolume.setValue(mediaPlayer.getVolume() * 100);

        runMediaPlayer(path);
    }

    @FXML
    protected void onLogOutButtonPressed(ActionEvent event){
        boolean answer = DialogBoxManager.confirmationDialogBox("Are you sure you want to log out?","click ok to continue");
        if (answer){
            try {
                SceneManager.sceneManager.changeScene(event,loginMenuPath);
            }catch (Exception e){
                DialogBoxManager.errorDialogBox("Error occurred","Changing from welcome scene to log in scene");
                e.printStackTrace();
            }
        }
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

        if (currentStatus == MediaPlayer.Status.PLAYING || currentStatus == MediaPlayer.Status.STOPPED) {
            mediaPlayer.stop();
        }

        lblElapsedTime.setText("00:00");
        lblTimeLeft.setText(formatTime(mediaPlayer.getTotalDuration().toSeconds()));

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

        int numOfMinutes;
        int numOfSeconds;
        int numOfHours;

        if (time > 3600) { //longer than 1 hour
            numOfSeconds = (int)(time % 60);
            time /= 60;
            numOfMinutes = (int)(time % 60);
            numOfHours = (int)(time / 60);
            return String.format("%02d:%02d:%02d", numOfHours, numOfMinutes, numOfSeconds);
        } else {
            numOfSeconds = (int)(time % 60);
            numOfMinutes = (int)(time / 60);
            return String.format("%02d:%02d", numOfMinutes, numOfSeconds);
        }
    }

    @FXML
    private void clickOnListViewMainTracks() {

        selectedItem = lstMainTracks.getSelectionModel().getSelectedItem();

        //plays song only on double click
        if (selectedItem.equals(temp)) {
            Path path = Paths.get(lstMainTracks.getSelectionModel().getSelectedItem());
            mediaPlayer.stop();
            media = new Media(path.toUri().toString());
            mediaPlayer = new MediaPlayer(media);
            sliderVolume.setValue(mediaPlayer.getVolume() * 100);
            runMediaPlayer(path);
            mediaPlayer.play();
        } else {
            temp = lstMainTracks.getSelectionModel().getSelectedItem();
        }
    }

    private void runMediaPlayer(Path path) {

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

                            if (!tglLoop.isSelected()) {

                                if (mediaPlayer.getTotalDuration().toMinutes() - mediaPlayer.getCurrentTime().toMinutes() <= 0.01) {
                                    mediaPlayer.stop();
                                }
                                mediaPlayer.setCycleCount(1);

                            } else if (tglLoop.isSelected()) {
                                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                            }
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

        sliderVolume.valueProperty().addListener(observable -> {
            mediaPlayer.setVolume(sliderVolume.getValue() / 100);
            if (sliderVolume.getValue() == 0.0) {
                imgVolume.setImage(new Image("VolumeOff.png"));
            } else if (sliderVolume.getValue() > 0.0 && sliderVolume.getValue() < 40) {
                imgVolume.setImage(new Image("VolumeLow.png"));
            } else if (sliderVolume.getValue() >= 40 && sliderVolume.getValue() < 85) {
                imgVolume.setImage(new Image("VolumeMed.png"));
            } else {
                imgVolume.setImage(new Image("VolumeHigh.png"));
            }
        });

        lblElapsedTime.setText("00:00");
        lblTimeLeft.setText(formatTime(mediaPlayer.getTotalDuration().toSeconds()));
        Metadata metadata = new Metadata();

        //reads mp3 file's metadata
        try {
            InputStream input = new FileInputStream(new File(path.getFileName().toString()));
            ContentHandler handler = new DefaultHandler();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();
        } catch (Exception fe) {
            fe.printStackTrace();
        }



        if (metadata.get("title") != null && metadata.get("xmpDM:artist") != null) { //if the mp3 file is tagged with metadata
            lblTrackName.setText(metadata.get("title")); //displays metadata
            lblTrackArtist.setText(metadata.get("xmpDM:artist"));
        } else {
            String fileName = path.getFileName().toString();
            fileName = fileName.substring(0, fileName.length() - 4); //removes ".mp3" from file name
            lblTrackName.setText(fileName);
        }


    }
}

