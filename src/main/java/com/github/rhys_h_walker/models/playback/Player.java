package com.github.rhys_h_walker.models.playback;

import java.io.File;

import com.github.rhys_h_walker.Logger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * A wrapper class for the MediaPlayer
 * 
 * Can only take MP3 audio files
 */

public class Player {

    private MediaPlayer curPlayer;

    /**
     * Constructor does nothing
     */
    public Player() {
        // Do nothing, not setup required
    }

    /**
     * Begin playback of the currently loaded track
     * If none loaded nothing will happen
     */
    public void play() {

        if (curPlayer == null) {
            Logger.logerror("MediaPlayer null when attempting to play a track");
            return;
        }

        curPlayer.play();
    }

    /**
     * Toggles the current tracks paused status
     */
    public void togglePause() {

        if (curPlayer == null) {
            Logger.logwarn("Attempting to pause when track null");
            return;
        }

        if (curPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            Logger.logdebug("Resuming track");
            curPlayer.play();
        } else if (curPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            Logger.logdebug("Pausing track");
            curPlayer.pause();
        }

    }

    /**
     * Load a track int the current mediaPlayer.
     * This stops the old track and deassigns it to be collected by the GC
     * @param mediaPath The path to the mp3 file to load
     * @return The MediaPlayer object
     */
    public MediaPlayer loadTrack(String mediaPath) {
        Logger.logdebug("Loading new track");

        // If null nothing to stop
        if (curPlayer != null) {
            Logger.logdebug("Stopping current track");
            stop();
        }

        // Null path acts as a stop code essentially
        if (mediaPath == null) {
            Logger.logdebug("New path is null, stopping playback");
            curPlayer = null;
            return null;
        }
        
        curPlayer = new MediaPlayer(new Media(new File(mediaPath).toURI().toString()));
        return curPlayer;
    }

    /**
     * Stop playback of the current object
     */
    public void stop() {
        Logger.logdebug("Stopping playback");
        curPlayer.stop();
    }
}
