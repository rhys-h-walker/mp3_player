package com.github.rhys_h_walker.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.rhys_h_walker.Logger;
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
            Logger.logerror("Location either did not exist or was null");
            return new File[0]; // Return empty array instead of null
        }

        Logger.logdebug("Locating files with extension .mp3");

        File[] filtered = location.listFiles(file -> FileUtilities.getExtension(file.toString()).equals(".mp3"));
        return filtered != null ? filtered : new File[0];
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
            Logger.logerror("Provided path is not a valid directory: " + directory);
            return new File[0];
        }

        Logger.logdebug("Locating subdirectories");

        File[] subdirs = location.listFiles(File::isDirectory);
        return subdirs != null ? subdirs : new File[0];
    }

    /**
     * Get the directory in user.home which should hold the user/Music directory
     * @return
     */
    public static File getMP3Directory() {
        Logger.logdebug("Locating MP3Player directory");
        return new File(System.getProperty("user.home")+File.separator+"MP3Player");
    }

    /**
     * Create the directories required to load music
     * @return
     */
    public static File createMP3Directory() {
        Logger.logdebug("Creating MP3Player directory");
        File locationsFile = new File(System.getProperty("user.home")+File.separator+"MP3Player");
        locationsFile.mkdirs();
        return locationsFile;
    }

    /**
     * Create the file required to load locations for music
     */
    public static void addToLocationsFile(String musicDirectory) {

        Logger.logdebug("Getting MP3Player directory");
        String fileLocation = System.getProperty("user.home") + 
                        File.separator+"MP3Player";

        if (Files.exists(Paths.get(fileLocation)));

        try {
            Logger.logdebug("Creating new PrintWriter");
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

            Logger.logdebug("Writing to directory");
            pw.println(musicDirectory);
            pw.close();
        } catch (IOException e) {
            Logger.logerror("Failed to create printwriter, exiting");
            return;
        }

    }

}
