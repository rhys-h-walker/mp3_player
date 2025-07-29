package com.github.rhys_h_walker.models.playback;

import com.github.rhys_h_walker.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.MediaPlayer;

/**
 * The manager of all media playback
 */
public class PlayBackManager {
    
    private Queue queue = new Queue();
    private Player player = new Player();
    private StringProperty filepath = new SimpleStringProperty();

    /**
     * Constructor does nothing
     */
    public PlayBackManager() {
        // Do nothing, not setup required
    }

    /**
     * Clear the current queue and begin playing from the given track
     */
    public void start(String filepath) {
        Logger.logdebug("Starting playback with a new track");
        queue.clearQueue();
        queue.enqueue(filepath);
        loadNextPlayTrack();
    }

    /**
     * Skip the current track for the next in the queue
     */
    public void skip() {
        Logger.logdebug("Skipping current track");
        loadNextPlayTrack();
    }

    /**
     * Pause the current song
     */
    public void pause() {
        Logger.logdebug("Pausing current track");
        player.togglePause();
    }

    /**
     * Play the current song
     */
    public void play() {
        Logger.logdebug("Playing current track");
        player.play();
    }

    /**
     * Return to the previous song
     */
    public void previous() {
        Logger.logwarn("Not implemented yet.");
    }

    /**
     * Add a track to the current queue
     * @param filepath Filepath of the track to queue
     */
    public void queue(String filepath) {
        Logger.logdebug("Enqueue new track " + filepath);
        queue.enqueue(filepath);
    }

    /**
     * Get the path to the currently playing song
     * @return Path to current song
     */
    public String current() {
        Logger.logdebug("Returning current track");
        return this.filepath.get();
    }

    /**
     * Return a StringProperty to attatch a listener to
     * @return The StringProperty to attatch a listener to
     */
    public StringProperty currentTrackProperty() {
        Logger.logdebug("Returning current track property");
        return this.filepath;
    }

    /**
     * Load the next track in the queue and start playing,
     * making sure to add the end of media command
     */
    private void loadNextPlayTrack() {
        Logger.logdebug("Loading new track from file");
        this.filepath.set(queue.next());
        MediaPlayer mp = player.loadTrack(this.filepath.get());

        // Check if we are out of tracks
        if (mp == null) {
            Logger.logdebug("Out of tracks to play, null code received");
            return;
        }

        Logger.logdebug("Attaching end of media to current track");
        mp.setOnEndOfMedia(() -> {
            this.filepath.set(queue.next());
            player.loadTrack(this.filepath.get());
            player.play();
        });

        Logger.logdebug("Playing new track");
        player.play();
    }

}
