package com.github.rhys_h_walker.models.Factories;

import java.io.IOException;

import com.github.rhys_h_walker.controllers.ListViewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class SongListFactory {
    
    public ScrollPane createScrollPaneForSongs(HBox[] songCards) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/list_view.fxml"));
        ScrollPane sp;

        try {
            sp = loader.load();
        } catch (IOException e) {
            System.err.println("Cannot open fxml file " + e.toString());
            return null;
        }

        ListViewController controller = loader.getController();
        controller.setSongsContent(songCards);

        return sp;
    }

}
