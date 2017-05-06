package musicplayer.model;

public class Comment {

	private int commentID;
	private String message;
	private User user;
	private MusicTrack musicTrack;

	/**
	 *
	 * @param message
	 *            String User's text message;
	 */

	public Comment(String message) {
		this.message = message;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) { this.commentID = commentID;}

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
