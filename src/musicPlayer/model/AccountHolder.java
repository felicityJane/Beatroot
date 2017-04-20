package model;

import java.util.Date;

public class AccountHolder extends User {

	private int bankCardNumber;
	private Date expiryDate;
	private String billingCardHolderName, billingAddress, billingCity, billingPostalCode, billingCountry, cardType;
	private int billingPhoneNumber;
	private boolean isSameAsResidentAddress;

	/*
	 * @Param Card number Card expiration date Card holder name Billing address
	 * Billing city Billing postal code Billing country Card type
	 */

	public AccountHolder(String userName, String password, String firstName, String lastName, String emailAddress,
			String physicalAddress, String cityOfResidence, String postalCode, String country, String gender,
			int phoneNumber, int bankCardNumber, Date expiryDate, String billingCardHolderName, String billingAddress,
			String billingCity, String billingPostalCode, String billingCountry, String cardType,
			int billingPhoneNumber) {
		super(userName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
				country, gender, phoneNumber);
		this.bankCardNumber = bankCardNumber;
		this.expiryDate = expiryDate;
		this.billingCardHolderName = billingCardHolderName;
		this.billingAddress = billingAddress;
		this.billingCity = billingCity;
		this.billingPostalCode = billingPostalCode;
		this.billingCountry = billingCountry;
		this.cardType = cardType;
		this.billingPhoneNumber = billingPhoneNumber;
	}

	public int getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(int bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public int getBillingPhoneNumber() {
		return billingPhoneNumber;
	}

	public void setBillingPhoneNumber(int billingPhoneNumber) {
		this.billingPhoneNumber = billingPhoneNumber;
	}

	public boolean isSameAsResidentAddress() {
		return isSameAsResidentAddress;
	}

	public void setSameAsResidentAddress(boolean isSameAsResidentAddress) {
		this.isSameAsResidentAddress = isSameAsResidentAddress;
	}

	private void changePaymentInformation() {
		// TODO Auto-generated method stub
	}

}
