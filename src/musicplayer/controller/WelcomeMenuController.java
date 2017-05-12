package musicplayer.controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import musicplayer.*;
import musicplayer.model.Album;
import musicplayer.model.GlobalVariables;
import musicplayer.model.MusicTrack;
import org.apache.commons.io.FilenameUtils;
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
    @FXML private ImageView imgNews1;
    @FXML private ImageView imgNews2;
    @FXML private ImageView imgNews3;
    @FXML private ImageView imgNews4;
    @FXML private ImageView imgNews5;
    @FXML private ImageView imgNews6;
    @FXML private ImageView imgNews7;
    @FXML private ImageView imgSugg1;
    @FXML private ImageView imgSugg2;
    @FXML private ImageView imgSugg3;
    @FXML private ImageView imgSugg4;
    @FXML private ImageView imgSugg5;
    @FXML private ImageView imgSugg6;
    @FXML private ImageView imgSugg7;
    @FXML private ListView<String> lstMainTracks;
    @FXML private AnchorPane welcomeRootAnchor;
    @FXML private AnchorPane welcomeParentAnchorPane;
    @FXML private Label lblDisplayName;
    @FXML private ImageView imgProfilePicture;
    @FXML private AnchorPane anchorNews;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private String temp = "";
    private String selectedItem = "";
    private static String logInMenuPath = "view/logInMenu.fxml";
    private URL url;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    private Server_Connector connector;
    private Album album;

    @FXML private MainMenuController mainMenuController;
    @FXML private AlbumPageController albumPageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainMenuController.init(this);
        mainMenuController.setDisabledMenuItemsWelcomeScene();
        mainMenuController.menuBarFitToParent(welcomeParentAnchorPane);
        //albumPageController.init(this);
        Image img = new Image("images/PlayNormal.jpg");
        btnPlay.setFill(new ImagePattern(img));
        Image img1 = new Image("images/StopNormal.jpg");
        btnStop.setFill(new ImagePattern(img1));
        Image img2 = new Image("images/PauseNormal.jpg");
        btnPause.setFill(new ImagePattern(img2));
        tglLoop.setText("⟳");
        /*String css = this.getClass().getResource("/musicPlayer/CSS/welcomePage.css").toExternalForm();
        welcomeRootAnchor.getStylesheets().add(css);*/
        Path path;


        imgVolume.setImage(new Image("images/VolumeHigh.png"));
        imgProfilePicture.setImage(new Image("images/Konachan.jpg"));
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imgMain.setEffect(dropShadow);
        lblDisplayName.setText("Hello, " + db_connector.search("display_name", "premium_user", "user_name = 'Misstery'") + "!");



        for (Node n : welcomeRootAnchor.getChildren()) {

            if (n instanceof ImageView && n != imgMain && n != imgVolume && n != imgProfilePicture) {

                DropShadow ds = new DropShadow(10, 0, 0, Color.GRAY);
                n.setEffect(ds);

                n.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY){
                        clickOnImageView(n);
                    }else if (event.getButton() == MouseButton.SECONDARY){
                        ImageView im = (ImageView) event.getSource();
                        popUpMenu(im);
                    }
                });

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

        /*try {
            url = new URL("http://www.webshare.hkr.se/FECO0002/Ellie%20Goulding%20-%20Burn.mp3");
            path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
            imgMain.setImage(new Image("http://www.webshare.hkr.se/FECO0002/Ellie%20Goulding%20-%20Halcyon%20Days.png"));
            runMediaPlayer(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        media = new Media("http://www.webshare.hkr.se/FECO0002/Ellie%20Goulding%20-%20Burn.mp3");
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        connector = new Server_Connector(url.toString(), url);
        connector.connectToServer();
        sliderVolume.setValue(mediaPlayer.getVolume() * 100);*/

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

        selectedItem = lstMainTracks.getSelectionModel().getSelectedItem();
        //plays song only on double click
        if (selectedItem.equals(temp)) {
            String songUrl = db_connector.search("track_url", "music_track",
                    "track_name = " + "'" + selectedItem + "'");
            try {
                url = new URL(songUrl);
                mediaPlayer.stop();
                media = new Media(songUrl);
                mediaPlayer = new MediaPlayer(media);
                sliderVolume.setValue(mediaPlayer.getVolume() * 100);
                connector = new Server_Connector(songUrl, url);
                connector.connectToServer();
                Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
                runMediaPlayer(path);
                mediaPlayer.play();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

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
    private void clickOnImageView(Node n) {

        mediaPlayer.stop();
        String imgUrl = ((ImageView)n).getImage().impl_getUrl();
        int albumId = Integer.parseInt(db_connector.search("album_id", "album", "album_cover_path = " + "'" + imgUrl + "'"));
        String albumName = db_connector.search("album_name", "album", "album_id = " + Integer.toString(albumId));
        Album album = new Album(albumName, new Image(imgUrl));
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

            if (n instanceof ImageView && n != imgMain && n != imgVolume && n != imgProfilePicture) {

                String imgUrl = db_connector.search("album_cover_path", "album", "album_id = " + Integer.toString(counter));
                ((ImageView) n).setImage(new Image(imgUrl));
                //set image id to be able to search database for album info
                n.setId(String.valueOf(counter));
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
        album = new Album(albumName, new Image(imgUrl));
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
        }
    }
    private void popUpMenu(ImageView imageView){
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem songPage = new MenuItem("See song info");
        final MenuItem artistPage = new MenuItem("See artist info");
        final MenuItem albumPage = new MenuItem("See album info");
        contextMenu.getItems().addAll(songPage,artistPage,albumPage);


        GlobalVariables context = GlobalVariables.getInstance();
        for (Node n : welcomeRootAnchor.getChildren()) {

            if (n instanceof ImageView && n != imgMain && n != imgVolume && n != imgProfilePicture) {
                n.setOnContextMenuRequested(event -> contextMenu.show(n, event.getScreenX(), event.getScreenY()));
            }
        }
        songPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneManager.sceneManager.changeSceneMenuItem(welcomeParentAnchorPane,"view/songPage.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        artistPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneManager.sceneManager.changeSceneMenuItem(welcomeParentAnchorPane,"view/artistPage.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        albumPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String imgUrl = db_connector.search("album_cover_path", "album", "album_id = " + imageView.getId());
                    context.setAlbumCover(imgUrl);
                    SceneManager.sceneManager.changeSceneMenuItem(welcomeParentAnchorPane,"view/albumPage.fxml");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

