package musicPlayer.model;

public class Feedback {

	private final String feedbackID;
	private String message;
	private User user;
	private MusicTrack musicTrack;

	/**
	 * 
	 * @param feedbackID
	 *            String Feedback ID for database handling
	 * @param message
	 *            String User's text message;
	 */

	public Feedback(String feedbackID, String message) {
		this.feedbackID = feedbackID;
		this.message = message;
	}

	public String getFeedbackID() {
		return feedbackID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MusicTrack getMusicTrack() {
		return musicTrack;
	}

	public void setMusicTrack(MusicTrack musicTrack) {
		this.musicTrack = musicTrack;
	}

}
