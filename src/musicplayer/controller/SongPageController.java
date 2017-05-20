package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import musicplayer.DB_Connector;
import musicplayer.model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SongPageController implements Initializable {
    @FXML private AnchorPane songPageAnchorPane;
    @FXML private ImageView imageView, starRatingImageView;
    @FXML private Label albumLabel,artistLabel,trackLabel,trackTimeLabel,publicationLabel, ratingLabel, songNameLabel;
    @FXML private ListView userCommentList;
    private DB_Connector db_connector = new DB_Connector("jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
    GlobalVariables globalVariables = GlobalVariables.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        globalVariables.setSongPageController(this);
        userCommentList.getItems().clear();
        getAlbumInfo();
    }
    private void getAlbumInfo(){
        Album album = globalVariables.getAlbum();
        MusicTrack musicTrack = globalVariables.getMusicTrack();
        MusicArtist musicArtist = globalVariables.getMusicArtist();
        db_connector.getComments(musicTrack.getID());
        ArrayList<Comment> comments = globalVariables.getComments();
        db_connector.getRating(musicTrack.getRatingId());
        Rating rating = globalVariables.getRating();

        imageView.setImage(album.getAlbumCover());
        DropShadow dropShadow = new DropShadow(10, 0, 0, Color.GRAY);
        imageView.setEffect(dropShadow);

        albumLabel.setText(String.format("Album : %-20s" , album.getAlbumName()));
        artistLabel.setText(String.format("Artist : %-20s" , musicArtist.getStageName()));
        trackLabel.setText(String.format("%-20s", musicTrack.getTrackName()));
        trackTimeLabel.setText(String.format("Length : %-20s" , musicTrack.getTrackTime()));
        String publicationDate = String.valueOf(musicTrack.getPublicationYear());
        publicationLabel.setText(String.format("Release Date : %-20s" , publicationDate.substring(0,4)));

        if (comments != null){
            for (Comment c : comments){
                userCommentList.getItems().add(c.getMessage());
            }
        }else {
            userCommentList.getItems().add("No comments yet...");
        }

        globalVariables.getWelcomeMenuController().setRatingStars(ratingLabel, rating,starRatingImageView);
    }
}