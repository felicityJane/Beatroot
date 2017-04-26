package musicplayer.model;

import java.util.ArrayList;
import java.util.Date;

public abstract class User {
	private String userName, displayName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence,
			postalCode, country, phoneNumber;
	private Date dateOfBirth;
	private Gender gender;
	private static Playlist defaultPlaylist;
	private ArrayList<Playlist> userPlaylists = new ArrayList<Playlist>();
	private ArrayList<Comment> userComments = new ArrayList<Comment>();
	private ArrayList<Rating> userRatings = new ArrayList<Rating>();

	public User(String userName, String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence, String postalCode,
			String country, Gender gender, String phoneNumber) {
		this.userName = userName;
		this.displayName = displayName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.emailAddress = emailAddress;
		this.physicalAddress = physicalAddress;
		this.cityOfResidence = cityOfResidence;
		this.postalCode = postalCode;
		this.country = country;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		// TODO this.defaultPlaylist=new Playlist()
		userPlaylists.add(defaultPlaylist);
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

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public String getCityOfResidence() {
		return cityOfResidence;
	}

	public void setCityOfResidence(String cityOfResidence) {
		this.cityOfResidence = cityOfResidence;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public ArrayList<Playlist> getUserPlaylists() {
		return userPlaylists;
	}

	public void setUserPlaylists(ArrayList<Playlist> userPlaylists) {
		this.userPlaylists = userPlaylists;
	}

	public void addPlaylist(Playlist pl) {
		if (userPlaylists.contains(pl))
			return;
		userPlaylists.add(pl);
	}

	public void removePlaylist(Playlist pl) {
		if (!userPlaylists.contains(pl))
			return;
		userPlaylists.remove(pl);
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
}
