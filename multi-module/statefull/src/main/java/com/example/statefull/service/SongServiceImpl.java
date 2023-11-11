package com.example.statefull.service;

import com.example.statefull.ArtistRepository;
import com.example.statefull.SongRepository;
import com.example.statefull.exception.NotFoundException;
import com.example.statefull.mapper.SongMapper;
import com.example.statefull.model.Song;

import com.example.statefull.model.dto.SongDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final ObjectMapper objectMapper;
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public Song save(Song song) {
        Set<ConstraintViolation<Song>> validatorSet = validator.validate(song);
        if (validatorSet.isEmpty()) {
            return songRepository.save(song);
        } else {
            String message = validatorSet.stream()
                    .findFirst()
                    .map(ConstraintViolation::getMessage)
                    .orElseThrow();
            throw new RuntimeException(message);
        }
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(int id) {
        return songRepository.findById((long) id)
                .orElseThrow(() -> new NotFoundException("Объект не найден"));
    }

    @Override
    public Song delete(int id) {
        Song toDelete = findById(id);
        songRepository.delete(toDelete);
        return toDelete;
    }
    @Override
    public Song findSongsByArtistId(int artistId) {
        return songRepository.findByArtistId((int) artistId);
    }
    @Override
    public Song update(int id, String json) {
        Song song = findById(id);
        ObjectReader readeer = objectMapper.readerForUpdating(song);

        Song updated;
        try {
            updated = readeer.readValue(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Не удалось считать данные");
        }

        Set<ConstraintViolation<Song>> validatorSet = validator.validate(updated);
        if (validatorSet.isEmpty()) {
            return save(updated);
        } else {
            String message = validatorSet.stream()
                    .findFirst()
                    .map(ConstraintViolation::getMessage)
                    .orElseThrow();
            throw new RuntimeException(message);
        }
    }
}
