package com.github.rhys_h_walker;

import java.io.File;
import java.util.ArrayList;

import com.github.rhys_h_walker.controllers.SongCardController;
import com.github.rhys_h_walker.models.Album;
import com.github.rhys_h_walker.models.FileLocator;
import com.github.rhys_h_walker.models.PlayBackManager;
import com.github.rhys_h_walker.models.Player;
import com.github.rhys_h_walker.models.Queue;
import com.github.rhys_h_walker.models.SongMetadata;
import com.github.rhys_h_walker.models.general_utilities.FileUtilities;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {



    @Override
    public void start(Stage stage) {

        String albumDirectory = "C:\\Users\\Rhysw\\Music\\Black Country New Road - Ants From Up There";
        Album bcnr = new Album(new File(albumDirectory));

        PlayBackManager pbm = new PlayBackManager();

        for (File album : bcnr.getAlbumFiles()) {
            pbm.queue(album.getAbsolutePath());
        }

        Button begin = new Button("Begin");
        Button skip = new Button("Skip");

        begin.setOnAction(e -> pbm.skip());
        skip.setOnAction(e -> pbm.skip());

        VBox layout = new VBox(10);

        SongMetadata bcnrmd = bcnr.getMetadata(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/song_card.fxml"));
        try {
            HBox card = loader.load();
            SongCardController controller = loader.getController();
            controller.setTitle(bcnrmd.getTitle());
            controller.setArtist(bcnrmd.getArtist());
            controller.setAlbum(bcnrmd.getAlbum());
            controller.setCover(bcnrmd.getCover());
            layout.getChildren().add(card);
        } catch (Exception e) {
            System.err.println("General error in loading " + e.toString());
        }
             
        layout.getChildren().addAll(begin, skip);

        // Scene and Stage setup
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("Basic Button App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}