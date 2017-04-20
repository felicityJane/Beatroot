package musicPlayer.model;

import java.time.Duration;
import java.util.ArrayList;

public class MusicTrack {
	private String trackName;
	private Duration trackLength;
	private Genre[] genre;
	private String url;
	private Rating rating;
	private ArrayList<MusicArtist> performers = new ArrayList<MusicArtist>();

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
}
