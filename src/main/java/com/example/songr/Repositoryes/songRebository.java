package com.example.songr.Repositoryes;

import com.example.songr.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.List;

@Repository
public interface songRebository extends JpaRepository<Song, Long> {
    List<Song> findByalbum_id(Long id);


}
