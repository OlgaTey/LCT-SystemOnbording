package com.example.statefull.model;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class AuditionRequest {
    @Positive
    int auditions;
}
