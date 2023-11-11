package com.example.statefull.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Setter
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static AtomicInteger uniqueId=new AtomicInteger();
    public Song() {artistId = uniqueId.getAndIncrement();}
    private int artistId;
    @NotBlank(message = "Название песни не может быть пустым")
    private String name;
    @NotBlank(message = "Название исполнителя не может быть пустым")
    private String artistName;
    private int auditions;
}
