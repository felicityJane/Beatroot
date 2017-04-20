package musicPlayer.model;

import java.util.ArrayList;

public class Playlist {
	private String plID;
	private String name;
	private int numberOfEntries;
	private PrivacyLevel visibility;
	private ArrayList<MusicTrack> musicTracks = new ArrayList<MusicTrack>();
	private User user;

	/**
	 * 
	 * @param name
	 *            String Custom playlist's name
	 * @param numberOfEntries
	 *            Integer The amount of songs in the playlists
	 * @param visibility
	 *            Enum {@link: PrivacyLevel} The level of visibility for other
	 *            users.
	 */
	public Playlist(String plID, String name, int numberOfEntries, PrivacyLevel visibility) {
		this.plID = plID;
		this.name = name;
		this.numberOfEntries = numberOfEntries;
		this.visibility = visibility;
	}

	public String getPlID() {
		return plID;
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

	public void addMusicTracks(MusicTrack t) {
		musicTracks.add(t);
	}

	public void removeMusicTracks(MusicTrack t) {
		musicTracks.remove(t);
	}

	public ArrayList<MusicTrack> getMusicTracks() {
		return new ArrayList<MusicTrack>(musicTracks);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
