package com.example.statefull.controller;

import com.example.statefull.SongRepository;
import com.example.statefull.mapper.ArtistMapper;
import com.example.statefull.mapper.SongMapper;
import com.example.statefull.model.Artist;
import com.example.statefull.model.Song;
import com.example.statefull.model.dto.ArtistDto;
import com.example.statefull.model.dto.SongDto;
import com.example.statefull.service.ArtistService;
import com.example.statefull.service.ListenService;
import com.example.statefull.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final SongService songService;
    private final ListenService listenService;
    private final ArtistService artistService;
    private final SongRepository songRepository;

    @PostMapping
    public ArtistDto createArtist(@RequestBody Artist artist) {
        return ArtistMapper.toArtistDto(artistService.save(artist));
    }

    @GetMapping
    public List<ArtistDto> getAll() {
        return artistService.getAll().stream()
                .map(ArtistMapper::toArtistDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{artistId}")
    public ArtistDto findByArtistId(@PathVariable("artistId") int artistId) {
        return ArtistMapper.toArtistDto(artistService.findByArtistId(artistId));
    }
    @GetMapping("/{artistId}/songs")
    public SongDto findSongsByArtistId(@PathVariable("artistId") int artistId) {
        return SongMapper.toSongDto(songService.findSongsByArtistId(artistId));
    }

    @DeleteMapping("/{artistId}")
    public ArtistDto delete(@PathVariable("artistId") int artistId) {
        return ArtistMapper.toArtistDto(artistService.delete(artistId));
    }
    //@GetMapping("/{artistId}/songs")
    //public void findSongsByArtistId(int artistId) {
    //    List<Song> songs = songRepository.findAll();
    //    for (Song song : songs) {
    //        if (song.getArtistId() == artistId){
    //            return SongMapper.toSongDto(songService.Song song);
    //        }
    //    }
    //}
}
