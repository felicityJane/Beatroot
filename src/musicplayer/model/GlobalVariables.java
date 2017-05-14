package musicplayer.model;

import javafx.fxml.FXML;
import musicplayer.controller.*;

import java.util.ArrayList;

/**
 * Created by felic on 11/05/2017.
 */
public class GlobalVariables {

    private final static GlobalVariables instance = new GlobalVariables();

    public static GlobalVariables getInstance(){
        return instance;
    }
    private Album album;
    private String albumCover;
    private ArrayList<MusicTrack> musicTracks;
    @FXML private AboutPageController aboutPageController;
    @FXML private AlbumPageController albumPageController;
    @FXML private ArtistPageController artistPageController;
    @FXML private FAQsPageController faQsPageController;
    @FXML private LogInMenuController logInMenuController;
    @FXML private MainMenuController mainMenuController;
    @FXML private PaymentMenuController paymentMenuController;
    @FXML private SettingsPageController settingsPageController;
    @FXML private SignUpMenuController signUpMenuController;
    @FXML private SongPageController songPageController;
    @FXML private WelcomeMenuController welcomeMenuController;


    public void setAlbum(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

    public ArrayList<MusicTrack> getMusicTracks() {
        return musicTracks;
    }

    public void setMusicTracks(ArrayList<MusicTrack> musicTracks) {
        this.musicTracks = musicTracks;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    public SongPageController getSongPageController() {
        return songPageController;
    }

    public void setSongPageController(SongPageController songPageController) {
        this.songPageController = songPageController;
    }

    public AlbumPageController getAlbumPageController() {
        return albumPageController;
    }

    public void setAlbumPageController(AlbumPageController albumPageController) {
        this.albumPageController = albumPageController;
    }

    public AboutPageController getAboutPageController() {
        return aboutPageController;
    }

    public void setAboutPageController(AboutPageController aboutPageController) {
        this.aboutPageController = aboutPageController;
    }

    public ArtistPageController getArtistPageController() {
        return artistPageController;
    }

    public void setArtistPageController(ArtistPageController artistPageController) {
        this.artistPageController = artistPageController;
    }

    public FAQsPageController getFaQsPageController() {
        return faQsPageController;
    }

    public void setFaQsPageController(FAQsPageController faQsPageController) {
        this.faQsPageController = faQsPageController;
    }

    public LogInMenuController getLogInMenuController() {
        return logInMenuController;
    }

    public void setLogInMenuController(LogInMenuController logInMenuController) {
        this.logInMenuController = logInMenuController;
    }

    public PaymentMenuController getPaymentMenuController() {
        return paymentMenuController;
    }

    public void setPaymentMenuController(PaymentMenuController paymentMenuController) {
        this.paymentMenuController = paymentMenuController;
    }

    public SettingsPageController getSettingsPageController() {
        return settingsPageController;
    }

    public void setSettingsPageController(SettingsPageController settingsPageController) {
        this.settingsPageController = settingsPageController;
    }

    public SignUpMenuController getSignUpMenuController() {
        return signUpMenuController;
    }

    public void setSignUpMenuController(SignUpMenuController signUpMenuController) {
        this.signUpMenuController = signUpMenuController;
    }

    public WelcomeMenuController getWelcomeMenuController() {
        return welcomeMenuController;
    }

    public void setWelcomeMenuController(WelcomeMenuController welcomeMenuController) {
        this.welcomeMenuController = welcomeMenuController;
    }
}
