package musicplayer.model;

import java.util.ArrayList;

public class Rating {
	private int ratingID, sumFromAllVoters = 0;
	private double finalRating;
	private MusicTrack song;
	private ArrayList<User> votedUsers = new ArrayList<User>();

	public Rating(MusicTrack song) {
		this.song = song;
	}

	public int getRatingID() {
		return ratingID;
	}

	public void setRatingID(int ratingID) {
		this.ratingID = ratingID;
	}

	public int getSumFromAllVoters() {
		return sumFromAllVoters;
	}

	public double getFinalRating() {
		return finalRating;
	}

	public void calculateRating() {
		this.finalRating = sumFromAllVoters / (votedUsers.size() + 1);
	}

	public ArrayList<User> getVotedUsers() {
		return votedUsers;
	}

	public void setVotedUsers(ArrayList<User> votedUsers) {
		this.votedUsers = votedUsers;
	}

	public void addSumFromAllVoters(User u, float vote) {
		if (vote >= 1 && vote <= 5 && !votedUsers.contains(u)) {
			sumFromAllVoters += vote;
			votedUsers.add(u);
		}
	}

	public void removeSumFromAllVoters(User u, float vote) {
		if (vote >= 1 && vote <= 5 && votedUsers.contains(u)) {
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
}
