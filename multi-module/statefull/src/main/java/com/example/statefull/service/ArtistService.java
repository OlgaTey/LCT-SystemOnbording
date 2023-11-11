package com.example.statefull.service;
import org.springframework.data.domain.Pageable;
import com.example.statefull.model.Artist;
import com.example.statefull.model.Song;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Artist save(Artist artist);
    Artist delete(int artistId);
    List<Artist> getAll();
    Artist findByArtistId(int artistId);



}