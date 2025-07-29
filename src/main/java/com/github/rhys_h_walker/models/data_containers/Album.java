package com.github.rhys_h_walker.models.data_containers;

import java.io.File;
import java.util.Arrays;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;

import com.github.rhys_h_walker.models.FileLocator;

public class Album {

    private File[] albumContents;
    
    /**
     * Take a location of the album and read all mp3 files from within
     * 
     * Read a from the file and sort the songs in order based on the TRACK number field
     * 
     * @param albumLocation The directory that the album should be built from
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

    /**
     * Get a full list of files in the album
     * @return
     */
    public File[] getAlbumFiles() {
        return albumContents;
    }

    /**
     * Get the total number of tracks in the album
     * @return
     */
    public int getAlbumLength() {
        return albumContents.length;
    }

    /**
     * Retrueve metadata on a specific track in the album
     * @param songNum
     * @return
     */
    public SongMetadata getMetadata(int songNum) {
        if (songNum > albumContents.length) {
            return null;
        }

        return new SongMetadata(albumContents[songNum].getAbsolutePath());
    }

}
