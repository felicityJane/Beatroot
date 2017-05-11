package musicplayer.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import musicplayer.*;
import musicplayer.model.Album;
import musicplayer.model.MusicTrack;
import musicplayer.model.Rating;
import org.apache.commons.io.FilenameUtils;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
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
    @FXML private AnchorPane welcomeRootAnchor;
    @FXML private AnchorPane welcomeParentAnchorPane;
    @FXML private Label lblDisplayName;
    @FXML private ImageView imgProfilePicture;
    @FXML private AnchorPane anchorNews;
    @FXML private Rectangle imgNoConnection;
    @FXML private Label lblNoConnection1;
    @FXML private Label lblNoConnection2;
    @FXML private ImageView imgSearchIcon;
    @FXML private ComboBox cmbSearchMusic;
    @FXML private RadioButton rdSong;
    @FXML private RadioButton rdArtist;
    @FXML private RadioButton rdAlbum;
    @FXML private ImageView imgSearchUser;
    @FXML private Label lblNoMatchesFound;
    @FXML private ImageView imgRating;
    @FXML private ProgressIndicator progressDownload;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private String temp = "";
    private String selectedItem = "";
    private static String logInMenuPath = "view/logInMenu.fxml";
    private URL url;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private Server_Connector connector;
    private Album albumSelected;
    private MusicTrack trackPlaying;

    @FXML private MainMenuController mainMenuController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.setDisabledMenuItemsWelcomeScene();
        mainMenuController.menuBarFitToParent(welcomeParentAnchorPane);
        progressDownload = new ProgressBar(0);
        Image img = new Image("images/PlayNormal.jpg");
        btnPlay.setFill(new ImagePattern(img));
        Image img1 = new Image("images/StopNormal.jpg");
        btnStop.setFill(new ImagePattern(img1));
        Image img2 = new Image("images/PauseNormal.jpg");
        btnPause.setFill(new ImagePattern(img2));
        tglLoop.setText("⟳");
        /*String css = this.getClass().getResource("/musicplayer/css/welcomePage.css").toExternalForm();
        welcomeRootAnchor.getStylesheets().add(css);*/



        imgVolume.setImage(new Image("images/VolumeHigh.png"));
        imgProfilePicture.setImage(new Image("images/Konachan.jpg"));
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imgMain.setEffect(dropShadow);
        lblDisplayName.setText("Hello, " + db_connector.search("display_name", "premium_user", "user_name = 'Misstery'") + "!");
        imgSearchIcon.setImage(new Image("images/SearchIcon.png"));
        imgSearchUser.setImage(new Image("images/SearchIcon.png"));
        lblNoMatchesFound.setText("");
        imgRating.setImage(new Image("images/0Starz.png"));
        progressDownload.setVisible(false);

        imgSearchIcon.setOnMouseEntered(event -> {
            Scene scene = imgSearchIcon.getScene();
            scene.setCursor(Cursor.HAND);
        });

        imgSearchIcon.setOnMouseExited(event -> {
            Scene scene = imgSearchIcon.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });

        imgSearchUser.setOnMouseEntered(event -> {
            Scene scene = imgSearchIcon.getScene();
            scene.setCursor(Cursor.HAND);
        });

        imgSearchUser.setOnMouseExited(event -> {
            Scene scene = imgSearchIcon.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });

        imgSearchIcon.setOnMouseClicked(event -> {
            clickOnSearchIcon();
        });

        imgRating.setOnMouseEntered(event -> {
            Scene scene = imgSearchIcon.getScene();
            scene.setCursor(Cursor.HAND);
            double x = event.getX();
            if (x >= 0.0 && x <= 3.0 ) {
                imgRating.setImage(new Image("images/0Starz.png"));
            } else if (x > 3.0 && x <= 25.0 ) {
                imgRating.setImage(new Image("images/1Starz.png"));
            }else if (x > 25.0 && x <= 45.0 ) {
                imgRating.setImage(new Image("images/2Starz.png"));
            }else if (x > 45.0 && x <= 65.0 ) {
                imgRating.setImage(new Image("images/3Starz.png"));
            }else if (x > 65.0 && x <= 90.0 ) {
                imgRating.setImage(new Image("images/4Starz.png"));
            }else if (x > 90.0 && x <= 100.0 ) {
                imgRating.setImage(new Image("images/5Starz.png"));
            }
        });

        imgRating.setOnMouseExited(event -> {
            imgRating.setImage(new Image("images/0Starz.png"));
        });

        imgRating.setOnMouseClicked(event -> {
            Rating rating = new Rating(trackPlaying);
            double x = event.getX();
            rating.addSumFromAllVoters((float)x/20);
            rating.calculateRating();
            System.out.println(rating.getFinalRating());
        });

        for (Node n : welcomeRootAnchor.getChildren()) {

            if (n instanceof ImageView && n != imgMain && n != imgVolume && n != imgProfilePicture && n != imgSearchIcon
                    && n != imgSearchUser && n != imgRating) {

                DropShadow ds = new DropShadow(10, 0, 0, Color.GRAY);
                n.setEffect(ds);

                n.setOnMouseClicked(event -> {
                    clickOnImageView(n);
                });

                n.setOnMouseEntered(event ->  {
                    ColorAdjust brightness = new ColorAdjust();
                    DropShadow ds1 = new DropShadow(12, 0, 0, Color.GRAY);
                    brightness.setInput(ds1);
                    brightness.setBrightness(0.2);
                    n.setEffect(brightness);
                    Scene scene = imgSearchIcon.getScene();
                    scene.setCursor(Cursor.HAND);
                });
                n.setOnMouseExited(event -> {
                    ColorAdjust normal = new ColorAdjust();
                    normal.setInput(ds);
                    normal.setBrightness(0.0);
                    n.setEffect(normal);
                    Scene scene = imgSearchIcon.getScene();
                    scene.setCursor(Cursor.DEFAULT);
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

        for (Node n : anchorNews.getChildren()) {

            if (n instanceof ImageView) {

                DropShadow ds = new DropShadow(10, 0, 0, Color.GRAY);
                n.setEffect(ds);

                n.setOnMouseClicked(event -> {
                    clickOnImageView(n);
                });

                n.setOnMouseEntered(event ->  {
                    ColorAdjust brightness = new ColorAdjust();
                    DropShadow ds1 = new DropShadow(12, 0, 0, Color.GRAY);
                    brightness.setInput(ds1);
                    brightness.setBrightness(0.2);
                    n.setEffect(brightness);
                    Scene scene = imgSearchIcon.getScene();
                    scene.setCursor(Cursor.HAND);
                });
                n.setOnMouseExited(event -> {
                    ColorAdjust normal = new ColorAdjust();
                    normal.setInput(ds);
                    normal.setBrightness(0.0);
                    n.setEffect(normal);
                    Scene scene = imgSearchIcon.getScene();
                    scene.setCursor(Cursor.DEFAULT);
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


        cmbSearchMusic.getEditor().textProperty().addListener((obs, oldText, newText) -> {
            cmbSearchMusic.setValue(newText);

            cmbSearchMusic.show();
            ArrayList<String> sqlArrayList = new ArrayList<>();


            if (rdSong.isSelected()) {
                if (newText.equals("")) {
                    cmbSearchMusic.getItems().clear();
                } else {
                    for (String s : db_connector.searchMultipleResults("track_name", "music_track", "(track_name LIKE '%" + newText + "%' OR track_name LIKE '"
                            + newText + "%')")) {

                        if (!newText.equals("")) {
                            sqlArrayList.add(s);
                            cmbSearchMusic.getItems().clear();
                            cmbSearchMusic.getItems().addAll(sqlArrayList);
                        }
                    }
                    if (sqlArrayList.isEmpty()) {

                        cmbSearchMusic.getItems().clear();
                        cmbSearchMusic.getItems().add("No matches found");
                    }
                }
            } else if (rdArtist.isSelected()) {
                if (newText.equals("")) {
                    cmbSearchMusic.getItems().clear();
                } else {
                    for (String s : db_connector.searchMultipleResults("stage_name", "music_artist", "(stage_name LIKE '%" + newText + "%' OR stage_name LIKE '"
                            + newText + "%')")) {
                        if (!newText.equals("")) {
                            sqlArrayList.add(s);
                            cmbSearchMusic.getItems().clear();
                            cmbSearchMusic.getItems().addAll(sqlArrayList);
                        }
                    }
                    if (sqlArrayList.isEmpty()) {

                        cmbSearchMusic.getItems().clear();
                        cmbSearchMusic.getItems().add("No matches found");
                    }

                }
            }else if (rdAlbum.isSelected()) {
                if (newText.equals("")) {
                    cmbSearchMusic.getItems().clear();
                } else {
                    for (String s : db_connector.searchMultipleResults("album_name", "album", "(album_name LIKE '%" + newText + "%' OR album_name LIKE '"
                            + newText + "%')")) {

                        if (!newText.equals("")) {
                            sqlArrayList.add(s);
                            cmbSearchMusic.getItems().clear();
                            cmbSearchMusic.getItems().addAll(sqlArrayList);
                        }
                    }
                    if (sqlArrayList.isEmpty()) {
                        cmbSearchMusic.getItems().clear();
                        cmbSearchMusic.getItems().add("No matches found");
                    }

                }
            }
        });

        //imgNoConnection.setVisible(false);
        //lblNoConnection1.setVisible(false);
        //lblNoConnection2.setVisible(false);
        setImageNews();
        setImageSuggestions();
        setFirstSong();
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

        selectedItem = albumSelected.getSongs().get(lstMainTracks.getSelectionModel().getSelectedIndex()).getTrackName();
        //plays song only on double click
        if (selectedItem.equals(temp)) {
            String songUrl = db_connector.search("track_url", "music_track",
                    "track_name = " + "'" + selectedItem.replaceAll("'", "''") + "'");
            MusicTrack mt = new MusicTrack(selectedItem, songUrl);
            trackPlaying = mt;
            try {
                url = new URL(songUrl);
                mediaPlayer.stop();media = new Media(songUrl);
                mediaPlayer = new MediaPlayer(media);
                sliderVolume.setValue(mediaPlayer.getVolume() * 100);
                connector = new Server_Connector(songUrl, url);
                connector.connectToServer();
                System.out.println(connector.getFileSize(url));
                Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
                runMediaPlayer(path);
                mediaPlayer.play();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            temp = albumSelected.getSongs().get(lstMainTracks.getSelectionModel().getSelectedIndex()).getTrackName();
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
            //imgNoConnection.setVisible(true);
            //lblNoConnection2.setVisible(true);
            //lblNoConnection1.setVisible(true);
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
    private void clickOnImageView(Node n) {

        mediaPlayer.stop();
        String imgUrl = ((ImageView)n).getImage().impl_getUrl();
        int albumId = Integer.parseInt(db_connector.search("album_id", "album", "album_cover_path = " + "'" + imgUrl + "'"));
        String albumName = db_connector.search("album_name", "album", "album_id = " + Integer.toString(albumId));
        Album album = new Album(albumName, new Image(imgUrl));
        albumSelected = album;
        String artistName;

        int musicId;
        ArrayList<MusicTrack> tempArray = new ArrayList<>();
        musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                "album_album_id = " + Integer.toString(albumId)));
        artistName = db_connector.search("music_artist_artist_id", "music_track", "track_id = " + Integer.toString(musicId));
        artistName = db_connector.search("stage_name", "music_artist", "artist_id = " + artistName);

        MusicTrack mt = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
        tempArray.add(mt);

        for (int i = 0; i < 15; i++) {
            musicId--;

            try {
                musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                        "album_album_id = " + Integer.toString(albumId) + " AND music_track_track_id = "
                                + Integer.toString(musicId)));
                if (musicId != 0) {
                    MusicTrack mt1 = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                    tempArray.add(mt1);
                }
            } catch (NumberFormatException ne) {
                break;
            }
        }

        for (int i = tempArray.size() - 1; i >= 0; i--) {
            album.addSongs(tempArray.get(i));
           albumSelected.addSongs(tempArray.get(i));
        }

        lstMainTracks.getItems().clear();
        for (MusicTrack m : album.getSongs()) {
            String trackLength = db_connector.search("track_length",
                    "music_track", "track_name = '" + m.getTrackName().replaceAll("'", "''") + "'");
            String lstTrackInfo = String.format("%-20s %-5s", m.getTrackName(), trackLength.substring(3));
            lstMainTracks.getItems().add(lstTrackInfo);
        }

        imgMain.setImage(album.getAlbumCover());
        lblTrackName.setText(album.getSongs().get(0).getTrackName());
        lblTrackArtist.setText(artistName);

        try {
            url = new URL(album.getSongs().get(0).getUrl());
            trackPlaying = album.getSongs().get(0);
            mediaPlayer.stop();
            media = new Media(url.toString());
            mediaPlayer = new MediaPlayer(media);
            sliderVolume.setValue(mediaPlayer.getVolume() * 100);
            connector = new Server_Connector(url.toString(), url);
            connector.connectToServer();
            Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
            runMediaPlayer(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setImageNews() {

        int counter = Integer.parseInt(db_connector.search("album_id", "album", "album_id = (SELECT MAX(album_id) FROM album)"));

        for (Node n : anchorNews.getChildren()) {

            if (n instanceof ImageView) {

               String imgUrl = db_connector.search("album_cover_path", "album", "album_id = " + Integer.toString(counter));
               ((ImageView) n).setImage(new Image(imgUrl));
               counter--;
            }
        }
    }

    private void setImageSuggestions() {

        Random rnd = new Random();

        int counter = rnd.nextInt(16) + 1;

        for (Node n : welcomeRootAnchor.getChildren()) {

            if (n instanceof ImageView && n != imgMain && n != imgVolume && n != imgProfilePicture && n != imgSearchIcon
                    && n != imgSearchUser && n != imgRating) {

                String imgUrl = db_connector.search("album_cover_path", "album", "album_id = " + Integer.toString(counter));
                ((ImageView) n).setImage(new Image(imgUrl));
                counter++;
                if (counter > 16) {
                    counter = 1;
                }
            }
        }

    }

    private void setFirstSong() {

        String imgUrl = "http://www.webshare.hkr.se/FECO0002/Ellie%20Goulding%20-%20Halcyon%20Days.png";
        int albumId = Integer.parseInt(db_connector.search("album_id", "album", "album_cover_path = " + "'" + imgUrl + "'"));
        String albumName = db_connector.search("album_name", "album", "album_id = " + Integer.toString(albumId));
        Album album = new Album(albumName, new Image(imgUrl));
        albumSelected = album;
        String artistName = "";

        int musicId;
        ArrayList<MusicTrack> tempArray = new ArrayList<>();
        musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                "album_album_id = " + Integer.toString(albumId)));
        artistName = db_connector.search("music_artist_artist_id", "music_track", "track_id = " + Integer.toString(musicId));
        artistName = db_connector.search("stage_name", "music_artist", "artist_id = " + artistName);

        MusicTrack mt = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
        tempArray.add(mt);

        for (int i = 0; i < 15; i++) {
            musicId--;

            try {
                musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                        "album_album_id = " + Integer.toString(albumId) + " AND music_track_track_id = "
                                + Integer.toString(musicId)));
                if (musicId != 0) {
                    MusicTrack mt1 = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                    tempArray.add(mt1);
                }
            } catch (NumberFormatException ne) {
                break;
            }
        }

        for (int i = tempArray.size() - 1; i >= 0; i--) {
            album.addSongs(tempArray.get(i));
            albumSelected.addSongs(tempArray.get(i));
        }

        lstMainTracks.getItems().clear();
        for (MusicTrack m : album.getSongs()) {
            lstMainTracks.getItems().add(m.getTrackName());
        }

        imgMain.setImage(album.getAlbumCover());
        lblTrackName.setText(album.getSongs().get(0).getTrackName());
        lblTrackArtist.setText(artistName);

        try {
            url = new URL(album.getSongs().get(0).getUrl());
            trackPlaying = album.getSongs().get(0);
            //mediaPlayer.stop();
            media = new Media(url.toString());
            mediaPlayer = new MediaPlayer(media);
            sliderVolume.setValue(mediaPlayer.getVolume() * 100);
            connector = new Server_Connector(url.toString(), url);

            connector.connectToServer();
            Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
            runMediaPlayer(path);
        } catch (Exception ex) {
            ex.printStackTrace();
            imgNoConnection.setVisible(true);
            lblNoConnection2.setVisible(true);
            lblNoConnection1.setVisible(true);
        }
    }

    private void clickOnSearchIcon() {

        if (rdSong.isSelected()) {

            String trackSearched = cmbSearchMusic.getEditor().getText();
            if (!db_connector.search("track_name",
                    "music_track", "track_name = '" + trackSearched + "'").equals("")) {
                lblNoMatchesFound.setText("");
                int trackId = Integer.parseInt(db_connector.search("track_id", "music_track",
                        "track_name = '" + cmbSearchMusic.getEditor().getText() + "'"));
                int albumId = Integer.parseInt(db_connector.search("album_album_id",
                        "album_has_music_track", "music_track_track_id = " + Integer.toString(trackId)));
                String albumName = db_connector.search("album_name", "album", "album_id = " +
                Integer.toString(albumId));
                String albumCoverPath = db_connector.search("album_cover_path", "album",
                        "album_id = " + Integer.toString(albumId));

                Album album = new Album(albumName, new Image(albumCoverPath));
                albumSelected = album;

                int musicId;
                ArrayList<MusicTrack> tempArray = new ArrayList<>();
                musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                        "album_album_id = " + Integer.toString(albumId)));
                String artistName = db_connector.search("music_artist_artist_id", "music_track", "track_id = " + Integer.toString(musicId));
                artistName = db_connector.search("stage_name", "music_artist", "artist_id = " + artistName);


                MusicTrack mt = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                tempArray.add(mt);

                for (int i = 0; i < 15; i++) {
                    musicId--;

                    try {
                        musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                                "album_album_id = " + Integer.toString(albumId) + " AND music_track_track_id = "
                                        + Integer.toString(musicId)));
                        if (musicId != 0) {
                            MusicTrack mt1 = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                            tempArray.add(mt1);
                        }
                    } catch (NumberFormatException ne) {
                        break;
                    }
                }

                for (int i = tempArray.size() - 1; i >= 0; i--) {
                    album.addSongs(tempArray.get(i));
                    albumSelected.getSongs().clear();
                    albumSelected.addSongs(tempArray.get(i));
                }

                lstMainTracks.getItems().clear();
                for (MusicTrack m : album.getSongs()) {
                    lstMainTracks.getItems().add(m.getTrackName());
                }

                imgMain.setImage(album.getAlbumCover());
                lblTrackName.setText(trackSearched);
                lblTrackArtist.setText(artistName);

                int index = 0;
                for (int i = 0; i < 20; i++) {
                    if (album.getSongs().get(i).getTrackName().equals(trackSearched)) {
                        index = i;
                        break;
                    }
                }

                try {
                    url = new URL(album.getSongs().get(index).getUrl());
                    trackPlaying = album.getSongs().get(index);
                    mediaPlayer.stop();
                    media = new Media(url.toString());
                    mediaPlayer = new MediaPlayer(media);
                    sliderVolume.setValue(mediaPlayer.getVolume() * 100);
                    connector = new Server_Connector(url.toString(), url);
                    connector.connectToServer();
                    Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
                    runMediaPlayer(path);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                lblNoMatchesFound.setText("No matches found.");
            }
        } else if (rdAlbum.isSelected()) {
            String albumSearched = cmbSearchMusic.getEditor().getText();
            if (!db_connector.search("album_name",
                    "album", "album_name = '" + albumSearched + "'").equals("")) {
                lblNoMatchesFound.setText("");
                loadAlbum(albumSearched, 0);
            } else {
                lblNoMatchesFound.setText("No matches found.");
            }
        } else if (rdArtist.isSelected()) {
            String artistSearched = cmbSearchMusic.getEditor().getText();
            if (!db_connector.search("stage_name",
                    "music_artist", "stage_name = '" + artistSearched + "'").equals("")) {
                lblNoMatchesFound.setText("");
                int artistId = Integer.parseInt(db_connector.search("artist_id", "music_artist",
                        "stage_name = '" + artistSearched + "'"));
               ArrayList<Integer> musicIds = new ArrayList<>();
               for (String s : db_connector.searchMultipleResults("track_id", "music_track",
                        "music_artist_artist_id = " + Integer.toString(artistId))) {
                   musicIds.add(Integer.parseInt(s));
                }

                ArrayList<Integer> albumIds = new ArrayList<>();
               for (Integer i : musicIds) {
                   if (!albumIds.contains(Integer.parseInt(db_connector.search("album_album_id",
                           "album_has_music_track", "music_track_track_id = " +
                   Integer.toString(i))))) {
                       albumIds.add(Integer.parseInt(db_connector.search("album_album_id",
                               "album_has_music_track", "music_track_track_id = " +
                                       Integer.toString(i))));
                   }
               }
               cmbSearchMusic.show();
               cmbSearchMusic.getItems().clear();
               ArrayList<String> albumNames = new ArrayList<>();
               for (Integer i : albumIds) {
                   albumNames.add(db_connector.search("album_name", "album",
                           "album_id = " + Integer.toString(i)));
               }
                cmbSearchMusic.getItems().addAll(albumNames);

               rdAlbum.setSelected(true);
            } else {
                lblNoMatchesFound.setText("No matches found.");
            }
        }

    }

    private void loadAlbum(String searchedItem, int songIndex) {

        int albumId = Integer.parseInt(db_connector.search("album_id", "album", "album_name = '" +
                searchedItem + "'"));
        String albumCoverPath = db_connector.search("album_cover_path", "album",
                "album_id = " + Integer.toString(albumId));

        Album album = new Album(searchedItem, new Image(albumCoverPath));
        albumSelected = album;
        int musicId;
        ArrayList<MusicTrack> tempArray = new ArrayList<>();
        musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                "album_album_id = " + Integer.toString(albumId)));
        String artistName = db_connector.search("music_artist_artist_id", "music_track", "track_id = " + Integer.toString(musicId));
        artistName = db_connector.search("stage_name", "music_artist", "artist_id = " + artistName);


        MusicTrack mt = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
        tempArray.add(mt);

        for (int i = 0; i < 15; i++) {
            musicId--;

            try {
                musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                        "album_album_id = " + Integer.toString(albumId) + " AND music_track_track_id = "
                                + Integer.toString(musicId)));
                if (musicId != 0) {
                    MusicTrack mt1 = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                    tempArray.add(mt1);
                }
            } catch (NumberFormatException ne) {
                break;
            }
        }

        for (int i = tempArray.size() - 1; i >= 0; i--) {
            album.addSongs(tempArray.get(i));
           albumSelected.addSongs(tempArray.get(i));
        }

        lstMainTracks.getItems().clear();
        for (MusicTrack m : album.getSongs()) {
            lstMainTracks.getItems().add(m.getTrackName());
        }

        imgMain.setImage(album.getAlbumCover());
        lblTrackName.setText(searchedItem);
        lblTrackArtist.setText(artistName);



        loadMediaPlayer(0, album);
    }

    private void loadMediaPlayer(int index, Album album) {
        try {
            url = new URL(album.getSongs().get(index).getUrl());
            trackPlaying = album.getSongs().get(0);
            mediaPlayer.stop();
            media = new Media(url.toString());
            mediaPlayer = new MediaPlayer(media);
            sliderVolume.setValue(mediaPlayer.getVolume() * 100);
            connector = new Server_Connector(url.toString(), url);
            connector.connectToServer();
            Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
            runMediaPlayer(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}



