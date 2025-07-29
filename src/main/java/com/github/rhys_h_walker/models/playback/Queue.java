package com.github.rhys_h_walker.models.playback;

import java.util.ArrayList;

/**
 * A basic queue which can be used for playing tracks
 */

public class Queue {
    
    private ArrayList<String> queue;

    public Queue() {
        queue = new ArrayList<>();
    }

    /**
     * Get the next element in queue if one exists
     * @return The filepath to the next track
     */
    public String next() {

        if (queue.isEmpty()) {
            return null;
        }

        String n = queue.getFirst();
        queue.removeFirst();
        return n;
    }

    /**
     * Add a song to the current queue
     * @param track The string filepath for the track to queue
     */
    public void enqueue(String track) {
        queue.add(track);
    }

    /**
     * Clear all currently queued songs
     */
    public void clearQueue() {
        queue.clear();
    }
}
