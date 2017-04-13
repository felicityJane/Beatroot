package model;

public abstract class User {
	private String userName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
			country, gender;
	private int phoneNumber;
	// private Playlist playlist;

	/*
	 * @Param User name Password First name Last Name email address Physical
	 * address City of residence Postal code Country Gender Phone number
	 */
	public User(String userName, String password, String firstName, String lastName, String emailAddress,
			String physicalAddress, String cityOfResidence, String postalCode, String country, String gender,
			int phoneNumber) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.physicalAddress = physicalAddress;
		this.cityOfResidence = cityOfResidence;
		this.postalCode = postalCode;
		this.country = country;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private void createPlayList() {
		// TODO Auto-generated method stub
	}

}
