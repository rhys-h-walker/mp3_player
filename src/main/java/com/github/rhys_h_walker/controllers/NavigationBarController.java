package com.github.rhys_h_walker.controllers;

import com.github.rhys_h_walker.models.PlayBackManager;
import com.github.rhys_h_walker.models.SongMetadata;

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

    private boolean paused = true;
    private PlayBackManager pb;

    public void initializeBar(PlayBackManager pb) {

        this.pb = pb;

        albumCover.setImage(new Image(getClass().getResourceAsStream("/images/PlaceholderImage.png")));

        play.setOnAction(e -> {
            if (paused == true) {
                pb.play();
                paused = false;
            } else {
                pb.pause();
                paused = true;
            }
        });

        next.setOnAction(e -> {
            pb.skip();
        });

        previous.setOnAction(e -> {
            pb.previous();
        });

        pb.currentTrackProperty().addListener((obs, oldVal, newVal) -> {
            updateCurrentlyPlaying();
        });
    }

    private void updateCurrentlyPlaying() {
        SongMetadata md = new SongMetadata(pb.current());
        albumCover.setImage(md.getCover());
        songName.setText(md.getTitle());
        artistName.setText(md.getArtist());

    }
}
