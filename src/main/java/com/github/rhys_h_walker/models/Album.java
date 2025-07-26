package com.github.rhys_h_walker.models;

import java.io.File;
import java.util.Arrays;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Album {

    private File[] albumContents;
    
    /**
     * Take a location of the album and read all mp3 files from within
     * @param albumLocation
     */
    public Album(File albumLocation) {
        albumContents = FileLocator.getFilesForDirectory(albumLocation.getAbsolutePath());
            
        Arrays.sort(albumContents, (a, b) -> {
            try {
                return Integer.parseInt(AudioFileIO.read(a).getTag().getFirst(FieldKey.TRACK)) - Integer.parseInt(AudioFileIO.read(b).getTag().getFirst(FieldKey.TRACK));
            } catch (Exception e) {
                System.err.println("Error when sorting albumContents");
                return 0;
            }
        });
    }

    public File[] getAlbumFiles() {
        return albumContents;
    }

    public void printAlbumContents() {
        for (File song: albumContents) {
            try {
                Tag tag = AudioFileIO.read(song).getTag();
                System.out.println("Title: " + tag.getFirst(FieldKey.TITLE));
            } catch (Exception e) {
                System.err.println("Error when reading tags");
            }
        }
    }

    

}
