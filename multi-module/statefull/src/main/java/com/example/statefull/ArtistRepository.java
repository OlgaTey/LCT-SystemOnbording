package com.example.statefull;

import com.example.statefull.model.Artist;
import com.example.statefull.model.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{

    List<Artist> findByArtistId(int artistId);
}