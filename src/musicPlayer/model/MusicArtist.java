package musicPlayer.model;

import java.util.ArrayList;

public class MusicArtist {
	private final String artistID;
	private String stageName;
	private ArrayList<MusicTrack> songs = new ArrayList<MusicTrack>();
	private Rating rating;

	/**
	 * 
	 * @param artistID
	 *            Integer For database handling
	 * @param stageName
	 *            String Music Artist's name
	 */

	public MusicArtist(String artistID, String stageName) {
		this.artistID = artistID;
		this.stageName = stageName;
	}

	public String getArtistID() {
		return artistID;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public ArrayList<MusicTrack> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<MusicTrack> songs) {
		this.songs = songs;
	}

	public void addSongs(MusicTrack s) {
		if (songs.contains(s))
			return;
		songs.add(s);
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
}
