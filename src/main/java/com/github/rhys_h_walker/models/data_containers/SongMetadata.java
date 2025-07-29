package com.github.rhys_h_walker.models.data_containers;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

import com.github.rhys_h_walker.Logger;

import javafx.scene.image.Image;

public class SongMetadata {
    
    private String title;
    private String artist;
    private String album;
    private Image cover;
    private File file;

    /**
     * Create a metadata object with each of the required values
     * @param title
     * @param artist
     * @param album
     * @param cover
     * @param file
     */
    public SongMetadata(String title, String artist, String album, Image cover, File file) {

        Logger.logdebug("Creating metadata from all fields specified");

        this.title = title;
        this.artist = artist;
        this.album = album;
        this.cover = cover;
        this.file = file;
    }

    /**
     * Create a metadata object from a filepath
     * @param filepath The path to the metadata object
     */
    public SongMetadata(String filepath) {

        Logger.logdebug("Creating metadata object from file location");

        this.file = new File(filepath);

        try {
            Tag tag = AudioFileIO.read(this.file).getTag();
            
            this.title = tag.getFirst(FieldKey.TITLE);
            this.artist = tag.getFirst(FieldKey.ARTIST);
            this.album = tag.getFirst(FieldKey.ALBUM);
            Artwork artwork = tag.getFirstArtwork();
            this.cover = new Image(new ByteArrayInputStream(artwork.getBinaryData()));

        } catch (Exception e) {
            Logger.logerror("Error when reading tags");
            return;
        }
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
