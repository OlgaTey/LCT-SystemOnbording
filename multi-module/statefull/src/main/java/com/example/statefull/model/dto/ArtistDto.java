package com.example.statefull.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class ArtistDto {
    private int artistId;
    private String artistName;
}