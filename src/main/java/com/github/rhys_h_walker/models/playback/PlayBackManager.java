package com.github.rhys_h_walker.models.playback;

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

    public PlayBackManager() {

    }

    /**
     * Clear the current queue and begin playing from the given track
     */
    public void start(String filepath) {
        queue.clearQueue();
        queue.enqueue(filepath);
        loadNextPlayTrack();
    }

    /**
     * Skip the current track for the next in the queue
     */
    public void skip() {
        loadNextPlayTrack();
    }

    public void pause() {
        player.togglePause();
    }

    public void play() {
        player.play();
    }

    public void previous() {
        System.err.println("Not implemented yet.");
    }

    /**
     * Add a track to the current queue
     * @param filepath
     */
    public void queue(String filepath) {
        queue.enqueue(filepath);
    }

    /**
     * Get the path to the currently playing song
     * @return
     */
    public String current() {
        return this.filepath.get();
    }

    /**
     * Return a StringProperty to attatch a listener to
     * @return
     */
    public StringProperty currentTrackProperty() {
        return this.filepath;
    }

    /**
     * Load the next track in the queue and start playing,
     * making sure to add the end of media command
     */
    private void loadNextPlayTrack() {
        this.filepath.set(queue.next());
        MediaPlayer mp = player.loadTrack(this.filepath.get());

        // Check if we are out of tracks
        if (mp == null) {
            return;
        }

        mp.setOnEndOfMedia(() -> {
            this.filepath.set(queue.next());
            player.loadTrack(this.filepath.get());
            player.play();
        });
        player.play();
    }

}
