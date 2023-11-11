package com.example.statefull.service;

import com.example.statefull.SongRepository;
import com.example.statefull.exception.NotFoundException;
import com.example.statefull.model.ListenRequest;
import com.example.statefull.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListenServiceImpl implements ListenService {
    private final SongRepository songRepository;

    @Override
    public List<Song> getSortedSongsByAuditions(int limit) {
        return songRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Song::getAuditions).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Song> listenSongByIds(ListenRequest listenRequest) {
        List<Song> songs = songRepository.findAllById(listenRequest.getSongs());

        for (Song song : songs) {
            song.setAuditions(song.getAuditions() + listenRequest.getAuditions());
        }

        return songRepository.saveAll(songs);
    }


    @Override
    public Song listenSongByIds(Long id, int auditions) {
        Song song = songRepository.findById(id).orElseThrow(() -> new NotFoundException("Объект не найден"));
        song.setAuditions(song.getAuditions() + auditions);
        return songRepository.save(song);
    }


}
