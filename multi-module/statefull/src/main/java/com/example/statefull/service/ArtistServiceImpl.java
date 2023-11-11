package com.example.statefull.service;

import com.example.statefull.ArtistRepository;
import com.example.statefull.SongRepository;
import com.example.statefull.exception.NotFoundException;
import com.example.statefull.model.Artist;
import com.example.statefull.model.ListenRequest;
import com.example.statefull.model.Song;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService{
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final ObjectMapper objectMapper;
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final JdbcTemplate template;

    @Override
    public Artist save(Artist artist) {
        Set<ConstraintViolation<Artist>> validatorSet = validator.validate(artist);
        if (validatorSet.isEmpty()) {
            return artistRepository.save(artist);
        } else {
            String message = validatorSet.stream()
                    .findFirst()
                    .map(ConstraintViolation::getMessage)
                    .orElseThrow();
            throw new RuntimeException(message);
        }
    }
    @Override
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findByArtistId(int artistId) {
        return artistRepository.findById((long) artistId)
                .orElseThrow(() -> new NotFoundException("Объект не найден"));
    }


    @Override
    public Artist delete(int artistId) {
        Artist toDelete = findByArtistId(artistId);
        artistRepository.delete(toDelete);
        return toDelete;
    }

}