package com.github.rhys_h_walker.models;

import java.io.File;

import javafx.scene.image.Image;

public class SongMetadata {
    
    private String title;
    private String artist;
    private String album;
    private Image cover;
    private File file;

    public SongMetadata(String title, String artist, String album, Image cover, File file) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.cover = cover;
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public Image getCover() {
        return cover;
    }

    public File getFile() {
        return file;
    }
}
