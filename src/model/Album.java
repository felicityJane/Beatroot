package model;

import javafx.scene.image.Image;

public class Album {
	private String albumName;
	private Image albumCover;

	public Album(String albumName, Image albumCover) {
		this.albumName = albumName;
		this.albumCover = albumCover;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public Image getAlbumCover() {
		return albumCover;
	}

	public void setAlbumCover(Image albumCover) {
		this.albumCover = albumCover;
	}
}
