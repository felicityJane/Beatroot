package musicPlayer.model;

import java.util.ArrayList;
import java.util.Date;

public abstract class User {
	private String userName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
			country, gender, phoneNumber;
	private Date dateOfBirth;
	private static Playlist defaultPlaylist;
	private ArrayList<Playlist> userPlaylists = new ArrayList<Playlist>();
	private ArrayList<Feedback> userFeedback = new ArrayList<Feedback>();

	public User(String userName, String password, String firstName, String lastName, Date dateOfBirth,
			String emailAddress, String physicalAddress, String cityOfResidence, String postalCode, String country,
			String gender, String phoneNumber) {
		this.userName = userName;
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

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
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

	public ArrayList<Feedback> getUserFeedback() {
		return userFeedback;
	}

	public void setUserFeedback(ArrayList<Feedback> userFeedback) {
		this.userFeedback = userFeedback;
	}

	public void addUserFeedback(Feedback fb) {
		if (userFeedback.contains(fb.getFeedbackID()))
			return;
		userFeedback.add(fb);
	}

	public void removeUserFeedback(Feedback fb) {
		if (!userFeedback.contains(fb.getFeedbackID()))
			return;
		userFeedback.remove(fb);
	}
}
