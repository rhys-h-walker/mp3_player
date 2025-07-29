package com.github.rhys_h_walker.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import com.github.rhys_h_walker.Logger;
import com.github.rhys_h_walker.models.playback.PlayBackManager;

public class SongCardController extends HBox {

    private PlayBackManager pb;
    private String track;

    @FXML
    private ImageView albumCover;

    @FXML
    private Label songTitle;

    @FXML
    private Label artistName;

    @FXML
    private Label albumName;

    @FXML
    private Button queue;

    @FXML
    private Button play;
    
    @FXML
    public void initialize() {
        Logger.logdebug("Initializing song card");

        queue.setOnAction(e -> {
            pb.queue(track);
        });

        play.setOnAction(e -> {
            pb.start(track);
        });
    }

    public void setTitle(String title) {
        this.songTitle.setText(title);
    }

    public void setAlbum(String album) {
        this.albumName.setText(album);
    }

    public void setArtist(String artist) {
        this.artistName.setText(artist);
    }

    public void setCover(Image image) {
        albumCover.setImage(image);
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setPlaybackManager(PlayBackManager pb) {
        this.pb = pb;
    }
}