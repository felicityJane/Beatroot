package musicplayer.model;

import java.time.Duration;
import java.util.ArrayList;

public class Playlist {
	private String playlistID;
	private String name;
	private int numberOfEntries;
	private Duration duration;
	private PrivacyLevel visibility;
	private ArrayList<MusicTrack> musicTracks = new ArrayList<MusicTrack>();
	private User user;

	/**
	 * 
	 * @param name
	 *            String Custom playlist's name
	 * @param numberOfEntries
	 *            Integer The amount of songs in the playlist
	 * @param visibility
	 *            Enum {@link: PrivacyLevel} The level of visibility for other
	 *            users.
	 */
	public Playlist(String playlistID, String name, int numberOfEntries, PrivacyLevel visibility) {
		this.playlistID = playlistID;
		this.name = name;
		this.numberOfEntries = numberOfEntries;
		this.visibility = visibility;
	}

	public String getPlaylistID() {
		return playlistID;
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
		updateDuration();
	}

	// TODO ask how to sum duration
	public void updateDuration() {

		for (int i = 0; i < musicTracks.size(); i++)
			duration = Duration.from(musicTracks.get(i).getTrackLength());
	}

	public void removeMusicTracks(MusicTrack t) {
		if (!musicTracks.contains(t))
			return;
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
