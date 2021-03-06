package musicplayer.model;

import java.util.ArrayList;

public class Rating {
	private int ratingID, sumFromAllVoters = 0;
	private double finalRating;
	private MusicArtist musicArtist;
	private MusicTrack song;
	private ArrayList<User> votedUsers = new ArrayList<User>();
	// temporary
	private int numberOfVoters;

	public Rating(MusicTrack song) {
		this.song = song;
	}

	public Rating(MusicTrack song, double finalRating) {
		this.song = song;
		this.finalRating = finalRating;
	}

	public Rating(double finalRating) {
		this.finalRating = finalRating;
	}

	public Rating(MusicArtist musicArtist, int ratingId) {
		this.musicArtist = musicArtist;
		this.ratingID = ratingId;
	}

	public int getRatingID() {
		return ratingID;
	}

	public int getNumberOfVoters() {
		return numberOfVoters;
	}

	public void setNumberOfVoters(int numberOfVoters) {
		this.numberOfVoters = numberOfVoters;
	}

	public void setRatingID(int ratingID) {
		this.ratingID = ratingID;
	}

	public int getSumFromAllVoters() {
		return sumFromAllVoters;
	}

	public void setSumFromAllVoters(int sumFromAllVoters) {
		this.sumFromAllVoters = sumFromAllVoters;
	}

	public double getFinalRating() {
		return finalRating;
	}

	public void calculateRating() {
		this.finalRating = sumFromAllVoters
				/ numberOfVoters /* (votedUsers.size() + 1) */;
	}

	public ArrayList<User> getVotedUsers() {
		return votedUsers;
	}

	public void setVotedUsers(ArrayList<User> votedUsers) {
		this.votedUsers = votedUsers;
	}

	public void addSumFromAllVoters(User u, float vote) {
		if (vote >= 0 && vote <= 5 && !votedUsers.contains(u)) {
			sumFromAllVoters += vote;
			votedUsers.add(u);
		}
	}

	public void addSumFromAllVoters(float vote) {
		if (vote >= 0 && vote <= 5) {
			sumFromAllVoters += vote;
		}
	}

	public void removeSumFromAllVoters(User u, float vote) {
		if (vote >= 0 && vote <= 5 && votedUsers.contains(u)) {
			sumFromAllVoters -= vote;
			votedUsers.remove(u);
		}
	}

	public MusicTrack getSong() {
		return song;
	}

	public void setSong(MusicTrack song) {
		this.song = song;
	}

	public MusicArtist getMusicArtist() {
		return musicArtist;
	}

	public void setMusicArtist(MusicArtist musicArtist) {
		this.musicArtist = musicArtist;
	}
}
