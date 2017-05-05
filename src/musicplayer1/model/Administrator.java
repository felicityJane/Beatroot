package musicplayer1.model;

import java.util.ArrayList;
import java.util.Date;

public class Administrator extends NonTrialUser {

	private Date startDate;
	private float wage, contractHours;
	private String staffID;
	private ArrayList<MusicTrack> addedSongs = new ArrayList<MusicTrack>();
	private ArrayList<MusicTrack> removedSongs = new ArrayList<MusicTrack>();
	private ArrayList<Album> addedAlbums = new ArrayList<Album>();
	private ArrayList<Album> removedAlbums = new ArrayList<Album>();
	private ArrayList<MusicArtist> addedArtists = new ArrayList<MusicArtist>();
	private ArrayList<MusicArtist> removedArtists = new ArrayList<MusicArtist>();
	// private ArrayList<User> friends = new ArrayList<User>();
	// private ArrayList<User> blocked = new ArrayList<User>();

	/**
	 * @param userName
	 *            String Employee's login name
	 * @param displayName
	 *            String The employee's display name within the program, which
	 *            is editable.
	 * @param password
	 *            String Eser's login password
	 * @param firstName
	 *            String Employee's first name
	 * @param lastName
	 *            String Employee's last name
	 * @param dateOfBirth
	 *            Date Employee's date of birth
	 * @param emailAddress
	 *            Employee's address
	 * @param physicalAddress
	 *            String Employee's address
	 * @param cityOfResidence
	 *            String Employee's city of residence
	 * @param postalCode
	 *            String Employee's postal code
	 * @param country
	 *            String Employee's country of residence
	 * @param gender
	 *            Enum {@link: Gender} User's gender
	 * @param phoneNumber
	 *            String Employee's phone number
	 * @param startDate
	 *            Date Employee's start date according to contract
	 * @param wage
	 *            Float Employee's current wage
	 * @param contractHours
	 *            Float Employee's hours by contract (for overtime purposes)
	 * @param staffID
	 *            String Employee's ID code
	 */

	public Administrator(String userName, String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence, String postalCode,
			Country country, Gender gender, String phoneNumber, Date startDate, float wage, float contractHours,
			String staffID) {
		super(userName, displayName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress,
				cityOfResidence, postalCode, country, gender, phoneNumber);
		this.startDate = startDate;
		this.wage = wage;
		this.contractHours = contractHours;
		this.staffID = staffID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public float getWage() {
		return wage;
	}

	public void setWage(float wage) {
		this.wage = wage;
	}

	public float getContractHours() {
		return contractHours;
	}

	public void setContractHours(float contractHours) {
		this.contractHours = contractHours;
	}

	public String getStaffID() {
		return staffID;
	}

	public ArrayList<MusicTrack> getAddedSongs() {
		return addedSongs;
	}

	public void setAddedSongs(ArrayList<MusicTrack> addedSongs) {
		this.addedSongs = addedSongs;
	}

	public ArrayList<MusicTrack> getremovedSongs() {
		return removedSongs;
	}

	public void setRemovedSongs(ArrayList<MusicTrack> removedSongs) {
		this.removedSongs = removedSongs;
	}

	public void addSong(MusicTrack song) {
		if (addedSongs.contains(song))
			return;
		addedSongs.add(song);
	}

	public void removeSong(MusicTrack song) {
		if (!addedSongs.contains(song))
			return;
		addedSongs.remove(song);
		removedSongs.add(song);
	}

	public ArrayList<Album> getAddedAlbums() {
		return addedAlbums;
	}

	public void setAddedAlbums(ArrayList<Album> addedAlbums) {
		this.addedAlbums = addedAlbums;
	}

	public ArrayList<Album> getRemovedAlbums() {
		return removedAlbums;
	}

	public void setRemovedAlbums(ArrayList<Album> removedAlbums) {
		this.removedAlbums = removedAlbums;
	}

	public void addAlbum(Album album) {
		if (addedAlbums.contains(album))
			return;
		addedAlbums.add(album);
	}

	public void removeAlbum(Album album) {
		if (!addedAlbums.contains(album))
			return;
		addedAlbums.remove(album);
		removedAlbums.add(album);
	}

	public void addMusicArtist(MusicArtist artist) {
		if (addedArtists.contains(artist))
			return;
		addedArtists.add(artist);
	}

	public void removeMusicArtist(MusicArtist artist) {
		if (!addedArtists.contains(artist))
			return;
		addedArtists.remove(artist);
		removedArtists.add(artist);
	}

	public void updateUserInformation(User u, String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence, String postalCode,
			Country country, String phoneNumber) {
		u.changeAccountSettings(displayName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress,
				cityOfResidence, postalCode, country, phoneNumber);
	}

	public void updatePremiumUserInformation(PremiumUser u, String displayName, String password, String firstName,
			String lastName, Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence,
			String postalCode, Country country, String phoneNumber) {
		u.changeAccountSettings(displayName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress,
				cityOfResidence, postalCode, country, phoneNumber);
	}

	public void modifyMusicTrackInformation(MusicTrack mt) {
		// mt.modifyTrackDetails();
	}

	// TODO Check if I'm missing something
	// @Override
	// public String toString() {
	// DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	// String output = format.format(getStartDate());
	//
	// return "Employee details:\nFirst name: " + getFirstName() + "\nStart
	// date: " + output;
	// }
}
