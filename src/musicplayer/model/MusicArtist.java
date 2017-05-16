package musicplayer.model;

import java.sql.Date;
import java.util.ArrayList;

public class MusicArtist {

	private int artistID;
	private String stageName;
	private ArrayList<MusicTrack> songs = new ArrayList<MusicTrack>();
	private Rating rating;
	private Administrator administrator;
	private Date publicationYear;
	private String artistDescription;
	// private ArrayList<>

	/**
	 *
	 * @param stageName
	 *            String Music Artist's name
	 */

	public MusicArtist(String stageName) {
		this.stageName = stageName;
	}

	public int getArtistID() {
		return artistID;
	}

	public void setArtistID(int artistID ) {this.artistID = artistID;}

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

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Date getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Date publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getArtistDescription() {
		return artistDescription;
	}

	public void setArtistDescription(String artistDescription) {
		this.artistDescription = artistDescription;
	}
}
