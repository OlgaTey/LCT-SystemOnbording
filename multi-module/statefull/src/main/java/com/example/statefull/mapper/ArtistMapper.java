package com.example.statefull.mapper;

import com.example.statefull.model.Artist;
import com.example.statefull.model.Song;
import com.example.statefull.model.dto.ArtistDto;
import com.example.statefull.model.dto.SongDto;

public class ArtistMapper {
    public static ArtistDto toArtistDto(Artist artist) {
        return ArtistDto.builder()
                .artistId((int) artist.getArtistId())
                .artistName(artist.getArtistName())
                .build();
    }
}