package com.github.rhys_h_walker.models;

import java.util.ArrayList;

import javafx.scene.media.MediaPlayer;

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
     * @return
     */
    public String next() {

        if (queue.isEmpty()) {
            return null;
        }

        String n = queue.getFirst();
        queue.removeFirst();
        return n;
    }

    public void enqueue(String track) {
        queue.add(track);
    }

    public void clearQueue() {
        queue.clear();
    }
}
