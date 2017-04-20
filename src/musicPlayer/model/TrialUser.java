package musicPlayer.model;

import java.util.Date;

public class TrialUser extends User {

	private Date freeTrialEndDate;

	/**
	 * @param userName
	 *            User's login name
	 * @param password
	 *            User's login password
	 * @param firstName
	 *            User's first name
	 * @param lastName
	 *            User's last name
	 * @param Date
	 *            dateOfBirth User's date of birth
	 * @param emailAddress
	 *            User's address
	 * @param physicalAddress
	 *            User's address
	 * @param cityOfResidence
	 *            User's city of residence
	 * @param postalCode
	 *            User's postal code
	 * @param country
	 *            User's country of residence
	 * @param gender
	 *            User's gender
	 * @param phoneNumber
	 *            User's phone number
	 */
	public TrialUser(String userName, String password, String firstName, String lastName, Date dateOfBirth,
			String emailAddress, String physicalAddress, String cityOfResidence, String postalCode, String country,
			String gender, String phoneNumber, Date freeTrialEndDate) {
		super(userName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress, cityOfResidence,
				postalCode, country, gender, phoneNumber);
		this.freeTrialEndDate = freeTrialEndDate;

	}

	public Date getFreeTrialEndDate() {
		return freeTrialEndDate;
	}

	private void upgradeToPremiumUser() {
		// TODO Auto-generated method stub
	}

}
