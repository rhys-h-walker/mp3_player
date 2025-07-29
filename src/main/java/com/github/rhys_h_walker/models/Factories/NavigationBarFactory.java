package com.github.rhys_h_walker.models.factories;

import java.io.IOException;

import com.github.rhys_h_walker.controllers.NavigationBarController;
import com.github.rhys_h_walker.models.playback.PlayBackManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class NavigationBarFactory {
    
    /**
     * Create an correctly initialize a navigation bar
     * @param pb The playback manager to use
     * @return A created and initialized navigation bar
     */
    public HBox createNavigationBar(PlayBackManager pb) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/navigation_bar.fxml"));
        HBox bar;
        try {
            bar = loader.load();
        } catch (IOException e) {
            System.err.println("Cannot open fxml file " + e.toString());
            return null;
        }

        NavigationBarController controller = loader.getController();
        controller.initializeBar(pb);

        return bar;
    }

}
