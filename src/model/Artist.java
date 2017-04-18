package model;

import java.util.ArrayList;

import musicPlayer.Album;

public class Artist {
	private String stageName;
	private ArrayList<Album> albums = new ArrayList<Album>();

	public Artist(String stageName) {
		this.stageName = stageName;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		if (!albums.contains(albums))
			return;
		this.albums = albums;
	}

}
