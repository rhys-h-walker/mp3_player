package com.github.rhys_h_walker.models.Factories;

import java.io.IOException;

import com.github.rhys_h_walker.controllers.NavigationBarController;
import com.github.rhys_h_walker.models.PlayBackManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NavigationBarFactory {
    
    public HBox createNavigationBar(PlayBackManager pb) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/navigation_bar.fxml"));
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
