package musicplayer2.model;

import musicplayer2.DialogBoxManager;

import java.util.ArrayList;

/**
 * Created by Federica on 26/04/2017.
 */
public class UserPlaylistLink {

    private String userName;
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public UserPlaylistLink(String userName, Playlist playlist) {

        this.userName = userName;
        if (playlists.contains(playlist)) {
            DialogBoxManager.errorDialogBox("Duplicate playlist", "The user already has this playlist.");
        } else {
            playlists.add(playlist);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(Playlist p) {
        if (playlists.contains(p)) {
            DialogBoxManager.errorDialogBox("Duplicate playlist", "The user already has this playlist.");
        } else {
            playlists.add(p);
        }
    }

    public void removePlaylist(Playlist pl) {
        if (!playlists.contains(pl))
            DialogBoxManager.errorDialogBox("Non-existing playlist", "The playlist cannot be removed because it does not exist.");
        else {
            playlists.remove(pl);
        }
    }
}
