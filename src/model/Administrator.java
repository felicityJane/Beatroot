package model;

import java.util.Date;

public class Administrator extends User {

	private Date startDate;
	private float wage, contractHours;

	/**
	 * @param userName
	 *            User's login name
	 * @param password
	 *            User's login password
	 * @param firstName
	 *            Employee's first name
	 * @param lastName
	 *            Employee's last name
	 * @param emailAddress
	 *            Employee's address
	 * @param physicalAddress
	 *            Employee's address
	 * @param cityOfResidence
	 *            Employee's city of residence
	 * @param postalCode
	 *            Employee's postal code
	 * @param country
	 *            Employee's country of residence
	 * @param gender
	 *            Employee's gender
	 * @param phoneNumber
	 *            Employee's phone number
	 * @param startDate
	 *            Employee's start date according to contract
	 * @param wage
	 *            Employee's current wage
	 * @param contractHours
	 *            Employee's hours by contract (for overtime purposes)
	 */

	public Administrator(String userName, String password, String firstName, String lastName, String emailAddress,
			String physicalAddress, String cityOfResidence, String postalCode, String country, String gender,
			int phoneNumber, Date startDate, float wage, float contractHours) {
		super(userName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
				country, gender, phoneNumber);
		this.startDate = startDate;
		this.wage = wage;
		this.contractHours = contractHours;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public float getWage() {
		return wage;
	}

	public void setWage(float wage) {
		this.wage = wage;
	}

	public float getContractHours() {
		return contractHours;
	}

	public void setContractHours(float contractHours) {
		this.contractHours = contractHours;
	}
	// TODO Check if I'm missing something
}
