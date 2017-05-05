package musicplayer.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.Server_Connector;
import musicplayer.TemporaryAlbumClass;
import org.apache.commons.io.FilenameUtils;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
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
    @FXML private ImageView imgNews1;
    @FXML private ImageView imgNews2;
    @FXML private ImageView imgNews3;
    @FXML private ImageView imgNews4;
    @FXML private ImageView imgNews5;
    @FXML private ImageView imgNews6;
    @FXML private ListView<String> lstMainTracks;
    @FXML private AnchorPane welcomeRootAnchor;
    @FXML private AnchorPane welcomeParentAnchorPane;
    private TemporaryAlbumClass tempAlbum2 = new TemporaryAlbumClass();
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private String temp = "";
    private String selectedItem = "";
    private static String logInMenuPath = "view/logInMenu.fxml";
    private URL url;


    @FXML private MainMenuController mainMenuController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.setDisabledMenuItemsWelcomeScene();
        mainMenuController.menuBarFitToParent(welcomeParentAnchorPane);

        //***********************************************************
        Server_Connector connector = new Server_Connector();
        connector.connectToServer();
        //***********************************************************


        Image img = new Image("images/PlayNormal.jpg");
       btnPlay.setFill(new ImagePattern(img));
        Image img1 = new Image("images/StopNormal.jpg");
        btnStop.setFill(new ImagePattern(img1));
        Image img2 = new Image("images/PauseNormal.jpg");
        btnPause.setFill(new ImagePattern(img2));
        tglLoop.setText("⟳");
        /*String css = this.getClass().getResource("/musicPlayer/CSS/welcomePage.css").toExternalForm();
        welcomeRootAnchor.getStylesheets().add(css);*/

        TemporaryAlbumClass tempAlbum = new TemporaryAlbumClass();
        tempAlbum.getTracks().add("01. Celldweller - Faction 04 .mp3");
        tempAlbum.getTracks().add("02. Celldweller - Down to Earth .mp3");
        tempAlbum.getTracks().add("03. Celldweller - Heart On .mp3");
        tempAlbum.getTracks().add("04. Celldweller - Faction 05 .mp3");
        tempAlbum.getTracks().add("05. Celldweller - Faction 06 .mp3");
        tempAlbum.setAlbumCover(new Image("images/Celldweller_EoaE_BG_LOVE.jpg"));
        tempAlbum2.getTracks().add("05 - I'LL BE GONE.mp3");
        tempAlbum2.getTracks().add("06 - CASTLE OF GLASS.mp3");
        tempAlbum2.getTracks().add("08 - ROADS UNTRAVELED.mp3");
        tempAlbum2.setAlbumCover(new Image("images/Linkin Park - Living Things.jpg"));
        Path path = Paths.get(tempAlbum.getTracks().get(0));
        lstMainTracks.getItems().addAll(tempAlbum.getTracks());
        imgVolume.setImage(new Image("images/VolumeHigh.png"));

        imgMain.setImage(tempAlbum.getAlbumCover());
        imgNews1.setImage(new Image("images/Linkin Park - Living Things.jpg"));
        imgNews2.setImage(new Image("images/Konachan.jpg"));
        imgNews3.setImage(new Image("images/EyeGirl.jpg"));
        imgNews4.setImage(new Image("images/Hadean.jpg"));
        imgNews5.setImage(new Image("images/Celldweller_EoaE_BG_LOVE.jpg"));
        imgNews6.setImage(new Image("images/Rage Against the Machine - Rage Against the Machine.jpg"));
        imgMain.setImage(tempAlbum.getAlbumCover());



        for (Node n : welcomeRootAnchor.getChildren()) {

            if (n instanceof ImageView) {

                DropShadow ds = new DropShadow(10, 0, 0, Color.GRAY);
                n.setEffect(ds);

                n.setOnMouseEntered(event ->  {
                    ColorAdjust brightness = new ColorAdjust();
                    DropShadow ds1 = new DropShadow(12, 0, 0, Color.GRAY);
                    brightness.setInput(ds1);
                    brightness.setBrightness(0.2);
                    n.setEffect(brightness);
                });
                n.setOnMouseExited(event -> {
                    ColorAdjust normal = new ColorAdjust();
                    normal.setInput(ds);
                    normal.setBrightness(0.0);
                    n.setEffect(normal);
                });
                n.setOnMousePressed(event ->  {
                    ColorAdjust blackout = new ColorAdjust();
                    DropShadow ds1 = new DropShadow(12, 0, 0, Color.GRAY);
                    blackout.setInput(ds1);
                    blackout.setBrightness(-0.3);
                    n.setEffect(blackout);
                });
                n.setOnMouseReleased(event -> {
                    ColorAdjust normal = new ColorAdjust();
                    normal.setInput(ds);
                    normal.setBrightness(0.0);
                    n.setEffect(normal);
                });
            }
        }

        try {
            url = new URL("http://www.webshare.hkr.se/FECO0002/alice%20in%20chains%20-%2001%20-%20them%20bones.mp3");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        media = new Media("http://www.webshare.hkr.se/FECO0002/alice%20in%20chains%20-%2001%20-%20them%20bones.mp3");
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
                mediaPlayer.stop();

                SceneManager.sceneManager.changeScene(event,"view/logInMenu.fxml");

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
        Image img = new Image("images/PlayHover.jpg");
        btnPlay.setFill(new ImagePattern(img));
    }

    @FXML
    private void hoverOnPauseButton() {
        Image img = new Image("images/PauseHover.jpg");
        btnPause.setFill(new ImagePattern(img));
    }

    @FXML
    private void hoverOnStopButton() {
        Image img = new Image("images/StopHover.jpg");
        btnStop.setFill(new ImagePattern(img));
    }

    @FXML
    private void leavePlayButton() {
        Image img = new Image("images/PlayNormal.jpg");
        btnPlay.setFill(new ImagePattern(img));
    }

    @FXML
    private void leavePauseButton() {
        Image img = new Image("images/PauseNormal.jpg");
        btnPause.setFill(new ImagePattern(img));
    }

    @FXML
    private void leaveStopButton() {
        Image img = new Image("images/StopNormal.jpg");
        btnStop.setFill(new ImagePattern(img));
    }

    @FXML
    private void pressOnPlayButton() {
        Image img = new Image("images/PlayPressed.jpg");
        btnPlay.setFill(new ImagePattern(img));
    }

    @FXML
    private void pressOnPauseButton() {
        Image img = new Image("images/PausePressed.jpg");
        btnPause.setFill(new ImagePattern(img));
    }

    @FXML
    private void pressOnStopButton() {
        Image img = new Image("images/StopPressed.jpg");
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
                            mediaPlayer.setCycleCount(1);
                            lblTimeLeft.setText(formatTime(mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer.getCurrentTime().toSeconds()));

                            if (!tglLoop.isSelected()) {

                                if (mediaPlayer.getTotalDuration().toMinutes() - mediaPlayer.getCurrentTime().toMinutes() <= 0.01) {
                                    mediaPlayer.stop();
                                }
                                mediaPlayer.setCycleCount(1);

                            } else if (tglLoop.isSelected()) {
                                mediaPlayer.setOnEndOfMedia(new Runnable() {
                                    public void run() {
                                       mediaPlayer.seek(Duration.ZERO);
                                    }
                                });
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
                imgVolume.setImage(new Image("images/VolumeOff.png"));
            } else if (sliderVolume.getValue() > 0.0 && sliderVolume.getValue() < 40) {
                imgVolume.setImage(new Image("images/VolumeLow.png"));
            } else if (sliderVolume.getValue() >= 40 && sliderVolume.getValue() < 85) {
                imgVolume.setImage(new Image("images/VolumeMed.png"));
            } else {
                imgVolume.setImage(new Image("images/VolumeHigh.png"));
            }
        });

        lblElapsedTime.setText("00:00");
        lblTimeLeft.setText(formatTime(mediaPlayer.getTotalDuration().toSeconds()));
        Metadata metadata = new Metadata();

        //reads mp3 file's metadata
        try {
            InputStream input = new FileInputStream(new File("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " "))));
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

    @FXML
    private void clickOnImageView() {

        lstMainTracks.getItems().clear();
        lstMainTracks.getItems().addAll(tempAlbum2.getTracks());
        imgMain.setImage(tempAlbum2.getAlbumCover());

    }

}

