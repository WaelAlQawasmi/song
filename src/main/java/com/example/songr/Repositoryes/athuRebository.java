package com.example.songr.Repositoryes;

import com.example.songr.auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface athuRebository extends JpaRepository<auth,Long> {

   auth findByusername(String username);

}
