package com.example.statefull.service;

import com.example.statefull.model.ListenRequest;
import com.example.statefull.model.Song;

import java.util.List;

public interface ListenService {

    List<Song> getSortedSongsByAuditions(int limit);

    List<Song> listenSongByIds(ListenRequest listenRequest);

    Song listenSongByIds(Long id, int auditions);

}
