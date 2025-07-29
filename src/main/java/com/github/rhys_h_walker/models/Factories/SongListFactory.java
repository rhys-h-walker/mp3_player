package com.github.rhys_h_walker.models.factories;

import java.io.IOException;

import com.github.rhys_h_walker.Logger;
import com.github.rhys_h_walker.controllers.ListViewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class SongListFactory {
    
    /**
     * Create a ScrollPane full of SongCards
     * @param songCards Array of SongCards
     * @return The completed ScrollPane
     */
    public ScrollPane createScrollPaneForSongs(HBox[] songCards) {

        Logger.logdebug("Loading fxml file list_view");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/list_view.fxml"));
        ScrollPane sp;

        try {
            Logger.logdebug("Getting stackpane from file");
            sp = loader.load();
        } catch (IOException e) {
            Logger.logerror("Cannot open fxml file " + e.toString());
            return null;
        }

        Logger.logdebug("Getting controller from FXML loader");
        ListViewController controller = loader.getController();

        Logger.logdebug("Setting songCards");
        controller.setSongsContent(songCards);

        return sp;
    }

}
