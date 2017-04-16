package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Administrator extends User {

	private Date startDate;
	private float wage, contractHours;
	// private ArrayList<Playlist> userPlaylists = new ArrayList<Playlist>();

	/**
	 * @param userName
	 *            String User's login name
	 * @param password
	 *            String User's login password
	 * @param firstName
	 *            String Employee's first name
	 * @param lastName
	 *            String Employee's last name
	 * @param emailAddress
	 *            Employee's address
	 * @param physicalAddress
	 *            String Employee's address
	 * @param cityOfResidence
	 *            String Employee's city of residence
	 * @param postalCode
	 *            String Employee's postal code
	 * @param country
	 *            String Employee's country of residence
	 * @param gender
	 *            String Employee's gender
	 * @param phoneNumber
	 *            String Employee's phone number
	 * @param startDate
	 *            Date Employee's start date according to contract
	 * @param wage
	 *            Float Employee's current wage
	 * @param contractHours
	 *            Float Employee's hours by contract (for overtime purposes)
	 */

	public Administrator(String userName, String password, String firstName, String lastName, String emailAddress,
			String physicalAddress, String cityOfResidence, String postalCode, String country, String gender,
			String phoneNumber, Date startDate, float wage, float contractHours) {
		super(userName, password, firstName, lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
				country, gender, phoneNumber);
		this.startDate = startDate;
		this.wage = wage;
		this.contractHours = contractHours;
	}

	public Date getStartDate() {
		return startDate;
	}

	// public void setStartDate(Date startDate) {
	// this.startDate = startDate;
	// }

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
	@Override
	public String toString() {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String output = format.format(getStartDate());

		return "Employee details:\nFirst name: " + getFirstName() + "\nStart date: " + output;
	}
}
