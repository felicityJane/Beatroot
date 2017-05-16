package musicplayer.model;

import java.util.ArrayList;

import javafx.fxml.FXML;
import musicplayer.controller.AboutPageController;
import musicplayer.controller.AlbumPageController;
import musicplayer.controller.ArtistPageController;
import musicplayer.controller.FAQsPageController;
import musicplayer.controller.LogInMenuController;
import musicplayer.controller.MainMenuController;
import musicplayer.controller.PaymentMenuController;
import musicplayer.controller.SettingsPageController;
import musicplayer.controller.SignUpMenuController;
import musicplayer.controller.SongPageController;
import musicplayer.controller.WelcomeMenuController;

public class GlobalVariables {

	private final static GlobalVariables instance = new GlobalVariables();

	public static GlobalVariables getInstance() {
		return instance;
	}

	private Album album;
	private String albumCover;
	private MusicTrack musicTrack;
	private ArrayList<MusicTrack> musicTracks;
	private MusicArtist musicArtist;
	private TrialUser trialuser;
	private PremiumUser premiumUser;
	private Administrator administrator;
	@FXML
	private AboutPageController aboutPageController;
	@FXML
	private AlbumPageController albumPageController;
	@FXML
	private ArtistPageController artistPageController;
	@FXML
	private FAQsPageController faQsPageController;
	@FXML
	private LogInMenuController logInMenuController;
	@FXML
	private MainMenuController mainMenuController;
	@FXML
	private PaymentMenuController paymentMenuController;
	@FXML
	private SettingsPageController settingsPageController;
	@FXML
	private SignUpMenuController signUpMenuController;
	@FXML
	private SongPageController songPageController;
	@FXML
	private WelcomeMenuController welcomeMenuController;

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

	public MusicTrack getMusicTrack() {
		return musicTrack;
	}

	public void setMusicTrack(MusicTrack musicTrack) {
		this.musicTrack = musicTrack;
	}

	public ArrayList<MusicTrack> getMusicTracks() {
		return musicTracks;
	}

	public void setMusicTracks(ArrayList<MusicTrack> musicTracks) {
		this.musicTracks = musicTracks;
	}

	public MusicArtist getMusicArtist() {
		return musicArtist;
	}

	public void setMusicArtist(MusicArtist musicArtist) {
		this.musicArtist = musicArtist;
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

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public PremiumUser getPremiumUser() {
		return premiumUser;
	}

	public void setPremiumUser(PremiumUser premiumUser) {
		this.premiumUser = premiumUser;
	}

	public TrialUser getTrialuser() {
		return trialuser;
	}

	public void setTrialuser(TrialUser trialuser) {
		this.trialuser = trialuser;
	}
}
