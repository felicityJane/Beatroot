package musicplayer.model;

public enum Gender {
	MALE("Male"), FEMALE("Female"), NOT_SPECIFIED("Not specified");
	private String displayName;

	Gender(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return this.displayName;
	}

	public static Gender fromString(String text) {
		for (Gender c : Gender.values()) {
			if (c.displayName.equalsIgnoreCase(text)) {
				return c;
			}
		}
		return null;
	}

}
