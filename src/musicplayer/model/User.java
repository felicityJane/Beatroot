package musicplayer.model;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class User {
	private StringProperty displayName, password, firstName, lastName, emailAddress, phoneNumber;
	private Address physicalAddress;
	private final String userName;
	private UserPlaylistLink userPlaylistLink;
	private Date dateOfBirth;
	private Gender gender;
	private Playlist defaultPlaylist;
	private String userDescription;
	private String profilePicturePath;
	// private ArrayList<Playlist> userPlaylists = new ArrayList<Playlist>();
	private ArrayList<Comment> userComments = new ArrayList<Comment>();
	private ArrayList<Rating> userRatings = new ArrayList<Rating>();

	public User(String userName, String displayName, String password, String userDescription, String profilePicturePath,
			String firstName, String lastName,
			// <<<<<<< HEAD
			// Date dateOfBirth, String emailAddress, String
			// streetNameAndNumber, String cityOfResidence,
			// String postalCode, Country country, Gender gender, String
			// phoneNumber) {
			// =======
			Date dateOfBirth, String emailAddress, String streetNameAndNumber, String cityOfResidence,
			String postalCode, Country country, Gender gender, String phoneNumber) {

		// >>>>>>> refs/heads/master
		this.userName = userName;
		this.displayName = new SimpleStringProperty(displayName);
		this.password = new SimpleStringProperty(password);
		this.userDescription = userDescription;
		this.profilePicturePath = profilePicturePath;
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.dateOfBirth = dateOfBirth;
		this.emailAddress = new SimpleStringProperty(emailAddress);
		// <<<<<<< HEAD
		this.physicalAddress = new Address(streetNameAndNumber, cityOfResidence, postalCode, country);
		// this.physicalAddress.setFirstName(firstName);
		// this.physicalAddress.setLastName(lastName);
		// this.physicalAddress.setStreetNameAndNumber(streetNameAndNumber);
		// this.physicalAddress.setCity(cityOfResidence);
		// this.physicalAddress.setCountry(country);
		// this.physicalAddress.setPostalCode(postalCode);
		// =======
		// physicalAddress = new Address(streetNameAndNumber, cityOfResidence,
		// postalCode, country);
		// this.physicalAddress.setFirstName(firstName);
		// this.physicalAddress.setLastName(lastName);
		// >>>>>>> refs/heads/master
		this.gender = gender;
		this.phoneNumber = new SimpleStringProperty(phoneNumber);

		this.defaultPlaylist = new Playlist("Default", PrivacyLevel.PRIVATE);
		userPlaylistLink = new UserPlaylistLink(userName, defaultPlaylist);
	}

	public String getUserName() {
		return userName;
	}

	public String getDisplayName() {
		return displayName.get();
	}

	public void setDisplayName(String displayName) {
		this.displayName.set(displayName);
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getEmailAddress() {
		return emailAddress.get();
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress.set(emailAddress);
	}

	public String getStreetNameAndNumber() {
		return physicalAddress.getStreetNameAndNumber();
	}

	public String getCityOfResidence() {
		return physicalAddress.getCity();
	}

	public String getPostalCode() {
		return physicalAddress.getPostalCode();
	}

	public Country getCountry() {
		return physicalAddress.getCountry();
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public Address getPhysicalAddress() {
		return physicalAddress;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getProfilePicturePath() {
		return profilePicturePath;
	}

	public void setProfilePicturePath(String profilePicturePath) {
		this.profilePicturePath = profilePicturePath;
	}

	public void setPhysicalAddress(Address physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public UserPlaylistLink getUserPlaylistLink() {
		return userPlaylistLink;
	}

	public void setUserPlaylistLink(UserPlaylistLink userPlaylistLink) {
		this.userPlaylistLink = userPlaylistLink;
	}

	public void addUserPlaylist(Playlist pl) {
		if (userPlaylistLink.getPlaylists().contains(pl))
			return;
		userPlaylistLink.getPlaylists().add(pl);
	}

	public void removeUserPlaylist(Playlist pl) {
		if (!userPlaylistLink.getPlaylists().contains(pl))
			return;
		userPlaylistLink.getPlaylists().remove(pl);
	}

	public ArrayList<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(ArrayList<Comment> userComments) {
		this.userComments = userComments;
	}

	public void addUserComments(Comment fb) {
		if (userComments.contains(fb.getCommentID()))
			return;
		userComments.add(fb);
	}

	public void removeUserComments(Comment fb) {
		if (!userComments.contains(fb.getCommentID()))
			return;
		userComments.remove(fb);
	}

	public void addUserRating(Rating r) {
		if (userRatings.contains(r))
			return;
		userRatings.add(r);
	}

	public void removeUserRating(Rating r) {
		if (!userRatings.contains(r))
			return;
		userRatings.remove(r);
	}

	public Playlist getDefaultPlaylist() {
		return defaultPlaylist;
	}

	public void setDefaultPlaylist(Playlist defaultPlaylist) {
		this.defaultPlaylist = defaultPlaylist;
	}

	public void changeAccountSettings(String displayName, String password, String userDescription,
			String profilePicturePath, String firstName, String lastName, Date dateOfBirth, String emailAddress,
			String physicalAddress, String cityOfResidence, String postalCode, Country country, String phoneNumber) {
		setDisplayName(displayName);
		setPassword(password);
		setUserDescription(userDescription);
		setProfilePicturePath(profilePicturePath);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setEmailAddress(emailAddress);
		getPhysicalAddress().setStreetNameAndNumber(physicalAddress);
		getPhysicalAddress().setPostalCode(postalCode);
		getPhysicalAddress().setCountry(country);
		getPhysicalAddress().setCity(cityOfResidence);
		setPhoneNumber(phoneNumber);
	}
}
