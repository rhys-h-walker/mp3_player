package com.github.rhys_h_walker.models.factories;

import java.io.IOException;

import com.github.rhys_h_walker.controllers.SongCardController;
import com.github.rhys_h_walker.models.data_containers.Album;
import com.github.rhys_h_walker.models.data_containers.SongMetadata;
import com.github.rhys_h_walker.models.playback.PlayBackManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

/**
 * A factory that dynamically creates SongCard objects to be added to the project
 */

public class SongCardFactory {
    
    public HBox createSongCard(SongMetadata meta, PlayBackManager pb) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/song_card.fxml"));
        HBox card;
        try {
            card = loader.load();
        } catch (IOException e) {
            System.err.println("Cannot open fxml file " + e.toString());
            return null;
        }
        
        SongCardController controller = loader.getController();
        controller.setTitle(meta.getTitle());
        controller.setArtist(meta.getArtist());
        controller.setAlbum(meta.getAlbum());
        controller.setCover(meta.getCover());
        controller.setTrack(meta.getFile().getAbsolutePath());
        controller.setPlaybackManager(pb);
        
        return card;
    }

    public HBox[] createSongCardsFromAlbum(Album album, PlayBackManager pb) {
        
        HBox[] arr = new HBox[album.getAlbumLength()];

        for (int x = 0; x < album.getAlbumLength(); x++) {
            arr[x] = createSongCard(album.getMetadata(x), pb);
        }

        return arr;
    }

}
