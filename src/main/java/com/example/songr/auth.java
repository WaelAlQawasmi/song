package com.example.songr;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter// use to create setter for all attribute
@Getter// use to create getter for all attribute
@NoArgsConstructor // to create deflate constructor ( constactor with out any args)
@RequiredArgsConstructor// to create constactor with arg that have  @NonNull
public class auth {
    @GeneratedValue
  @Id
    private  Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;

@OneToMany(mappedBy = "auth")
  List<post> posts;
}
