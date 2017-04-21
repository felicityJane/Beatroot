package musicPlayer.model;

public class Comment {

	private final String commentID;
	private String message;
	private User user;
	private MusicTrack musicTrack;

	/**
	 * 
	 * @param commentID
	 *            String Comment ID
	 * @param message
	 *            String User's text message;
	 */

	public Comment(String commentID, String message) {
		this.commentID = commentID;
		this.message = message;
	}

	public String getCommentID() {
		return commentID;
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
