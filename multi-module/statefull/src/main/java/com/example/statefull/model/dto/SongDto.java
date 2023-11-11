package com.example.statefull.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class SongDto {
    private Long id;
    private int artistId;
    private String name;
    private String artistName;
    private int auditions;
}
