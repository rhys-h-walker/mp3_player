package com.github.rhys_h_walker.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.rhys_h_walker.models.general_utilities.FileUtilities;

public class FileLocator {
    
    /**
     * Given a path to a directory filter out any non .mp3 files and return an array of these files
     * 
     * @param directory a String which points to the directory
     * @return an array of File objects which are mp3 files
     */
    public static File[] getFilesForDirectory(String directory) {
        File location = new File(directory);

        if (location.listFiles() == null) {
            System.err.println("Location either did not exist of was null");
        }

        File[] filtered = location.listFiles(file -> FileUtilities.getExtension(file.toString()).equals(".mp3"));
        return filtered;
    }

    /**
     * Get an array of directories from within a directory
     * These directories will function as Albums/Playlists etc
     * @param directory
     * @return
     */
    public static File[] getSubdirectories(String directory) {
        File location = new File(directory);

        if (!location.isDirectory()) {
            System.err.println("Provided path is not a valid directory: " + directory);
            return new File[0];
        }

        File[] subdirs = location.listFiles(File::isDirectory);
        return subdirs != null ? subdirs : new File[0];
    }

    /**
     * Get the directory in user.home which should hold the user/Music directory
     * @return
     */
    public static File getMP3Directory() {
        return new File(System.getProperty("user.home")+File.separator+"MP3Player");
    }

    /**
     * Create the directories required to load music
     * @return
     */
    public static File createMP3Directory() {
        File locationsFile = new File(System.getProperty("user.home")+File.separator+"MP3Player");
        locationsFile.mkdirs();
        return locationsFile;
    }

    /**
     * Create the file required to load locations for music
     */
    public static void addToLocationsFile(String musicDirectory) {

        String fileLocation = System.getProperty("user.home") + 
                        File.separator+"MP3Player";

        if (Files.exists(Paths.get(fileLocation)));

        try {
            PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                    new FileWriter(
                        fileLocation +
                        File.separator +
                        "locations"
                    )
                ), 
                true
            );

            pw.println(musicDirectory);
            pw.close();
        } catch (IOException e) {
            System.err.println("Failed to create printwriter, exiting");
            return;
        }

    }

}
