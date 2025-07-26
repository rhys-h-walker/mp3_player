package com.github.rhys_h_walker.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

public class SongCardController extends HBox {

    @FXML
    private ImageView albumCover;

    @FXML
    private Label songTitle;

    @FXML
    private Label artistName;

    @FXML
    private Label albumName;

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
}