package model;

import java.util.ArrayList;

public class Playlist {
	private String name;
	private int numberOfEntries;
	private PrivacyLevel visibility;
	private ArrayList<MusicTrack> musicTracks = new ArrayList<MusicTrack>();

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

	public void addMusicTracks(MusicTrack t) {
		musicTracks.add(t);
	}

	public void removeMusicTracks(MusicTrack t) {
		musicTracks.remove(t);
	}

	public ArrayList<MusicTrack> getMusicTracks() {
		return new ArrayList<MusicTrack>(musicTracks);
	}
}
