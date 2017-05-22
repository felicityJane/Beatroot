package musicplayer.model;

public enum PaymentMethod {
	MASTERCARD("Mastercard"), VISA("Visa");

	private String displayText;

	private PaymentMethod(String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}

	@Override
	public String toString() {
		return displayText;
	}

	public static PaymentMethod fromString(String text) {
		for (PaymentMethod c : PaymentMethod.values()) {
			if (c.displayText.equalsIgnoreCase(text)) {
				return c;
			}
		}
		return null;
	}
}
