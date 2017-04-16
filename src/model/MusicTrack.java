package model;

import java.sql.Time;
import java.util.ArrayList;

public class MusicTrack {
	private String trackName;
	private Time trackLength;
	private ArrayList<Genre> genre;
	private String url;

	// TODO see how to implement multiple enum values on creation
	public MusicTrack(String trackName, Time trackLength, String url) {
		this.trackName = trackName;
		this.trackLength = trackLength;
		// this.genre = genre;
		this.url = url;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Time getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(Time trackLength) {
		this.trackLength = trackLength;
	}

	public ArrayList<Genre> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<Genre> genre) {
		this.genre = genre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
