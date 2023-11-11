package com.example.statefull.model;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.List;

@Getter
public class ListenRequest {
    @Positive
    private int auditions;
    private List<Long> songs;
}

