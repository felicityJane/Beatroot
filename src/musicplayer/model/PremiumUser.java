package musicplayer.model;

import java.util.Date;

public class PremiumUser extends NonTrialUser {

	private String bankCardNumber;
	private Date expirationDate;
	private String billingAccountOwnerName, billingAddress, billingCity, billingPostalCode, billingCountry,
			billingPhoneNumber;
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
	 * @param physicalAddress
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
			Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence, String postalCode,
			String country, Gender gender, String phoneNumber, String bankCardNumber, Date expirationDate,
			PaymentMethod PaymentMethod, String billingAccountOwnerName) {
		super(userName, displayName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress,
				cityOfResidence, postalCode, country, gender, phoneNumber);
		this.bankCardNumber = bankCardNumber;
		this.expirationDate = expirationDate;
		this.PaymentMethod = PaymentMethod;
		this.billingAccountOwnerName = billingAccountOwnerName;
		this.billingAddress = physicalAddress;
		this.billingCity = cityOfResidence;
		this.billingCountry = country;
		this.billingPhoneNumber = phoneNumber;
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
	 * @param billingAccountOwnerName
	 *            The name of the account owner as printed on the card's face
	 * @param billingAddress
	 *            Account owner's address
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
			String country, Gender gender, String phoneNumber, String bankCardNumber, Date expirationDate,
			PaymentMethod PaymentMethod, String billingAccountOwnerName, String billingAddress, String billingCity,
			String billingPostalCode, String billingCountry, String billingPhoneNumber) {
		super(userName, displayName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress,
				cityOfResidence, postalCode, country, gender, phoneNumber);
		this.bankCardNumber = bankCardNumber;
		this.expirationDate = expirationDate;
		this.PaymentMethod = PaymentMethod;
		this.billingAccountOwnerName = billingAccountOwnerName;
		this.billingAddress = billingAddress;
		this.billingCity = billingCity;
		this.billingPostalCode = billingPostalCode;
		this.billingCountry = billingCountry;
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

	public String getBillingAccountOwnerName() {
		return billingAccountOwnerName;
	}

	public void setBillingAccountOwnerName(String billingAccountOwnerName) {
		this.billingAccountOwnerName = billingAccountOwnerName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
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
			String billingAddress, String billingCity, String billingCountry, String billingPhoneNumber,
			String billingPostalCode) {
		setBankCardNumber(bankCardNumber);
		setExpirationDate(expirationDate);
		setBillingAccountOwnerName(billingAccountOwnerName);
		setBillingAddress(billingAddress);
		setBillingCity(billingCity);
		setBillingCountry(billingCountry);
		setBillingPhoneNumber(billingPhoneNumber);
		setBillingPostalCode(billingPostalCode);
	}

}
