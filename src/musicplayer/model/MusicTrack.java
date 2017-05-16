package musicplayer.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.Duration;
import java.util.ArrayList;

public class MusicTrack implements Serializable {

	private int ID;
	private String trackName;
	private Duration trackLength;
	private String trackTime;
	private Genre[] genre;
	private Administrator administrator;
	private String url;
	private Rating rating;
	private Date publicationYear;
	private ArrayList<Album> albums = new ArrayList<Album>();
	private ArrayList<MusicArtist> performers = new ArrayList<MusicArtist>();
	private ArrayList<Comment> comments = new ArrayList<Comment>();

	public MusicTrack(String trackName, String url) {
		this.trackName = trackName;
		this.url = url;

	}

	/**
	 *
	 * @param trackName
	 *            String The song's name
	 * @param trackLength
	 *            Duration The song's duration.
	 * @param genres
	 *            Varargs Enum {@link Genre} Any number of genres between one
	 *            and all available in the Genre class
	 * @param url
	 *            String The path to the music track;
	 */
	// ... = various arguments (varargs)
	public MusicTrack(String trackName, String url, Duration trackLength, Genre... genres) {
		this.trackName = trackName;
		this.trackLength = trackLength;
		this.genre = genres;
		this.url = url;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Duration getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(Duration trackLength) {
		this.trackLength = trackLength;
	}

	public Genre[] getGenre() {
		return genre;
	}

	public void setGenre(Genre... genre) {
		this.genre = genre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<MusicArtist> getPerformers() {
		return performers;
	}

	public void setPerformers(ArrayList<MusicArtist> performers) {
		this.performers = performers;
	}

	public void addPerformer(MusicArtist performer) {
		if (performers.contains(performer))
			return;
		performers.add(performer);
	}

	public void removePerformer(MusicArtist performer) {
		if (!performers.contains(performer))
			return;
		performers.remove(performer);
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment fb) {
		if (comments.contains(fb.getCommentID()))
			return;
		comments.add(fb);
	}

	public void removeComment(Comment fb) {
		if (!comments.contains(fb))
			return;
		comments.remove(fb);
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdminstrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public void modifyTrackDetails(String trackName, Duration trackLength, String url, Genre... genre) {
		setTrackName(trackName);
		setTrackLength(trackLength);
		setUrl(url);
		setGenre(genre);
	}

	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}

	public void addToAlbum(Album al) {
		if (albums.contains(al))
			return;
		albums.add(al);
	}

	public void removeFromAlbum(Album al) {
		if (!albums.contains(al))
			return;
		albums.remove(al);
	}

	public String getTrackTime() {
		return trackTime;
	}

	public void setTrackTime(String trackTime) {
		this.trackTime = trackTime;
	}

	public Date getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Date publicationYear) {
		this.publicationYear = publicationYear;
	}
}
