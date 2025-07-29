package com.github.rhys_h_walker.controllers;

import com.github.rhys_h_walker.Logger;
import com.github.rhys_h_walker.models.data_containers.SongMetadata;
import com.github.rhys_h_walker.models.playback.PlayBackManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NavigationBarController {

    @FXML
    private ImageView albumCover;

    @FXML
    private Label songName;

    @FXML
    private Label artistName;
    
    @FXML
    private Button next;
    
    @FXML
    private Button previous;

    @FXML
    private Button play;

    private PlayBackManager pb;

    public void initializeBar(PlayBackManager pb) {

        Logger.logdebug("Initializing Navigation bar");

        this.pb = pb;

        albumCover.setImage(new Image(getClass().getResourceAsStream("/images/PlaceholderImage.png")));

        play.setOnAction(e -> {
            Logger.logprogress("Pausing the current track via nav bar");
            pb.pause();
        });

        next.setOnAction(e -> {
            Logger.logprogress("Skipping the current track via nav bar");
            pb.skip();
        });

        previous.setOnAction(e -> {
            Logger.logprogress("Playing previous track via nav bar");
            pb.previous();
        });

        Logger.logdebug("Adding listener to track current playing track");
        pb.currentTrackProperty().addListener((obs, oldVal, newVal) -> {
            updateCurrentlyPlaying();
        });
    }

    private void updateCurrentlyPlaying() {

        Logger.logdebug("Getting currently playing track");
        String t = pb.current();

        if (t == null) {
            Logger.logdebug("Track null setting to be no song playing");
            albumCover.setImage(new Image(getClass().getResourceAsStream("/images/PlaceholderImage.png")));
            songName.setText("Play a song");
            artistName.setText("");
            return;
        }

        Logger.logdebug("Updating the song information");
        SongMetadata md = new SongMetadata(pb.current());
        albumCover.setImage(md.getCover());
        songName.setText(md.getTitle());
        artistName.setText(md.getArtist());

    }
}
