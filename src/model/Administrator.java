package model;

import java.util.Date;

public class Administrator extends User {

	private Date startDate;
	private float wage, contractHours;

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
