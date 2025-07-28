package com.github.rhys_h_walker.models;

import javafx.scene.media.MediaPlayer;

/**
 * The manager of all media playback
 */
public class PlayBackManager {
    
    private Queue queue = new Queue();
    private Player player = new Player();

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
        player.pause();
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
     * Load the next track in the queue and start playing,
     * making sure to add the end of media command
     */
    private void loadNextPlayTrack() {
        MediaPlayer mp = player.loadTrack(queue.next());

        // Check if we are out of tracks
        if (mp == null) {
            return;
        }

        mp.setOnEndOfMedia(() -> {
            player.loadTrack(queue.next());
            player.play();
        });
        player.play();
    }

}
