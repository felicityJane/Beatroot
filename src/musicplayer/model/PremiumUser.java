package musicplayer.model;

import java.util.Date;

public class PremiumUser extends NonTrialUser {

	private String bankCardNumber;
	private Date expirationDate;
	private Address billingAddress;
	private String billingPhoneNumber;
	private String cardHolderName;
	private PaymentMethod PaymentMethod;
	// private ArrayList<User> friends = new ArrayList<User>();
	// private ArrayList<User> blocked = new ArrayList<User>();

	/**
	 * Billing address is the same as the user's address
	 * 
	 * @param userName
	 *            User's login name
	 * @param password
	 *            User's login password
	 * @param firstName
	 *            User's first name
	 * @param lastName
	 *            User's last name
	 * @param emailAddress
	 *            User's email address
	 * @param streetNameAndNumber
	 *            User's address of residence
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
	 * @param bankCardNumber
	 *            The card number for the person's payment plan
	 * @param expirationDate
	 *            Card's expiration date
	 * @param PaymentMethod
	 *            Enum {@link: PaymentMethod} The payment method.
	 */


	public PremiumUser(String userName, String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String streetNameAndNumber, String cityOfResidence, String postalCode,
			Country country, Gender gender, String phoneNumber, String bankCardNumber, Date expirationDate,
			PaymentMethod PaymentMethod, String billingAccountOwnerName) {
		super(userName, displayName, password, firstName, lastName, dateOfBirth, emailAddress, streetNameAndNumber,

				cityOfResidence, postalCode, country, gender, phoneNumber);
		this.bankCardNumber = bankCardNumber;
		this.expirationDate = expirationDate;
		this.PaymentMethod = PaymentMethod;
		this.cardHolderName = billingAccountOwnerName;
		billingAddress = super.getPhysicalAddress();
	}
	/**
	 * Billing address is different from the user's address
	 * 
	 * @param userName
	 *            User's login name
	 * @param displayName
	 *            String The user's display name within the program, which is
	 *            editable.
	 * @param password
	 *            User's login password
	 * @param firstName
	 *            User's first name
	 * @param lastName
	 *            User's last name
	 * @param dateOfBirth
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
	 *            Enum {@link: Gender} User's gender
	 * @param phoneNumber
	 *            User's phone number
	 * @param bankCardNumber
	 *            The card number for the person's payment plan
	 * @param expirationDate
	 *            Card's expiration date
	 * @param PaymentMethod
	 *            Enum {@link: PaymentMethod} The payment's type.
	 * @param cardHolderName
	 *            The name of the account owner as printed on the card's face
	 * @param billingCity
	 *            Account owner's city of residence
	 * @param billingPostalCode
	 *            Account owner's postal code
	 * @param billingCountry
	 *            Account owner's country of residence
	 * @param billingPhoneNumber
	 *            Account owner's phone number
	 */

	public PremiumUser(String userName, String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence, String postalCode,

			Country country, Gender gender, String phoneNumber, String bankCardNumber, Date expirationDate,
			PaymentMethod PaymentMethod, String cardHolderName, String billingStreet, String billingCity,
			String billingPostalCode, Country billingCountry, String billingPhoneNumber) {

		super(userName, displayName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress,
				cityOfResidence, postalCode, country, gender, phoneNumber);
		this.bankCardNumber = bankCardNumber;
		this.expirationDate = expirationDate;
		this.PaymentMethod = PaymentMethod;
		this.cardHolderName = cardHolderName;
		this.billingAddress.setStreetNameAndNumber(billingStreet);
		this.billingAddress.setCity(billingCity);
		this.billingAddress.setPostalCode(billingPostalCode);
		this.billingAddress.setCountry(billingCountry);
		this.PaymentMethod = PaymentMethod;

		this.billingPhoneNumber = billingPhoneNumber;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	public Address getBillingAddress() {
		return billingAddress;
	}

	public String getBillingStreet() { return billingAddress.getStreetNameAndNumber();}

	public void setBillingStreet(String billingStreet) { billingAddress.setStreetNameAndNumber(billingStreet);}

	public String getBillingCity() {
		return billingAddress.getCity();
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public void setBillingCity(String billingCity) {
		this.billingAddress.setCity(billingCity);
	}

	public String getBillingPostalCode() {
		return billingAddress.getPostalCode();
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingAddress.setPostalCode(billingPostalCode);
	}

	public Country getBillingCountry() {
		return billingAddress.getCountry();
	}

	public void setBillingCountry(Country billingCountry) {
		billingAddress.setCountry(billingCountry);
	}

	public PaymentMethod getPaymentMethod() {
		return PaymentMethod;
	}

	public void setPaymentMethod(PaymentMethod PaymentMethod) {
		this.PaymentMethod = PaymentMethod;
	}

	public String getBillingPhoneNumber() {
		return billingPhoneNumber;
	}

	public void setBillingPhoneNumber(String billingPhoneNumber) {
		this.billingPhoneNumber = billingPhoneNumber;
	}

	public void changePaymentInformation(String bankCardNumber, Date expirationDate, String billingAccountOwnerName,
			String billingAddress, String billingCity, Country billingCountry, String billingPhoneNumber,
			String billingPostalCode) {
		setBankCardNumber(bankCardNumber);
		setExpirationDate(expirationDate);
		setBillingPhoneNumber(billingPhoneNumber);
		setCardHolderName(billingAccountOwnerName);
		getBillingAddress().setStreetNameAndNumber(billingAddress);
		getBillingAddress().setCity(billingCity);
		getBillingAddress().setCountry(billingCountry);
		getBillingAddress().setPostalCode(billingPostalCode);
	}

}
