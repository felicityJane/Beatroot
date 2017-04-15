package model;

import java.util.Date;

public class TrialUser extends User {

	private Date freeTrialEndDate;

	public TrialUser(String userName, String password, String firstName, String lastName, String emailAddress,
			String physicalAddress, String cityOfResidence, String postalCode, String country, String gender,
			int phoneNumber, Date freeTrialEndDate) {
		super(userName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
				country, gender, phoneNumber);
		this.freeTrialEndDate = freeTrialEndDate;
	}

	public Date getFreeTrialEndDate() {
		return freeTrialEndDate;
	}

	private void upgradeToAccountHolder() {
		// TODO Auto-generated method stub
	}

}
