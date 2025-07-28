package com.github.rhys_h_walker.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListViewController {
    
    @FXML
    private VBox content;

    public void setSongsContent(HBox[] songCards) {

        content.getChildren().addAll(songCards);

    }

}
