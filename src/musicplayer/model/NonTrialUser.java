package musicplayer.model;

import java.util.ArrayList;
import java.util.Date;

public class NonTrialUser extends User {

	private ArrayList<User> friends = new ArrayList<User>();
	private ArrayList<User> blocked = new ArrayList<User>();

	public NonTrialUser(String userName, String displayName, String password, String firstName, String lastName,
			Date dateOfBirth, String emailAddress, String physicalAddress, String cityOfResidence, String postalCode,
			Country country, Gender gender, String phoneNumber) {
		super(userName, displayName, password, firstName, lastName, dateOfBirth, emailAddress, physicalAddress,
				cityOfResidence, postalCode, country, gender, phoneNumber);
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public ArrayList<User> getBlocked() {
		return blocked;
	}

	public void setBlocked(ArrayList<User> blocked) {
		this.blocked = blocked;
	}

	public void addFriend(User user) {
		if (friends.contains(user))
			return;
		friends.add(user);
	}

	public void removeFriend(User user) {
		if (!friends.contains(user))
			return;
		friends.remove(user);
	}

	public void blockPerson(User user) {
		if (blocked.contains(user))
			return;
		blocked.add(user);
	}

	public void unblockPerson(User user) {
		if (!blocked.contains(user))
			return;
		blocked.remove(user);
	}
}
