package com.example.statefull.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Entity
@Table(name = "artist")


public class Artist {
    @Id
    private int artistId;
    @NotBlank(message = "Название исполнителя не может быть пустым")
    private String artistName;
}