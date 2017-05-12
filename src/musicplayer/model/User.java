package musicplayer.model;

import java.util.ArrayList;
import java.util.Date;

public abstract class User {
	private String displayName, password, firstName, lastName, emailAddress, phoneNumber;
	private Address physicalAddress;
	private final String userName;
	private UserPlaylistLink userPlaylistLink;
	private Date dateOfBirth;
	private Gender gender;
	private Playlist defaultPlaylist;
	// private ArrayList<Playlist> userPlaylists = new ArrayList<Playlist>();
	private ArrayList<Comment> userComments = new ArrayList<Comment>();
	private ArrayList<Rating> userRatings = new ArrayList<Rating>();

	public User(String userName, String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String streetNameAndNumber, String cityOfResidence,
			String postalCode, Country country, Gender gender, String phoneNumber) {
		this.userName = userName;
		this.displayName = displayName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.emailAddress = emailAddress;
		this.physicalAddress = new Address(streetNameAndNumber, cityOfResidence, postalCode, country);
		// this.physicalAddress.setFirstName(firstName);
		// this.physicalAddress.setLastName(lastName);
		// this.physicalAddress.setStreetNameAndNumber(streetNameAndNumber);
		// this.physicalAddress.setCity(cityOfResidence);
		// this.physicalAddress.setCountry(country);
		// this.physicalAddress.setPostalCode(postalCode);
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.defaultPlaylist = new Playlist("Default", PrivacyLevel.PRIVATE);
		userPlaylistLink.getPlaylists().add(defaultPlaylist);
	}

	public String getUserName() {
		return userName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getPhysicalAddress() {
		return physicalAddress;
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

	public void changeAccountSettings(String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence, String postalCode,
			Country country, String phoneNumber) {
		setDisplayName(displayName);
		setPassword(password);
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
