package com.example.statefull;

import com.example.statefull.model.Artist;
import com.example.statefull.model.Song;
import com.example.statefull.model.dto.SongDto;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
    Song findByArtistId(int artistId);

}
