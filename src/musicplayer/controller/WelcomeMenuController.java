package musicplayer.controller;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import musicplayer.*;
import musicplayer.model.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static musicplayer.SceneManager.sceneManager;


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
    @FXML private Circle imgProfilePicture;
    @FXML private AnchorPane anchorNews;
    @FXML private Rectangle imgNoConnection;
    @FXML private Label lblNoConnection1;
    @FXML private Label lblNoConnection2;
    @FXML private ImageView imgSearchIcon;
    @FXML private ComboBox cmbSearchMusic;
    @FXML private ComboBox cmbSearchUser;
    @FXML private RadioButton rdSong;
    @FXML private RadioButton rdArtist;
    @FXML private RadioButton rdAlbum;
    @FXML private ImageView imgSearchUser;
    @FXML private Label lblNoMatchesFound;
    @FXML private ImageView imgRating;
    @FXML private ProgressIndicator progressDownload; //= new ProgressBar(ProgressIndicator.INDETERMINATE_PROGRESS);
    @FXML private Circle btnDownload;
    @FXML private Circle btnLogOut;
    @FXML private Label lblRating;
    @FXML private Circle btnPen;
    @FXML private Circle btnAddPlaylist;
    @FXML private Circle btnRemovePlaylist;
    @FXML private ListView lstPlaylists;
    @FXML private ListView<MusicTrack> lstPlaylistSongs;
    @FXML private Circle btnMessage;
    @FXML private ListView<PremiumUser> lstContacts;
    @FXML private RadioButton rdPremium;
    @FXML private RadioButton rdTrial;
    @FXML private Label lblContacts;
    @FXML private ImageView imgLogo;
    private String userDisplayName;
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
    private Album albumSelected;
    private MusicTrack trackPlaying;
    private int starsOn;
    private Rating currentSongRating;
    private String userName;
    private ArrayList<Playlist> userPlaylists = new ArrayList<>();
    private Node selectedNodeForAddingToPlaylist;
    private Playlist selectedPlaylist;
    private int tempPlaylist;
    private boolean unreadMessage = false;
    GlobalVariables globalVariables;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalVariables = GlobalVariables.getInstance();
        globalVariables.setWelcomeMenuController(this);
        globalVariables.getMainMenuController().menuBarFitToParent(welcomeParentAnchorPane);
        globalVariables.getMainMenuController().enableMenuItems();

        Image img = new Image("images/PlayNormal.jpg");
        progressDownload = new ProgressBar(0);
        btnPlay.setFill(new ImagePattern(img));
        Image img1 = new Image("images/StopNormal.jpg");
        btnStop.setFill(new ImagePattern(img1));
        Image img2 = new Image("images/PauseNormal.jpg");
        btnPause.setFill(new ImagePattern(img2));
        tglLoop.setText("⟳");
        readUserFromBinaryFile();
        imgLogo.setImage(new Image("images/beatrootlogo.png"));
        /*String css = this.getClass().getResource("/musicplayer/css/welcomePage.css").toExternalForm();
        welcomeRootAnchor.getStylesheets().add(css);*/


        if(globalVariables.getPremiumUser()!= null && globalVariables.getTrialuser() == null && globalVariables.getAdministrator() == null) {
            imgRating.setVisible(true);
            lblDisplayName.setText(" " + globalVariables.getPremiumUser().getDisplayName()+ "!");
            btnMessage.setVisible(true);
            try {
                Image image = new Image(globalVariables.getPremiumUser().getProfilePicturePath());
                imgProfilePicture.setFill(new ImagePattern(image));
            } catch (IllegalArgumentException ie) {
                db_connector.update("premium_user", "personal_picture_path",
                        "'http://www.webshare.hkr.se/FECO0002/default-user.png'", "user_name = '"
                                + globalVariables.getPremiumUser().getUserName() + "'");
                globalVariables.getPremiumUser().setProfilePicturePath("http://www.webshare.hkr.se/FECO0002/default-user.png");
                Image image = new Image(globalVariables.getPremiumUser().getProfilePicturePath());
                imgProfilePicture.setFill(new ImagePattern(image));
            }
            lstContacts.setVisible(true);
            lblContacts.setVisible(true);
        } else if (globalVariables.getTrialuser() != null && globalVariables.getPremiumUser() == null && globalVariables.getAdministrator() == null) {
            imgRating.setVisible(true);
            lblDisplayName.setText(" " + globalVariables.getTrialuser().getDisplayName() + "!");
            btnPen.setVisible(false);
            btnMessage.setVisible(false);
           try {
                Image image = new Image(globalVariables.getTrialuser().getProfilePicturePath());
                imgProfilePicture.setFill(new ImagePattern(image));
            } catch (IllegalArgumentException ie) {
               db_connector.update("trial_user", "personal_picture_path",
                       "'http://www.webshare.hkr.se/FECO0002/default-user.png'", "user_name = '"
               + globalVariables.getTrialuser().getUserName() + "'");
               globalVariables.getTrialuser().setProfilePicturePath("http://www.webshare.hkr.se/FECO0002/default-user.png");
               Image image = new Image(globalVariables.getTrialuser().getProfilePicturePath());
               imgProfilePicture.setFill(new ImagePattern(image));
           }
            lstContacts.setVisible(false);
            lblContacts.setVisible(false);
        } else if (globalVariables.getAdministrator() != null && globalVariables.getPremiumUser() == null && globalVariables.getTrialuser() == null) {
            imgRating.setVisible(true);
            lblDisplayName.setText(" " + globalVariables.getAdministrator().getDisplayName()+ "!");
            btnMessage.setVisible(false);
           try {
                Image image = new Image(globalVariables.getAdministrator().getProfilePicturePath());
                imgProfilePicture.setFill(new ImagePattern(image));
            } catch (IllegalArgumentException ie) {
               db_connector.update("administrator", "personal_picture_path",
                       "'http://www.webshare.hkr.se/FECO0002/default-user.png'", "staff_id = '"
                               + globalVariables.getAdministrator().getStaffID() + "'");
               globalVariables.getAdministrator().setProfilePicturePath("http://www.webshare.hkr.se/FECO0002/default-user.png");
               Image image = new Image(globalVariables.getAdministrator().getProfilePicturePath());
               imgProfilePicture.setFill(new ImagePattern(image));
           }
            lstContacts.setVisible(true);
            lblContacts.setVisible(true);
        }
        imgVolume.setImage(new Image("images/VolumeHigh.png"));
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imgMain.setEffect(dropShadow);

        imgSearchIcon.setImage(new Image("images/search_icon.jpg"));
        imgSearchUser.setImage(new Image("images/search_icon.jpg"));
        lblNoMatchesFound.setText("");
        Image img3 = new Image("images/arrow-download-icon.png");
        btnDownload.setFill(new ImagePattern(img3));
        Image img4 = new Image("images/log-off-icon.png");
        btnLogOut.setFill(new ImagePattern(img4));
        Image img5 = new Image("images/pencil.png");
        btnPen.setFill(new ImagePattern(img5));
        Tooltip.install(
                btnPen,
                new Tooltip("Add a comment to the song")
        );
        Image img6 = new Image("images/add.png");
        btnAddPlaylist.setFill(new ImagePattern(img6));
        Tooltip.install(
                btnAddPlaylist,
                new Tooltip("Create playlist")
        );
        Image img7 = new Image("images/remove.png");
        btnRemovePlaylist.setFill(new ImagePattern(img7));
        Tooltip.install(
                btnRemovePlaylist,
                new Tooltip("Remove playlist")
        );

        lstPlaylistSongs.setCellFactory(listView -> {

            ImageTextCellMusicTrack cell = new ImageTextCellMusicTrack();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().bind(Bindings.format("Delete"));
            deleteItem.setOnAction(event -> {
                selectedPlaylist.getMusicTracks().remove(cell.getItem());
                userPlaylists.get(lstPlaylists.getSelectionModel().getSelectedIndex()).removeMusicTracks(cell.getItem());
                db_connector.delete("playlist_has_music_track", "music_track_track_id = " +
                cell.getItem().getID());
                listView.getItems().remove(cell.getItem());
                setPlaylistSongs();
                setPlaylists();
            });
            contextMenu.getItems().addAll(deleteItem);
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });

            return cell;
        });
        lstContacts.setCellFactory(listView -> {

            ImageTextCellContacts cell = new ImageTextCellContacts();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().bind(Bindings.format("Remove from contacts"));
            deleteItem.setOnAction(event -> {

                boolean answer = DialogBoxManager.confirmationDialogBox("Remove contact?", "Are you sure you want to remove this contact?");
                if (answer) {
                    globalVariables.getContactList().remove(cell.getItem());
                    db_connector.delete("premium_user_has_contact", "contact_contact_name = '" +
                            cell.getItem().getUserName() + "' AND premium_user_user_name = '" +
                            globalVariables.getPremiumUser().getUserName() + "'");
                    db_connector.delete("premium_user_has_contact", "premium_user_user_name = '" +
                            cell.getItem().getUserName() + "' AND contact_contact_name = '" +
                            globalVariables.getPremiumUser().getUserName() + "'");
                    listView.getItems().remove(cell.getItem());
                    setContacts();
                }
            });
            contextMenu.getItems().addAll(deleteItem);
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });

            return cell;

        });

        setImageNews();
        setImageSuggestions();
        setFirstSong();
        setRatingStars(lblRating,currentSongRating,imgRating);
        setPlaylists();
        setMessages();

        if (globalVariables.getPremiumUser() != null && globalVariables.getTrialuser() == null && globalVariables.getAdministrator() == null) {
            setContacts();
        }

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

        lblDisplayName.setOnMouseEntered(event -> {
            Scene scene = imgSearchIcon.getScene();
            scene.setCursor(Cursor.HAND);
        });

        lblDisplayName.setOnMouseExited(event -> {
            Scene scene = imgSearchIcon.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
        lblDisplayName.setOnMouseClicked(event -> {
            globalVariables.setContactDescriptionController(null);
            globalVariables.setOwnUserDescriptionController(new UserDescriptionController());
            try {
                if (globalVariables.getPremiumUser() != null && globalVariables.getAdministrator() == null &&
                        globalVariables.getTrialuser() == null) {
                     SceneManager.sceneManager.openNewWindow( "view/userDescription.fxml", globalVariables.getPremiumUser().getDisplayName());
                } else if (globalVariables.getTrialuser()!= null && globalVariables.getPremiumUser() == null &&
                        globalVariables.getAdministrator() == null) {
                    SceneManager.sceneManager.openNewWindow( "view/userDescription.fxml", globalVariables.getTrialuser().getDisplayName());
                } else if (globalVariables.getAdministrator()!= null && globalVariables.getPremiumUser() == null &&
                        globalVariables.getTrialuser() == null) {
                    SceneManager.sceneManager.openNewWindow( "view/userDescription.fxml", globalVariables.getAdministrator().getDisplayName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnDownload.setOnMouseEntered(event -> {
            Scene scene = btnDownload.getScene();
            scene.setCursor(Cursor.HAND);
        });

        btnDownload.setOnMouseExited(event -> {
            Scene scene = btnDownload.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });

        btnLogOut.setOnMouseEntered(event -> {
            Scene scene = btnLogOut.getScene();
            scene.setCursor(Cursor.HAND);
        });

        btnLogOut.setOnMouseExited(event -> {
            Scene scene = btnLogOut.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
        btnPen.setOnMouseEntered(event -> {
            Scene scene = btnPen.getScene();
            scene.setCursor(Cursor.HAND);
        });

        btnPen.setOnMouseExited(event -> {
            Scene scene = btnPen.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
        btnMessage.setOnMouseEntered(event -> {
            Scene scene = btnMessage.getScene();
            scene.setCursor(Cursor.HAND);
        });

        btnMessage.setOnMouseExited(event -> {
            Scene scene = btnMessage.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
        btnAddPlaylist.setOnMouseEntered(event -> {
            Scene scene = btnAddPlaylist.getScene();
            scene.setCursor(Cursor.HAND);
        });

        btnAddPlaylist.setOnMouseExited(event -> {
            Scene scene = btnAddPlaylist.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });
        btnRemovePlaylist.setOnMouseEntered(event -> {
            Scene scene = btnRemovePlaylist.getScene();
            scene.setCursor(Cursor.HAND);
        });

        btnRemovePlaylist.setOnMouseExited(event -> {
            Scene scene = btnRemovePlaylist.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });

        lblTrackName.setOnMouseEntered(event -> {
            Scene scene = lblTrackName.getScene();
            scene.setCursor(Cursor.HAND);
        });

        lblTrackName.setOnMouseExited(event -> {
            Scene scene = lblTrackName.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });

        lstMainTracks.setOnMouseEntered(event -> {
            Scene scene = lstMainTracks.getScene();
            scene.setCursor(Cursor.HAND);
        });

        lstMainTracks.setOnMouseExited(event -> {
            Scene scene = lstMainTracks.getScene();
            scene.setCursor(Cursor.DEFAULT);
        });



        imgRating.setOnMouseEntered(event -> {

            if ((globalVariables.getAdministrator() != null && globalVariables.getPremiumUser() == null && globalVariables.getTrialuser() == null) ||
                    (globalVariables.getPremiumUser() != null && globalVariables.getAdministrator() == null && globalVariables.getTrialuser() == null)) {
                Scene scene = imgSearchIcon.getScene();
                scene.setCursor(Cursor.HAND);
                double x = event.getX();
                if (x >= 0.0 && x <= 3.0) {
                    imgRating.setImage(new Image("images/0Starz.png"));
                    starsOn = 0;
                } else if (x > 3.0 && x <= 25.0) {
                    imgRating.setImage(new Image("images/1Starz.png"));
                    starsOn = 1;
                } else if (x > 25.0 && x <= 45.0) {
                    imgRating.setImage(new Image("images/2Starz.png"));
                    starsOn = 2;
                } else if (x > 45.0 && x <= 65.0) {
                    imgRating.setImage(new Image("images/3Starz.png"));
                    starsOn = 3;
                } else if (x > 65.0 && x <= 90.0) {
                    imgRating.setImage(new Image("images/4Starz.png"));
                    starsOn = 4;
                } else if (x > 90.0 && x <= 100.0) {
                    imgRating.setImage(new Image("images/5Starz.png"));
                    starsOn = 5;
                }
            }
        });

        imgRating.setOnMouseExited(event -> {
            Scene scene = imgSearchIcon.getScene();
            scene.setCursor(Cursor.DEFAULT);
            setRatingStars(lblRating,currentSongRating,imgRating);
        });

        lblTrackName.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                selectedNodeForAddingToPlaylist = lblTrackName;
                popUpMenuSong(lblTrackName);
            }
        });


        imgRating.setOnMouseClicked(event -> {

            if ((globalVariables.getAdministrator() != null && globalVariables.getPremiumUser() == null && globalVariables.getTrialuser() == null) ||
                    (globalVariables.getPremiumUser() != null && globalVariables.getAdministrator() == null && globalVariables.getTrialuser() == null)) {
                currentSongRating.addSumFromAllVoters(starsOn);
                currentSongRating.setNumberOfVoters(Integer.parseInt(db_connector.search("sum_from_all_voters",
                        "rating", "rating_id = " + Integer.toString(currentSongRating.getRatingID()))) + 1);
                currentSongRating.calculateRating();
                db_connector.update("rating", "final_rating", Double.toString(currentSongRating.getFinalRating()),
                        "rating_id = " + Integer.toString(currentSongRating.getRatingID()));
                db_connector.update("rating", "sum_from_all_voters",
                        Integer.toString(currentSongRating.getNumberOfVoters()), "rating_id = " + Integer.toString(currentSongRating.getRatingID()));
                System.out.println(currentSongRating.getFinalRating());
                writeMusicTrackToBinaryFile();
                try {
                    sceneManager.openNewWindow( "view/commentWindow.fxml", "Add comment");
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        });


        for (Node n : welcomeRootAnchor.getChildren()) {

           if (n instanceof ListView && n != lstPlaylists && n != lstContacts && n != lstPlaylistSongs && n != imgLogo) {
               n.setOnMouseClicked(event -> {
                   if (event.getButton() == MouseButton.SECONDARY) {
                       popUpMenuGoToSongInfo(n);
                   }else {
                       clickOnListViewMainTracks();
                   }
               });
           }
            if (n instanceof ImageView && n != imgSearchIcon && n != imgRating && n != imgSearchUser && n != imgLogo ){
                n.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY){
                        popUpMenuAlbumPage(n);
                    }
                });
            }if (n instanceof ImageView && n != imgMain && n != imgVolume && n != imgProfilePicture && n != imgSearchIcon
                    && n != imgSearchUser && n != imgRating && n != imgLogo) {

                DropShadow ds = new DropShadow(10, 0, 0, Color.GRAY);
                n.setEffect(ds);

                n.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY){
                        clickOnImageView(n);
                    }if (event.getButton() == MouseButton.SECONDARY){
                        popUpMenuAlbumPage(n);
                    }
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

            if (n instanceof ImageView && n != imgLogo) {

                DropShadow ds = new DropShadow(10, 0, 0, Color.GRAY);
                n.setEffect(ds);

                n.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY){
                        clickOnImageView(n);
                    }else if (event.getButton() == MouseButton.SECONDARY){
                        popUpMenuAlbumPage(n);
                    }
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
                    for (String s : db_connector.searchMultipleResults("track_name", "music_track", "(track_name LIKE '%" + newText.replaceAll("'", "''") + "%' OR track_name LIKE '"
                            + newText.replaceAll("'", "''") + "%')")) {

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

        cmbSearchUser.getEditor().textProperty().addListener((obs, oldText, newText) -> {
            cmbSearchUser.setValue(newText);

            cmbSearchUser.show();
            ArrayList<String> sqlArrayList = new ArrayList<>();

            if (rdPremium.isSelected()) {
                if (newText.equals("")) {
                    cmbSearchUser.getItems().clear();
                } else {
                    for (String s : db_connector.searchMultipleResults("user_name", "premium_user", "(user_name LIKE '%" + newText.replaceAll("'", "''") + "%' OR user_name LIKE '"
                            + newText.replaceAll("'", "''") + "%')")) {

                        if (!newText.equals("")) {
                            sqlArrayList.add(s);
                            cmbSearchUser.getItems().clear();
                            cmbSearchUser.getItems().addAll(sqlArrayList);
                        }
                    }
                    if (sqlArrayList.isEmpty()) {

                        cmbSearchUser.getItems().clear();
                        cmbSearchUser.getItems().add("No matches found");
                    }
                }
            }else if (rdTrial.isSelected()) {
                if (newText.equals("")) {
                    cmbSearchUser.getItems().clear();
                } else {
                    for (String s : db_connector.searchMultipleResults("user_name", "trial_user", "(user_name LIKE '%" + newText + "%' OR user_name LIKE '"
                            + newText + "%')")) {

                        if (!newText.equals("")) {
                            sqlArrayList.add(s);
                            cmbSearchUser.getItems().clear();
                            cmbSearchUser.getItems().addAll(sqlArrayList);
                        }
                    }
                    if (sqlArrayList.isEmpty()) {
                        cmbSearchUser.getItems().clear();
                        cmbSearchUser.getItems().add("No matches found");
                    }

                }
            }
        });


//        imgNoConnection.setVisible(false);
//        lblNoConnection1.setVisible(false);
//        lblNoConnection2.setVisible(false);

    }

    @FXML
    protected void onLogOutButtonPressed(MouseEvent event){
        boolean answer = DialogBoxManager.confirmationDialogBox("Are you sure you want to log out?","click ok to continue");
        if (answer){
            try {
                mediaPlayer.stop();
                GlobalVariables.getInstance().getContactList().clear();
                sceneManager.changeScene(event,"view/logInMenu.fxml");

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
            mt.setID(Integer.parseInt(db_connector.search("track_id", "music_track", "track_name = '" +
                    selectedItem.replaceAll("'", "''") + "'")));
            trackPlaying = mt;
            currentSongRating = new Rating(mt);
            int ratingId = Integer.parseInt(db_connector.search("rating_id", "music_track",
                    "track_name = '" + mt.getTrackName().replaceAll("'", "''") + "'"));
            currentSongRating = new Rating(mt, Double.parseDouble(db_connector.search("final_rating",
                    "rating", "rating_id = " + Integer.toString(ratingId))));
            currentSongRating.setRatingID(ratingId);
            int product = (Integer.parseInt(db_connector.search("sum_from_all_voters", "rating", "rating_id = " + Integer.toString(ratingId)))) * (int)(Double.parseDouble(db_connector.search("final_rating",
                    "rating", "rating_id = " + Integer.toString(ratingId))) + 0.5);
            currentSongRating.setSumFromAllVoters(product);
            setRatingStars(lblRating,currentSongRating,imgRating);
            try {
                url = new URL(songUrl);
                mediaPlayer.stop();
                media = new Media(songUrl);
                mediaPlayer = new MediaPlayer(media);
                sliderVolume.setValue(mediaPlayer.getVolume() * 100);
                File dir = new File("tmp");
                File[] matches = dir.listFiles(new FilenameFilter()
                {
                    public boolean accept(File dir, String name)
                    {
                        return name.startsWith(url.toString().substring(36).replaceAll("%20", " ")) && name.endsWith(".mp3");
                    }
                });
                if (matches.length == 0) {
                    connector = new Server_Connector(url.toString(), url);
                    progressDownload.visibleProperty().bind(connector.runningProperty());
                    connector.restart();
                }
                Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
                runMediaPlayer(path);
                mediaPlayer.play();
                lblTrackName.setText(mt.getTrackName());
            } catch (Exception ex) {
                ex.printStackTrace();
//                imgNoConnection.setVisible(true);
//                lblNoConnection2.setVisible(true);
//                lblNoConnection1.setVisible(true);
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
            System.out.println("It does not find the file but it's okay.");
//            imgNoConnection.setVisible(true);
//            lblNoConnection2.setVisible(true);
//            lblNoConnection1.setVisible(true);
        }



        /*if (metadata.get("title") != null && metadata.get("xmpDM:artist") != null) { //if the mp3 file is tagged with metadata
                lblTrackName.setText(metadata.get("title")); //displays metadata
                lblTrackArtist.setText(metadata.get("xmpDM:artist"));
         } else {
                String fileName = path.getFileName().toString();
                fileName = fileName.substring(0, fileName.length() - 4); //removes ".mp3" from file name
                lblTrackName.setText(fileName);
         }*/
    }

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
        mt.setID(musicId);
        tempArray.add(mt);

        for (int i = 0; i < 15; i++) {
            musicId--;

            try {
                musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                        "album_album_id = " + Integer.toString(albumId) + " AND music_track_track_id = "
                                + Integer.toString(musicId)));
                if (musicId != 0) {
                    MusicTrack mt1 = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                    mt1.setID(musicId);
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

        int ratingId = Integer.parseInt(db_connector.search("rating_id", "music_track",
                "track_name = '" + album.getSongs().get(0).getTrackName().replaceAll("'", "''") + "'"));
        currentSongRating = new Rating(album.getSongs().get(0), Double.parseDouble(db_connector.search("final_rating",
                "rating", "rating_id = " + Integer.toString(ratingId))));
        currentSongRating.setRatingID(ratingId);
        int product = (Integer.parseInt(db_connector.search("sum_from_all_voters", "rating", "rating_id = " + Integer.toString(ratingId)))) * (int)(Double.parseDouble(db_connector.search("final_rating",
                "rating", "rating_id = " + Integer.toString(ratingId))) + 0.5);
        currentSongRating.setSumFromAllVoters(product);
        setRatingStars(lblRating,currentSongRating,imgRating);

        lstMainTracks.getItems().clear();
        for (MusicTrack m : album.getSongs()) {
            String trackLength = db_connector.search("track_length",
                    "music_track", "track_name = '" + m.getTrackName().replaceAll("'", "''") + "'");
            String indent = "                                                                ";
            String lstTrackInfo = String.format("%-150s%s", m.getTrackName(),trackLength.substring(3));
            lstMainTracks.getItems().add(m.getTrackName());
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
            File dir = new File("tmp");
            File[] matches = dir.listFiles(new FilenameFilter()
            {
                public boolean accept(File dir, String name)
                {
                    return name.startsWith(url.toString().substring(36).replaceAll("%20", " ")) && name.endsWith(".mp3");
                }
            });
            if (matches.length == 0)  {
                connector = new Server_Connector(url.toString(), url);
               // progressDownload.visibleProperty().bind(connector.runningProperty());
                connector.restart();
            }
            Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
            runMediaPlayer(path);
        } catch (Exception ex) {
            ex.printStackTrace();
//            imgNoConnection.setVisible(true);
//            lblNoConnection2.setVisible(true);
//            lblNoConnection1.setVisible(true);
        }
    }

    private void setImageNews() {

        int counter = Integer.parseInt(db_connector.search("album_id", "album", "album_id = (SELECT MAX(album_id) FROM album)"));

        for (Node n : anchorNews.getChildren()) {

            if (n instanceof ImageView) {
               String imgUrl = db_connector.search("album_cover_path", "album", "album_id = " + Integer.toString(counter));
               ((ImageView) n).setImage(new Image(imgUrl));
                //set image id to be able to search database for album info
                n.setId(String.valueOf(counter));
               counter--;
            }
        }
    }

    private void setImageSuggestions() {

        Random rnd = new Random();

        int counter = rnd.nextInt(16) + 1;

        for (Node n : welcomeRootAnchor.getChildren()) {

            if (n instanceof ImageView && n != imgMain && n != imgVolume && n != imgProfilePicture && n != imgSearchIcon
                    && n != imgSearchUser && n != imgRating && n != imgLogo) {

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
        albumSelected = album;
        String artistName = "";

        int musicId;
        ArrayList<MusicTrack> tempArray = new ArrayList<>();
        musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                "album_album_id = " + Integer.toString(albumId)));
        artistName = db_connector.search("music_artist_artist_id", "music_track", "track_id = " + Integer.toString(musicId));
        artistName = db_connector.search("stage_name", "music_artist", "artist_id = " + artistName);

        MusicTrack mt = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
        mt.setID(musicId);
        tempArray.add(mt);

        for (int i = 0; i < 15; i++) {
            musicId--;

            try {
                musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                        "album_album_id = " + Integer.toString(albumId) + " AND music_track_track_id = "
                                + Integer.toString(musicId)));
                if (musicId != 0) {
                    MusicTrack mt1 = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                    mt1.setID(musicId);
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


        int ratingId = Integer.parseInt(db_connector.search("rating_id", "music_track",
                "track_name = '" + album.getSongs().get(0).getTrackName() + "'"));
        currentSongRating = new Rating(album.getSongs().get(0), Double.parseDouble(db_connector.search("final_rating",
                "rating", "rating_id = " + Integer.toString(ratingId))));
        int product = (Integer.parseInt(db_connector.search("sum_from_all_voters", "rating", "rating_id = " + Integer.toString(ratingId)))) * (int)(Double.parseDouble(db_connector.search("final_rating",
                "rating", "rating_id = " + Integer.toString(ratingId))) + 0.5);
        currentSongRating.setSumFromAllVoters(product);
        currentSongRating.setRatingID(ratingId);

        lstMainTracks.getItems().clear();
        for (MusicTrack m : album.getSongs()) {
            String trackLength = db_connector.search("track_length",
                    "music_track", "track_name = '" + m.getTrackName().replaceAll("'", "''") + "'");
            String indent = "                                                                ";
            String lstTrackInfo = String.format("%-150s%s", m.getTrackName(),trackLength.substring(3));
            lstMainTracks.getItems().add(m.getTrackName());
        }

        imgMain.setImage(album.getAlbumCover());
        //set image id to be able to search database for album info
        imgMain.setId(String.valueOf(albumId));
        lblTrackName.setText(album.getSongs().get(0).getTrackName());
        lblTrackArtist.setText(artistName);

        try {
            url = new URL(album.getSongs().get(0).getUrl());
            trackPlaying = album.getSongs().get(0);
            media = new Media(url.toString());
            mediaPlayer = new MediaPlayer(media);
            sliderVolume.setValue(mediaPlayer.getVolume() * 100);
            File dir = new File("tmp");
            File[] matches = dir.listFiles(new FilenameFilter()
            {
                public boolean accept(File dir, String name)
                {
                    return name.startsWith(url.toString().substring(36).replaceAll("%20", " ")) && name.endsWith(".mp3");
                }
            });
            if (matches.length == 0) {
                try {
                    connector = new Server_Connector(url.toString(), url);
                    //progressDownload.visibleProperty().bind(connector.runningProperty());
                    connector.restart();
                } catch (Exception ex) {
                    System.out.println("Exception caught at line 872");
//                    imgNoConnection.setVisible(true);
//                    lblNoConnection2.setVisible(true);
//                    lblNoConnection1.setVisible(true);
                }
            }
            Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
            runMediaPlayer(path);
        } catch (Exception ex) {
            ex.printStackTrace();
//            imgNoConnection.setVisible(true);
//            lblNoConnection2.setVisible(true);
//            lblNoConnection1.setVisible(true);
            System.out.println("Exception caught at line 881");
//            imgNoConnection.setVisible(true);
//            lblNoConnection2.setVisible(true);
//            lblNoConnection1.setVisible(true);
            ex.printStackTrace();
        }
    }

    private void popUpMenuAlbumPage(Node n){
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem albumPage = new MenuItem("See album information");
        contextMenu.getItems().addAll(albumPage);

        n.setOnContextMenuRequested(event -> contextMenu.show(n, event.getScreenX(), event.getScreenY()));

        albumPage.setOnAction(event -> {
            try {
                db_connector.getAlbumDetails(Integer.parseInt(n.getId()));
                db_connector.getTrackDetails(Integer.parseInt(n.getId()));
                sceneManager.popUpWindow( "view/albumPage.fxml");
            } catch (IOException e) {
                DialogBoxManager.errorDialogBox("error occured in welcome menu controller","an error has occurred changing to album page scene");
                e.printStackTrace();
            }
        });
    }

    public void popUpMenuGoToSongInfo( Node n){
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem seeSongInfo = new MenuItem("See song information");
        contextMenu.getItems().addAll(seeSongInfo);

        n.setOnContextMenuRequested(event -> contextMenu.show(n, event.getScreenX(), event.getScreenY()));

        seeSongInfo.setOnAction(event -> {
            try {
                Integer trackID = db_connector.getMusicTrackInfo(lstMainTracks.getSelectionModel().getSelectedItem());
                db_connector.getArtistDetails(trackID);
                Integer albumID = db_connector.getAlbumIdFromTrackId(trackID);
                db_connector.getAlbumDetails(albumID);
                sceneManager.popUpWindow( "view/songPage.fxml");
            } catch (IOException ie) {
                DialogBoxManager.errorDialogBox("error occured in welcome menu controller","an error has occurred changing to song page scene");
                ie.printStackTrace();
            }
        });
    }

    private void clickOnSearchIcon() {

        if (rdSong.isSelected()) {

            String trackSearched = cmbSearchMusic.getEditor().getText();
            if (!db_connector.search("track_name",
                    "music_track", "track_name = '" + trackSearched.replaceAll("'", "''") + "'").equals("")) {
                lblNoMatchesFound.setText("");
                int trackId = Integer.parseInt(db_connector.search("track_id", "music_track",
                        "track_name = '" + cmbSearchMusic.getEditor().getText().replaceAll("'", "''") + "'"));
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
                mt.setID(musicId);
                tempArray.add(mt);

                for (int i = 0; i < 15; i++) {
                    musicId--;

                    try {
                        musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                                "album_album_id = " + Integer.toString(albumId) + " AND music_track_track_id = "
                                        + Integer.toString(musicId)));
                        if (musicId != 0) {
                            MusicTrack mt1 = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                            mt1.setID(musicId);
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
                int ratingId = Integer.parseInt(db_connector.search("rating_id", "music_track",
                        "track_name = '" + trackSearched.replaceAll("'", "''") + "'"));
                currentSongRating = new Rating(album.getSongs().get(0), Double.parseDouble(db_connector.search("final_rating",
                        "rating", "rating_id = " + Integer.toString(ratingId))));
                currentSongRating.setRatingID(ratingId);
                int product = (Integer.parseInt(db_connector.search("sum_from_all_voters", "rating", "rating_id = " + Integer.toString(ratingId)))) * (int)(Double.parseDouble(db_connector.search("final_rating",
                        "rating", "rating_id = " + Integer.toString(ratingId))) + 0.5);
                currentSongRating.setSumFromAllVoters(product);
                setRatingStars(lblRating,currentSongRating,imgRating);

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
                    sliderVolume.setValue(mediaPlayer.getVolume() * 100); File dir = new File("tmp");
                    File[] matches = dir.listFiles(new FilenameFilter()
                    {
                        public boolean accept(File dir, String name)
                        {
                            return name.startsWith(url.toString().substring(36).replaceAll("%20", " ")) && name.endsWith(".mp3");
                        }
                    });
                    if (matches.length == 0) {
                        try {
                            connector = new Server_Connector(url.toString(), url);
                            //progressDownload.visibleProperty().bind(connector.runningProperty());
                            connector.restart();
                        } catch (Exception ex) {
                            System.out.println("Exception caught at line 872");
//                    imgNoConnection.setVisible(true);
//                    lblNoConnection2.setVisible(true);
//                    lblNoConnection1.setVisible(true);
                        }
                    }
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
        mt.setID(musicId);
        tempArray.add(mt);

        for (int i = 0; i < 15; i++) {
            musicId--;

            try {
                musicId = Integer.parseInt(db_connector.search("music_track_track_id", "album_has_music_track",
                        "album_album_id = " + Integer.toString(albumId) + " AND music_track_track_id = "
                                + Integer.toString(musicId)));
                if (musicId != 0) {
                    MusicTrack mt1 = new MusicTrack(db_connector.search("track_name", "music_track", "track_id = " + musicId), db_connector.search("track_url", "music_track", "track_id = " + musicId));
                    mt1.setID(musicId);
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
        int ratingId = Integer.parseInt(db_connector.search("rating_id", "music_track",
                "track_name = '" + album.getSongs().get(0).getTrackName().replaceAll("'", "''") + "'"));
        currentSongRating = new Rating(album.getSongs().get(0), Double.parseDouble(db_connector.search("final_rating",
                "rating", "rating_id = " + Integer.toString(ratingId))));
        currentSongRating.setRatingID(ratingId);
        int product = (Integer.parseInt(db_connector.search("sum_from_all_voters", "rating", "rating_id = " + Integer.toString(ratingId)))) * (int)(Double.parseDouble(db_connector.search("final_rating",
                "rating", "rating_id = " + Integer.toString(ratingId))) + 0.5);
        currentSongRating.setSumFromAllVoters(product);
        setRatingStars(lblRating,currentSongRating,imgRating);
        lstMainTracks.getItems().clear();
        for (MusicTrack m : album.getSongs()) {
            lstMainTracks.getItems().add(m.getTrackName());
        }

        imgMain.setImage(album.getAlbumCover());
        lblTrackName.setText(album.getSongs().get(0).getTrackName());
        lblTrackArtist.setText(artistName);



        loadMediaPlayer(0, album);
    }

    private void loadMediaPlayer(int index, Album album) {
        try {
            url = new URL(album.getSongs().get(index).getUrl());
            trackPlaying = album.getSongs().get(index);
            mediaPlayer.stop();
            media = new Media(url.toString());
            mediaPlayer = new MediaPlayer(media);
            sliderVolume.setValue(mediaPlayer.getVolume() * 100);
            File dir = new File("tmp");
            File[] matches = dir.listFiles(new FilenameFilter()
            {
                public boolean accept(File dir, String name)
                {
                    return name.startsWith(url.toString().substring(36).replaceAll("%20", " ")) && name.endsWith(".mp3");
                }
            });
            if (matches.length == 0) {
                try {
                    connector = new Server_Connector(url.toString(), url);
                    //progressDownload.visibleProperty().bind(connector.runningProperty());
                    connector.restart();
                } catch (Exception ex) {
                    System.out.println("Exception caught at line 872");
//                    imgNoConnection.setVisible(true);
//                    lblNoConnection2.setVisible(true);
//                    lblNoConnection1.setVisible(true);
                }
            }
            Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
            runMediaPlayer(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clickOnUserName(MouseEvent mouseEvent, String userName){


    }

    protected void setRatingStars(Label lblRating, Rating currentSongRating, ImageView imgRating) {


        switch ((int)currentSongRating.getFinalRating()) {
            case 0:
                imgRating.setImage(new Image("images/0Starz.png"));
                break;
            case 1:
                imgRating.setImage(new Image("images/1Starz.png"));
                break;
            case 2:
                imgRating.setImage(new Image("images/2Starz.png"));
                break;
            case 3:
                imgRating.setImage(new Image("images/3Starz.png"));
                break;
            case 4:
                imgRating.setImage(new Image("images/4Starz.png"));
                break;
            case 5:
                imgRating.setImage(new Image("images/5Starz.png"));
                break;
        }

        lblRating.setText(String.format("%d/5", (int)currentSongRating.getFinalRating()));

    }
    @FXML
    private void onDownloadButtonPressed() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(imgMain.getScene().getWindow());

        if(file != null){
            try {
                URL url = new URL(trackPlaying.getUrl());
                Server_Connector sc = new Server_Connector(trackPlaying.getUrl(), url, file);
                //progressDownload.visibleProperty().bind(sc.runningProperty());
                sc.restart();
            } catch (MalformedURLException me) {
                me.printStackTrace();
            }
        }
    }

    @FXML
    private void onBtnPenPressed(MouseEvent e) {

        writeMusicTrackToBinaryFile();
        try {
            sceneManager.openNewWindow( "view/commentWindow.fxml", "Add comment");
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    private void writeMusicTrackToBinaryFile() {
        try (FileOutputStream fs = new FileOutputStream("MusicTrack.bin"); ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(trackPlaying);
        } catch (FileNotFoundException fe) {
            DialogBoxManager.errorDialogBox("File not found", "File not found. Try again.");
        } catch (IOException ie) {
            DialogBoxManager.errorDialogBox("Cannot create file", "Error with creating file.");
        }
    }

    private void readUserFromBinaryFile() {

        try {
            Path path = Paths.get("UserName.bin");
            java.util.List<String> userInfo = Files.readAllLines(path);
            userName = userInfo.get(0);
            userDisplayName = userInfo.get(2);
        } catch (IOException ie) {
            DialogBoxManager.errorDialogBox("Cannot read user info", "Cannot access user info from UserName.bin");
        }
    }

    @FXML
    private void onBtnAddPlaylistPressed(MouseEvent e) {
        try {
            Stage childStage = sceneManager.openNewWindowReturnStage( "view/playlistWindow.fxml", "Create playlist");
            childStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    setPlaylists();
                }
            });
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    @FXML
    private void onBtnRemovePlaylistPressed(MouseEvent e) {

        if (lstPlaylists.getSelectionModel().getSelectedItem() != null) {
            boolean answer = DialogBoxManager.confirmationDialogBox("Are you sure you want to remove this playlist?","click ok to continue");
            if (answer){
                db_connector.delete("playlist_has_music_track", "playlist_playlist_id = " +
                Integer.toString(userPlaylists.get(lstPlaylists.getSelectionModel().getSelectedIndex()).getId()));
                db_connector.delete("playlist", "playlist_id = " +
                Integer.toString(userPlaylists.get(lstPlaylists.getSelectionModel().getSelectedIndex()).getId()));
                setPlaylists();
            }
        } else {
            DialogBoxManager.errorDialogBox("Error", "Please select a playlist first.");
        }
    }

    private void setPlaylists() {

        lstPlaylists.getItems().clear();
        userPlaylists = new ArrayList<>();
        int counter = 0;
        for (String s : db_connector.searchMultipleResults("playlist_id", "playlist", "owner = '" + userName
        + "'")) {
            userPlaylists.add(new Playlist(db_connector.search("name", "playlist", "owner = '" + userName + "' AND playlist_id = " + s), PrivacyLevel.values()[Integer.parseInt(db_connector.search("privacy_level_privacy_id", "playlist",
                    "owner = '" + userName + "' AND playlist_id = " + s))]));
            userPlaylists.get(counter).setId(Integer.parseInt(s));
            userPlaylists.get(counter).setVisibility(PrivacyLevel.values()[Integer.parseInt(db_connector.search("privacy_level_privacy_id",
                    "playlist", "owner = '" + userName + "' AND playlist_id = " + s))]);
            counter++;
        }

        for (Playlist p : userPlaylists) {
            int nOfEntries = Integer.parseInt(db_connector.search("COUNT(playlist_playlist_id)", "playlist_has_music_track",
                    "playlist_playlist_id = " + Integer.toString(p.getId())));
            db_connector.update("playlist", "number_of_entries", Integer.toString(nOfEntries),
                    "playlist_id = " + Integer.toString(p.getId()));
            p.setNumberOfEntries(Integer.parseInt(db_connector.search("number_of_entries", "playlist",
                    "playlist_id = " + Integer.toString(p.getId()))));
        }

        fillUpLstPlaylists(userPlaylists);
    }

    private void fillUpLstPlaylists(ArrayList<Playlist> userPlaylists) {

        for (Playlist p : userPlaylists) {
            lstPlaylists.getItems().add(p.getName() + "   - " + p.getVisibility() + "\n" + p.getNumberOfEntries() + " tracks");
        }
    }

    private void popUpMenuSong(Node n) {
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem addToPlaylist = new MenuItem("Add song to playlist >");
        contextMenu.getItems().addAll(addToPlaylist);

        n.setOnContextMenuRequested(event -> contextMenu.show(n, event.getScreenX(), event.getScreenY()));

        addToPlaylist.setOnAction(event -> {
            try {
                Stage childStage = sceneManager.openNewWindowReturnStage("view/playlistChoiceWindow.fxml", "Choose a playlist");
                childStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        readPlaylistFromBinaryFile();
                    }
                });
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        });
    }

    private void readPlaylistFromBinaryFile() {

        try (FileInputStream fs = new FileInputStream("PlaylistId.bin"); ObjectInputStream os = new ObjectInputStream(fs)) {
            Playlist playlistChosen = (Playlist)os.readObject();
            writePlaylistInfoToDatabase(playlistChosen);
        } catch (FileNotFoundException fe) {
            DialogBoxManager.errorDialogBox("File not found", "File not found. Try again.");
        } catch (IOException ie) {
            DialogBoxManager.errorDialogBox("Cannot create file", "Error with creating file.");
        } catch (ClassNotFoundException ce) {
            DialogBoxManager.errorDialogBox("Cannot find class", "Error with finding class.");
        }
    }

    private void writePlaylistInfoToDatabase(Playlist playlistChosen) {
        if (selectedNodeForAddingToPlaylist instanceof Label) {
            int songId = Integer.parseInt(db_connector.search("track_id", "music_track",
                    "track_name = '" + lblTrackName.getText().replaceAll("'", "''") + "'"));
            int playlistId = playlistChosen.getId();
            db_connector.insert("playlist_has_music_track(playlist_playlist_id, music_track_track_id)", "("
                    + Integer.toString(playlistId) + ", " + Integer.toString(songId) + ")");
            setPlaylists();
        }
    }
    @FXML
    private void clickOnLstPlaylists() {

        setPlaylistSongs();
    }

    @FXML
    private void clickOnLstPlaylistSongs() {

        if (lstPlaylistSongs.getItems() != null) {
            MusicTrack songInPlaylist = selectedPlaylist.getMusicTracks().get(lstPlaylistSongs.getSelectionModel().getSelectedIndex());
            selectedItem = selectedPlaylist.getMusicTracks().get(lstPlaylistSongs.getSelectionModel().getSelectedIndex()).getTrackName();
            //plays song only on double click
            if (selectedItem.equals(temp)) {
                lstMainTracks.getItems().clear();
                albumSelected = new Album(selectedPlaylist.getName(), new Image("images/Playlist-default.jpg"));
                for (MusicTrack m : selectedPlaylist.getMusicTracks()) {
                    albumSelected.addSongs(m);
                }


                for (MusicTrack mt : selectedPlaylist.getMusicTracks()) {
                    lstMainTracks.getItems().add(mt.getTrackName());
                }
                imgMain.setImage(albumSelected.getAlbumCover());
                lblTrackName.setText(selectedItem);
                lblTrackArtist.setText(selectedPlaylist.getName());

                int ratingId = Integer.parseInt(db_connector.search("rating_id", "music_track",
                        "track_name = '" + songInPlaylist.getTrackName().replaceAll("'", "''") + "'"));
                currentSongRating = new Rating(songInPlaylist, Double.parseDouble(db_connector.search("final_rating",
                        "rating", "rating_id = " + Integer.toString(ratingId))));
                currentSongRating.setRatingID(ratingId);
                int product = (Integer.parseInt(db_connector.search("sum_from_all_voters", "rating", "rating_id = " + Integer.toString(ratingId)))) * (int) (Double.parseDouble(db_connector.search("final_rating",
                        "rating", "rating_id = " + Integer.toString(ratingId))) + 0.5);
                currentSongRating.setSumFromAllVoters(product);
                setRatingStars(lblRating,currentSongRating,imgRating);

                try {
                    url = new URL(songInPlaylist.getUrl());
                    trackPlaying = songInPlaylist;
                    mediaPlayer.stop();
                    media = new Media(url.toString());
                    mediaPlayer = new MediaPlayer(media);
                    sliderVolume.setValue(mediaPlayer.getVolume() * 100);
                    File dir = new File("tmp");
                    File[] matches = dir.listFiles(new FilenameFilter() {
                        public boolean accept(File dir, String name) {
                            return name.startsWith(url.toString().substring(36).replaceAll("%20", " ")) && name.endsWith(".mp3");
                        }
                    });
                    if (matches.length == 0) {
                        try {
                            connector = new Server_Connector(url.toString(), url);
                            //progressDownload.visibleProperty().bind(connector.runningProperty());
                            connector.restart();
                        } catch (Exception ex) {
                            System.out.println("Exception caught at line 872");
//                    imgNoConnection.setVisible(true);
//                    lblNoConnection2.setVisible(true);
//                    lblNoConnection1.setVisible(true);
                        }
                    }
                    Path path = Paths.get("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " ")));
                    runMediaPlayer(path);
                    mediaPlayer.play();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                temp = selectedPlaylist.getMusicTracks().get(lstPlaylistSongs.getSelectionModel().getSelectedIndex()).getTrackName();
            }
        }
    }

    @FXML
    private void onImgSearchUserPressed(MouseEvent e) {

        if (rdPremium.isSelected()) {
            String userSearched = cmbSearchUser.getEditor().getText();
                if (!db_connector.search("user_name",
                        "premium_user", "user_name = '" + userSearched.replaceAll("'", "''") + "'").equals("")) {
                    db_connector.findPremiumUser(cmbSearchUser.getEditor().getText());
                    if (GlobalVariables.getInstance().getPremiumUser() != null && GlobalVariables.getInstance().getTrialuser() == null) {
                        if (cmbSearchUser.getEditor().getText().equals(GlobalVariables.getInstance().getPremiumUser().getUserName())) {
                            GlobalVariables.getInstance().setOwnUserDescriptionController(new UserDescriptionController());
                            GlobalVariables.getInstance().setContactDescriptionController(null);
                        } else {
                            GlobalVariables.getInstance().setOwnUserDescriptionController(null);
                            GlobalVariables.getInstance().setContactDescriptionController(new UserDescriptionController());
                        }
                    } else if (GlobalVariables.getInstance().getTrialuser()!= null && GlobalVariables.getInstance().getPremiumUser() == null) {
                        if (cmbSearchUser.getEditor().getText().equals(GlobalVariables.getInstance().getTrialuser().getUserName())) {
                            GlobalVariables.getInstance().setOwnUserDescriptionController(new UserDescriptionController());
                            GlobalVariables.getInstance().setContactDescriptionController(null);
                        } else {
                            GlobalVariables.getInstance().setOwnUserDescriptionController(null);
                            GlobalVariables.getInstance().setContactDescriptionController(new UserDescriptionController());
                        }
                    }
                    try {
                        SceneManager.sceneManager.openNewWindow( "view/userDescription.fxml", GlobalVariables.getInstance().getContactSelected().getDisplayName());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            }

        } else if (rdTrial.isSelected()) {
            String userSearched = cmbSearchUser.getEditor().getText();
            if (!db_connector.search("user_name",
                    "trial_user", "user_name = '" + userSearched.replaceAll("'", "''") + "'").equals("")) {
                db_connector.findTrialUser(cmbSearchUser.getEditor().getText());
                GlobalVariables.getInstance().setOwnUserDescriptionController(null);
                GlobalVariables.getInstance().setContactDescriptionController(new UserDescriptionController());
                try {
                    SceneManager.sceneManager.openNewWindow( "view/userDescription.fxml", GlobalVariables.getInstance().getContactSelected().getDisplayName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void setMessages() {

        GlobalVariables globalVariables = GlobalVariables.getInstance();

        if (globalVariables.getPremiumUser() != null && globalVariables.getAdministrator() == null && globalVariables.getTrialuser() == null){
            int counter = 0;
            for (String s : db_connector.searchMultipleResults("friend_request_id", "friend_request",
                    "contact_contact_name = '" + globalVariables.getPremiumUser().getUserName() + "'")) {
                    if (s != null) {
                        Image img = new Image("images/messageUnread.png");
                        btnMessage.setFill(new ImagePattern(img));
                        counter++;
                        unreadMessage = true;
                    }
            } if (counter == 0) {
                Image img = new Image("images/message.png");
                btnMessage.setFill(new ImagePattern(img));
                unreadMessage = false;
            }
        }
    }

    @FXML
    private void onMessageButtonPressed(MouseEvent event) {

        if (unreadMessage) {
            try {
                Stage childStage = sceneManager.openNewWindowReturnStage( "view/friendRequestWindow.fxml", "Contact request");
                childStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        unreadMessage = false;
                        setMessages();
                        setContacts();
                    }
                });
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

    private void setContacts() {
        lstContacts.getItems().clear();
        ArrayList<String> contactUserNames = new ArrayList<>();
        GlobalVariables.getInstance().getContactList().clear();

        for (String s : db_connector.searchMultipleResults("contact_contact_name", "premium_user_has_contact",
                "premium_user_user_name = '" + GlobalVariables.getInstance().getPremiumUser().getUserName() + "'")) {
            if (!s.equals("")) {
                contactUserNames.add(s);
            }
        }
        for (String s : db_connector.searchMultipleResults("premium_user_user_name", "premium_user_has_contact",
                "contact_contact_name = '" + GlobalVariables.getInstance().getPremiumUser().getUserName() + "'")) {
            if (!s.equals("")) {
                contactUserNames.add(s);
            }
        }

        for (String s : contactUserNames) {
            db_connector.getContact(s);
        }

        lstContacts.getItems().addAll(GlobalVariables.getInstance().getContactList());
    }

    @FXML
    private void onLstContactsPressed(MouseEvent e) {

        if (e.getButton() == MouseButton.PRIMARY) {
            try {
                GlobalVariables.getInstance().setOwnUserDescriptionController(null);
                GlobalVariables.getInstance().setContactDescriptionController(new UserDescriptionController());
                GlobalVariables.getInstance().setContactSelected(GlobalVariables.getInstance().getContactList().get(lstContacts.getSelectionModel().getSelectedIndex()));
                SceneManager.sceneManager.openNewWindow("view/userDescription.fxml", GlobalVariables.getInstance().getContactList().get(lstContacts.getSelectionModel().getSelectedIndex()).getDisplayName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void setPlaylistSongs() {

        String albumId;
        String albumCoverPath = "";

        selectedPlaylist = userPlaylists.get(lstPlaylists.getSelectionModel().getSelectedIndex());
        for (MusicTrack m : userPlaylists.get(0).getMusicTracks()) {
            System.out.println(m.getTrackName());
        }
        System.out.println();
        userPlaylists.get(lstPlaylists.getSelectionModel().getSelectedIndex()).getMusicTracks().clear();
        selectedPlaylist.getMusicTracks().clear();
        //System.out.println(userPlaylists.get((lstPlaylists.getSelectionModel().getSelectedIndex())).getMusicTracks().size());
        if (lstPlaylists.getSelectionModel().getSelectedIndex() == tempPlaylist) {
            lstPlaylistSongs.getItems().clear();

            ArrayList<String> songsInPlaylistId = new ArrayList<>();
            ArrayList<String> songsInPlaylist = new ArrayList<>();
            for (String s : db_connector.searchMultipleResults("music_track_track_id", "playlist_has_music_track",
                    "playlist_playlist_id = "+ Integer.toString(selectedPlaylist.getId()))) {
                songsInPlaylistId.add(s);
                //System.out.println(songsInPlaylistId.size());
            }

            int counter = 0;
            selectedPlaylist = new Playlist(userPlaylists.get(lstPlaylists.getSelectionModel().getSelectedIndex()).getName(),
                    userPlaylists.get(lstPlaylists.getSelectionModel().getSelectedIndex()).getVisibility());
            for (String s : songsInPlaylistId) { //19 & 40

                songsInPlaylist.add(db_connector.search("track_name", "music_track",
                        "track_id = " + s));
                albumId = db_connector.search("album_album_id", "album_has_music_track",
                        "music_track_track_id = " + s);
                albumCoverPath = db_connector.search("album_cover_path", "album", "album_id = " +
                        albumId);
                MusicTrack check = new MusicTrack(db_connector.search("track_name", "music_track",
                        "track_id = " + s), db_connector.search("track_url", "music_track", "track_id = " + s));
                if (!selectedPlaylist.getMusicTracks().contains(check)) {
                    selectedPlaylist.addMusicTracks(new MusicTrack(db_connector.search("track_name", "music_track",
                            "track_id = " + s), db_connector.search("track_url", "music_track", "track_id = " + s)));
                    selectedPlaylist.getMusicTracks().get(counter).setID(Integer.parseInt(s));
                    selectedPlaylist.getMusicTracks().get(counter).setAlbumCoverPath(albumCoverPath);
                }
                counter++;
            }

            lstPlaylistSongs.getItems().addAll(selectedPlaylist.getMusicTracks());

        } else {
            tempPlaylist = lstPlaylists.getSelectionModel().getSelectedIndex();
        }
    }


}

