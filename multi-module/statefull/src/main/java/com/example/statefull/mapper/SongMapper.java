package com.example.statefull.mapper;

import com.example.statefull.model.Song;
import com.example.statefull.model.dto.SongDto;

public class SongMapper {
    public static SongDto toSongDto(Song song) {
        return SongDto.builder()
                .id(song.getId())
                .artistId((int) song.getArtistId())
                .artistName(song.getArtistName())
                .name(song.getName())
                .auditions(song.getAuditions())
                .build();
    }
}
