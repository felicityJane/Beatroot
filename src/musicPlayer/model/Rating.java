package musicPlayer.model;

import java.util.ArrayList;

public class Rating {
	private int ratingID;
	private int sumFromAllVoters = 0;
	private double finalRating;
	private MusicTrack song;
	private ArrayList<User> votedUsers = new ArrayList<User>();

	public Rating(int ratingID) {
		this.ratingID = ratingID;
	}

	public int getRatingID() {
		return ratingID;
	}

	public int getsumFromAllVoters() {
		return sumFromAllVoters;
	}

	public double getFinalRating() {
		return finalRating;
	}

	public ArrayList<User> getVotedUsers() {
		return votedUsers;
	}

	public void setVotedUsers(ArrayList<User> votedUsers) {
		this.votedUsers = votedUsers;
	}

	public void addsumFromAllVoters(User u, float vote) {
		try {
			if (vote >= 1 && vote <= 5 && !votedUsers.contains(u)) {
				sumFromAllVoters += vote;
				votedUsers.add(u);
			}

		} catch (IndexOutOfBoundsException ex) {
			ex.getStackTrace();
		}
	}

	public void removesumFromAllVoters(User u, float vote) {
		try {
			if (vote >= 1 && vote <= 5 && votedUsers.contains(u)) {
				sumFromAllVoters -= vote;
				votedUsers.remove(u);
			}
		} catch (IndexOutOfBoundsException ex) {
			ex.getStackTrace();
		}
	}

	public void calculateRating() {
		this.finalRating = sumFromAllVoters / (votedUsers.size() + 1);
	}

	public MusicTrack getSong() {
		return song;
	}

	public void setSong(MusicTrack song) {
		this.song = song;
	}
}
