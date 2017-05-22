package musicplayer.model;

import java.util.ArrayList;
import java.util.Date;

import javafx.scene.image.Image;

public class Album {
	private int albumID;
	private String albumName;
	private Image albumCover;
	private ArrayList<MusicTrack> songs = new ArrayList<MusicTrack>();
	private Administrator administrator;
	private MusicArtist[] artists;
	private Date debutYear;

	/**
	 * @param albumName
	 *            String Album's name
	 * @param albumCover
	 *            Image Album's cover
	 */
	public Album(String albumName, Image albumCover) {
		this.albumName = albumName;
		this.albumCover = albumCover;
	}

	public Album(String albumName, Image albumCover, Date debutYear) {
		this.albumName = albumName;
		this.albumCover = albumCover;
		this.debutYear = debutYear;
	}

	public Album(String albumName, Image albumCover, Administrator administrator, Date debutYear,
			MusicArtist... artists) {
		this.albumName = albumName;
		this.albumCover = albumCover;
		this.debutYear = debutYear;
		this.administrator = administrator;
		this.artists = artists;

	}

	public int getAlbumID() {
		return albumID;
	}

	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public Image getAlbumCover() {
		return albumCover;
	}

	public void setAlbumCover(Image albumCover) {
		this.albumCover = albumCover;
	}

	public Date getDebutYear() {
		return debutYear;
	}

	public void setDebutYear(Date debutYear) {
		this.debutYear = debutYear;
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

	public void removeSongs(MusicTrack s) {
		if (!songs.contains(s))
			return;
		songs.remove(s);
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public MusicArtist[] getArtists() {
		return artists;
	}

	public void setArtists(MusicArtist[] artists) {
		this.artists = artists;
	}
}
