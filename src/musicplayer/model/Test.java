package musicplayer.model;

import java.text.SimpleDateFormat;

public class Test {
	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String birthdate = "1991/06/13";
		String expiryDate = "2017/10/15";
		try {
			PremiumUser t = new PremiumUser("123123", "123123", "asdasd", "asdasd", sdf.parse(birthdate), "asdasd",
					"asdasd", "asdsad", "qwewqe", "qweqwe", "qweqwe", Gender.MALE, "3162", 1325263,
					sdf.parse(expiryDate), PaymentMethod.VISA, "V NIKOLOV");
			System.out.println(t.getExpirationDate());
			expiryDate = "2017/11/15";
			t.setExpirationDate(sdf.parse(expiryDate));
			System.out.println(t.getExpirationDate() + " " + t.getDisplayName());
			t.setDisplayName("Rockstar");
			System.out.println(t.getDisplayName() + " " + t.getBillingAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
