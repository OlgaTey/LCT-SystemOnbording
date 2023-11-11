package com.example.statefull.service;

import com.example.statefull.model.Artist;
import com.example.statefull.model.Song;

import java.util.List;

public interface SongService {
    Song save(Song song);

    List<Song> findAll();

    Song findById(int id);

    Song delete(int id);

    Song update(int id, String json);


    Song findSongsByArtistId(int artistId);
}
