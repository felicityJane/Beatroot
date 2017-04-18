package model;

import java.util.Date;

public class PremiumUser extends User {

	private int bankCardNumber;
	private Date expirationDate;
	private String billingCardHolderName, billingAddress, billingCity, billingPostalCode, billingCountry, cardType;
	private String billingPhoneNumber;
	private boolean isSameAsResidentAddress;

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
	 * @param cardType
	 *            The card's type (e.g. master card, visa, visa electron, visa
	 *            debit etc.)
	 */

	public PremiumUser(String userName, String password, String firstName, String lastName, String emailAddress,
			String physicalAddress, String cityOfResidence, String postalCode, String country, String gender,
			String phoneNumber, int bankCardNumber, Date expirationDate, String cardType) {
		super(userName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
				country, gender, phoneNumber);
		this.bankCardNumber = bankCardNumber;
		this.expirationDate = expirationDate;
		this.cardType = cardType;
	}

	/**
	 * Billing address is different from the user's address
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
	 * @param bankCardNumber
	 *            The card number for the person's payment plan
	 * @param expirationDate
	 *            Card's expiration date
	 * @param cardType
	 *            The card's type (e.g. master card, visa, visa electron, visa
	 *            debit etc.)
	 * @param billingCardHolderName
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

	public PremiumUser(String userName, String password, String firstName, String lastName, String emailAddress,
			String physicalAddress, String cityOfResidence, String postalCode, String country, String gender,
			String phoneNumber, int bankCardNumber, Date expirationDate, String cardType, String billingCardHolderName,
			String billingAddress, String billingCity, String billingPostalCode, String billingCountry,
			String billingPhoneNumber) {
		super(userName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
				country, gender, phoneNumber);
		this.bankCardNumber = bankCardNumber;
		this.expirationDate = expirationDate;
		this.cardType = cardType;
		this.billingCardHolderName = billingCardHolderName;
		this.billingAddress = billingAddress;
		this.billingCity = billingCity;
		this.billingPostalCode = billingPostalCode;
		this.billingCountry = billingCountry;

		this.billingPhoneNumber = billingPhoneNumber;
	}

	public int getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(int bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public Date getexpirationDate() {
		return expirationDate;
	}

	public void setexpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getBillingCardHolderName() {
		return billingCardHolderName;
	}

	public void setBillingCardHolderName(String billingCardHolderName) {
		this.billingCardHolderName = billingCardHolderName;
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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBillingPhoneNumber() {
		return billingPhoneNumber;
	}

	public void setBillingPhoneNumber(String billingPhoneNumber) {
		this.billingPhoneNumber = billingPhoneNumber;
	}

	public boolean isSameAsResidentAddress() {
		return isSameAsResidentAddress;
	}

	public void setSameAsResidentAddress(boolean isSameAsResidentAddress) {
		this.isSameAsResidentAddress = isSameAsResidentAddress;
	}

	private void changePaymentInformation() {

	}

}
