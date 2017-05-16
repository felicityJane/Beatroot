package musicplayer.model;

public enum Genre {
	POP("Pop"), INDUSTRIAL_METAL("Industrial metal"), HEAVY_METAL("Heavy metal"), DEATH_METAL(
			"Death metal"), ALTERNATIVE_METAL("Alternative metal"), JAZZ("Jazz"), REGGAE("Reggae"), HIP_HOP(
					"Hip hop"), TECHNO("Techno"), DISCO("Disco"), HOUSE("House"), BLUES("Blues"), RAP("Rap"), PUNK_ROCK(
							"Punk rock"), RYTHM_AND_BLUES("R&B"), FOLK("Folk"), DUBSTEP("Dubstep"), ELECTRO(
									"Electro"), FUNK("Funk"), COUNTRY("Country"), MELODY("Melody"), ORCHESTRA(
											"Orchestra"), CLASSICAL("Classical"), INSTRUMENTAL(
													"Instrumental"), DRUM_AND_BASS("Drum and bass"), TRANCE(
															"Trance"), GOSPEL("Gospel"), OPERA("Opera"), NEW_WAVE(
																	"New wave"), POP_ROCK("Pop rock"), BLUEGRASS(
																			"Bluegrass"), PROGRESSIVE_ROCK(
																					"Progressive rock"), FOLK_ROCK(
																							"Folk rock"), SOUL(
																									"Soul"), HARDCORE_PUNK(
																											"Hardcore punk"), SKA(
																													"Ska"), INDIE_POP(
																															"Indie pop"), TRAP(
																																	"Trap"), WORLD(
																																			"World"), REGGAETON(
																																					"Reggaeton"), BLUES_ROCK(
																																							"Blues rock");

	private String displayText;

	private Genre(String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}

	@Override
	public String toString() {
		return displayText;
	}

	public static Genre fromString(String text) {
		for (Genre c : Genre.values()) {
			if (c.displayText.equalsIgnoreCase(text)) {
				return c;
			}
		}
		return null;
	}
}
