package com.example.statefull.model;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CreateSong {
    String artistName;
    String name;
    @Positive
    int auditions;
}
