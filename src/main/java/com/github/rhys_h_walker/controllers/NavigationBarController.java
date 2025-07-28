package com.github.rhys_h_walker.controllers;

import com.github.rhys_h_walker.models.PlayBackManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NavigationBarController {
    
    @FXML
    private Button next;
    
    @FXML
    private Button previous;

    @FXML
    private Button play;

    private boolean paused = true;

    public void initializeBar(PlayBackManager pb) {

        play.setOnAction(e -> {
            if (paused == true) {
                pb.play();
                paused = false;
            } else {
                pb.pause();
                paused = true;
            }
        });

        next.setOnAction(e -> {
            pb.skip();
        });

        previous.setOnAction(e -> {
            pb.previous();
        });
    }
}
