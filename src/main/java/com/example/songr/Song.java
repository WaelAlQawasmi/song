package com.example.songr;

import com.example.songr.Album;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"album"})
@Setter// use to create setter for all attribute
@Getter// use to create getter for all attribute
@NoArgsConstructor // to create deflate constructor ( constactor with out any args)
@RequiredArgsConstructor// to create constactor with arg that have  @NonNull
@Entity // to crate table for this class


public class Song {


    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private   String title;
    @NonNull
    private   int trackNumber;

    @NonNull
    private   int length ;

    @ManyToOne // to make relation that many song have one albume

    Album album;
}
