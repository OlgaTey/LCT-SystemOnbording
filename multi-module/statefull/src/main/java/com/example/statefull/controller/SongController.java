package com.example.statefull.controller;

import com.example.statefull.ArtistRepository;
import com.example.statefull.exception.NotFoundException;
import com.example.statefull.mapper.ArtistMapper;
import com.example.statefull.mapper.SongMapper;
import com.example.statefull.model.AuditionRequest;
import com.example.statefull.model.ListenRequest;
import com.example.statefull.model.Song;
import com.example.statefull.model.Artist;
import com.example.statefull.model.dto.SongDto;
import com.example.statefull.model.dto.ArtistDto;
import com.example.statefull.service.ArtistService;
import com.example.statefull.service.ListenService;
import com.example.statefull.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;
    private final ArtistService artistService;
    private final ListenService listenService;

    @PostMapping
    public SongDto createSong (@RequestBody Song song){
        Artist artist = new Artist();
        artist.setArtistId(song.getArtistId());
        artist.setArtistName(song.getArtistName());
        ArtistMapper.toArtistDto(artistService.save(artist));
        return SongMapper.toSongDto(songService.save(song));
    }

    @GetMapping
    public List<SongDto> findAll() {
        return songService.findAll().stream()
                .map(SongMapper::toSongDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{songId}")
    public SongDto findById(@PathVariable("songId") int id) {
        return SongMapper.toSongDto(songService.findById(id));
    }


    @DeleteMapping("/{songId}")
    public SongDto delete(@PathVariable("songId") int id) {
        return SongMapper.toSongDto(songService.delete(id));
    }

    @PutMapping("/{songId}")
    public SongDto updateSong(
            @PathVariable("songId") final int id,
            @RequestBody final String json) {
        return SongMapper.toSongDto(songService.update(id, json));
    }

    @GetMapping("/listen")
    public List<SongDto> getSortedSongsByAuditions(@RequestParam final int limit) {
        List<Song> sortedSongs = listenService.getSortedSongsByAuditions(limit);
        return sortedSongs.stream()
                .map(SongMapper::toSongDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/listen")
    public List<SongDto> listenSongByIds(@Valid @RequestBody ListenRequest request) {
        List<Song> updated = listenService.listenSongByIds(request);
        return updated.stream()
                .map(SongMapper::toSongDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/listen")
    public SongDto listenSongByIds(@PathVariable Long id,
                                   @Valid @RequestBody AuditionRequest auditionRequest) {
        return SongMapper.toSongDto(listenService.listenSongByIds(id, auditionRequest.getAuditions()));
    }

}
