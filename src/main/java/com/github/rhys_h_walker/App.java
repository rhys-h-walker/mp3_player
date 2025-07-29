package com.github.rhys_h_walker;

import java.io.File;

import com.github.rhys_h_walker.models.data_containers.Album;
import com.github.rhys_h_walker.models.factories.NavigationBarFactory;
import com.github.rhys_h_walker.models.factories.SongCardFactory;
import com.github.rhys_h_walker.models.factories.SongListFactory;
import com.github.rhys_h_walker.models.playback.PlayBackManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {



    @Override
    public void start(Stage stage) {
        // Disable the JAudioTagger Logging
        java.util.logging.Logger.getLogger("org.jaudiotagger").setLevel(java.util.logging.Level.OFF);

        // Initialize the logigng algorithm
        Logger.initializeLogger("mp3_player", true);

        // Temporary hardcoding for testing
        String albumDirectory = "C:\\Users\\Rhysw\\Music\\Black Country New Road - Ants From Up There";
        Album bcnr = new Album(new File(albumDirectory));

        Logger.logprogress("Creating factories");

        PlayBackManager pbm = new PlayBackManager();
        SongCardFactory sf = new SongCardFactory();
        SongListFactory slf = new SongListFactory();
        NavigationBarFactory nvb = new NavigationBarFactory();

        VBox layout = new VBox(10);

        Logger.logprogress("Creating scrollpane");

        ScrollPane sp = slf.createScrollPaneForSongs(sf.createSongCardsFromAlbum(bcnr, pbm));

        Logger.logprogress("Creating navigation bar");

        HBox navigationBar = nvb.createNavigationBar(pbm);
        
        layout.getChildren().addAll(sp, navigationBar);

        Logger.logprogress("Creating scene and loading page");

        // Scene and Stage setup
        Scene scene = new Scene(layout, 800, 600);
        stage.setTitle("Basic Button App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Logger.logprogress("Launching application");

        launch();
    }

}