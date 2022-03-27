package com.example.songr.Repositoryes;

import com.example.songr.auth;
import com.example.songr.post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface postRebository extends JpaRepository<post,Long> {
List<post> findByauth_id(Long id);
}
