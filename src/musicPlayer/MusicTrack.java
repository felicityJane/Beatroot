package musicPlayer;

import java.time.Duration;

/**
 * Created by fatih on 2017-04-13.
 */
public class MusicTrack {

	private String trackName;
	private Genre musicGenre;
	private Duration time;

	public MusicTrack(String trackName, String string, musicPlayer.model.Genre classical) {
		this.trackName = trackName;
		this.musicGenre = string;
		this.time = classical;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Genre getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(Genre musicGenre) {
		this.musicGenre = musicGenre;
	}

	public Duration getTime() {
		return time;
	}

	public void setTime(Duration time) {
		this.time = time;
	}
}
