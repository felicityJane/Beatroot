package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		// Administrator user = new Administrator(userName, password, firstName,
		// lastName, emailAddress, physicalAddress, cityOfResidence, postalCode,
		// country, gender, phoneNumber, startDate, wage, contractHours)
		// PremiumUser n = new PremiumUser
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd");
		// Date date = new Date();
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date());
		// cal.add(Calendar.DATE, 1);
		// System.out.println(cal.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate;
		try {
			startDate = sdf.parse("2017/06/13");

			Administrator us = new Administrator("Viktor", "qwe", "Viktor", "Nikolov", "viktors@f2f.cx",
					"13 Gloucester Street South", "Oldham", "OL9 7RJ", "United Kingdom", "Male", "0737181299",
					startDate, 130.5f, 25.5f);

			System.out.println(us.toString());
			System.out.println(us.getUserPlaylists().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
