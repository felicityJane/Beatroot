package musicplayer.model;

import java.util.Date;

public class TrialUser extends User {

	private Date freeTrialEndDate;

	/**
	 * @param userName
	 *            String User's login name
	 * @param displayName
	 *            String The user's display name within the program, which is
	 *            editable.
	 * @param password
	 *            String User's login password
	 * @param firstName
	 *            String User's first name
	 * @param lastName
	 *            String User's last name
	 * @param dateOfBirth
	 *            Date dateOfBirth User's date of birth
	 * @param emailAddress
	 *            String User's address
	 * @param physicalAddress
	 *            String User's address
	 * @param cityOfResidence
	 *            String User's city of residence
	 * @param postalCode
	 *            String User's postal code
	 * @param country
	 *            String User's country of residence
	 * @param gender
	 *            Enum {@link: Gender} User's gender
	 * @param phoneNumber
	 *            User's phone number
	 */
	public TrialUser(String userName, String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence, String postalCode,
			Country country, Gender gender, String phoneNumber, Date freeTrialEndDate) {
		super(userName, displayName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress,
				cityOfResidence, postalCode, country, gender, phoneNumber);
		this.freeTrialEndDate = freeTrialEndDate;

	}

	public Date getFreeTrialEndDate() {
		return freeTrialEndDate;
	}

	public void upgradeToPremiumUserWithIdenticalBillingAddress(String bankCardNumber, Date expirationDate,
			PaymentMethod paymentMethod, String billingAccountOwnerName) {
		PremiumUser upgradedUser = new PremiumUser(this.getUserName(), this.getDisplayName(), this.getPassword(),
				this.getFirstName(), this.getLastName(), this.getDateOfBirth(), this.getEmailAddress(),
				this.getStreetNameAndNumber(), this.getCityOfResidence(), this.getPostalCode(), this.getCountry(),
				this.getGender(), this.getPhoneNumber(), bankCardNumber, expirationDate, paymentMethod,
				billingAccountOwnerName);
	}

	public void upgradeToPremiumUserWithDifferentBillingAddress(String bankCardNumber, Date expirationDate,
			PaymentMethod paymentMethod, String billingAccountOwnerName, String billingAddress, String billingCity,
			String billingPostalCode, Country billingCountry, String billingPhoneNumber) {
		PremiumUser upgradedUser = new PremiumUser(this.getUserName(), this.getDisplayName(), this.getPassword(),
				this.getFirstName(), this.getLastName(), this.getDateOfBirth(), this.getEmailAddress(),
				this.getStreetNameAndNumber(), this.getCityOfResidence(), this.getPostalCode(), this.getCountry(),
				this.getGender(), this.getPhoneNumber(), bankCardNumber, expirationDate, paymentMethod,
				billingAccountOwnerName, billingAddress, billingCity, billingPostalCode, billingCountry,
				billingPhoneNumber);
	}
}
