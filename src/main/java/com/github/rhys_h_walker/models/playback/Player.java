package com.github.rhys_h_walker.models.playback;

import java.io.File;

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
            return;
        }

        curPlayer.play();
    }

    /**
     * Toggles the current tracks paused status
     */
    public void togglePause() {

        if (curPlayer == null) {
            return;
        }

        if (curPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            curPlayer.play();
        } else if (curPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
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
        // If null nothing to stop
        if (curPlayer != null) {
            stop();
        }

        // Null path acts as a stop code essentially
        if (mediaPath == null) {
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
        curPlayer.stop();
    }
}
