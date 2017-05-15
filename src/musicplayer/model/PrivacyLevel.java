package musicplayer.model;

public enum PrivacyLevel {
	PRIVATE("Private"), PUBLIC("Public"), CONTACT("Visible to contacts");

	private String displayText;

	PrivacyLevel(String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}

	@Override
	public String toString() {
		return displayText;
	}

	public static PrivacyLevel fromString(String text){
		for( PrivacyLevel pL: PrivacyLevel.values()){
			if(pL.displayText.equalsIgnoreCase(text))
				return pL;
		}
	return null;
	}

}
