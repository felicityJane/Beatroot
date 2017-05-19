package musicplayer.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import musicplayer.model.GlobalVariables;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpPageController implements Initializable{
    @FXML
    private AnchorPane helpPageAnchorPane;
    @FXML private Label aboutLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GlobalVariables globalVariables = GlobalVariables.getInstance();
        globalVariables.setHelpPageController(this);
        globalVariables.getMainMenuController().menuBarFitToParent(helpPageAnchorPane);
        globalVariables.getMainMenuController().enableMenuItemsAboutPage();

        aboutLabel.setWrapText(true);
        aboutLabel.setText("=================== WELCOME TO THE BEATROOT® COMMUNITY! ====================\n" +
                "Instructions: \n" +
                "\n" +
                "General\n" +
                "\n" +
                "Click on an album thumbnail to see the whole album and load the first song of the album\n" +
                "into the player. \n" +
                "Double-click on a song in the list underneath the player to load into the player.\n" +
                "The song will play automatically.\n" +
                "Click on the download button on the top right corner of the welcome menu to download the\n" +
                "playing song on your computer.\n" +
                "----------------------------------------------------\n" +
                "Rating & comments\n" +
                "\n" +
                "Only songs loaded into the player can be rated and commented.\n" +
                "Click on the pen icon next to the rating stars to add a comment to the song loaded in the player.\n" +
                "Click on the stars to add a rating to the same song.\n" +
                "Only premium users can rate and comment songs.\n" +
                "----------------------------------------------------\n" +
                "Playlists\n" +
                "\n" +
                "Click on the green plus button next to the Playlist list to create a new playlist.\n" +
                "Right-click on the song loaded on the player to add it to a playlist of your choice.\n" +
                "Select a playlist from the list and click on the red minus button to delete it.\n" +
                "Right-click on the list of songs underneath the playlist list to remove it from the playlist.\n" +
                "----------------------------------------------------\n" +
                "Contacts\n" +
                "\n" +
                "Search for a premium or trial user on the search bar on the right of the welcome menu.\n" +
                "Click on the magnifying lens icon to open their user page.\n" +
                "If:\n" +
                " o you are a premium user;\n" +
                " o the selected contact is a premium user;\n" +
                " o the selected contact is not your contact already, and\n" +
                " o the selected contact does not have any pending contact requests\n" +
                "you will be able to send them a contact request. If they accept, they will be added to your contact list \n" +
                "underneath the search bar. To remove them, right-click on their name on the list.\n" +
                "If you are a premium user and have a contact request, you will see a little star on the message icon at the \n" +
                "top right corner. Click on the icon and you will be able to see who sent you the contact request.\n" +
                "You can accept it or decline it. \n" +
                "----------------------------------------------------\n" +
                "Searching\n" +
                "\n" +
                "Search for a song, album or artist on the search bar at the top center of the welcome page.\n" +
                "Select a matching result and click on the magnifying lens icon to load the song/album \n" +
                "on the player. If you search for an artist, select one of their suggested albums and click on the\n" +
                "magnifying lens to load it.");
    }
}
