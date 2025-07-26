package com.github.rhys_h_walker.models;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * A wrapper class for the MediaPlayer
 * 
 * Can only take MP3 audio files
 */

public class Player {

    private MediaPlayer curPlayer;

    public Player() {
        // Do nothing, not setup required
    }

    public void play() {

        if (curPlayer == null) {
            return;
        }

        curPlayer.play();
    }

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

    public void stop() {
        curPlayer.stop();
    }
}
