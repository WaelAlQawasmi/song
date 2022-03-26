package com.example.songr.Repositoryes;

import com.example.songr.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends  JpaRepository<Album,Long> {
}
