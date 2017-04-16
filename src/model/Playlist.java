package model;

public class Playlist {
	private String name;
	private int numberOfEntries;
	private PrivacyLevel visibility;

	public Playlist(String name, int numberOfEntries, PrivacyLevel visibility) {
		this.name = name;
		this.numberOfEntries = numberOfEntries;
		this.visibility = visibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfEntries() {
		return numberOfEntries;
	}

	public PrivacyLevel getVisibility() {
		return visibility;
	}

	public void setVisibility(PrivacyLevel visibility) {
		this.visibility = visibility;
	}

}
