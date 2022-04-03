package com.example.songr;

import com.example.songr.Album;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreProperties({"auth"}) // to privet go to infinity
@Setter// use to create setter for all attribute
@Getter// use to create getter for all attribute
@NoArgsConstructor // to create deflate constructor ( constactor with out any args)
@RequiredArgsConstructor// to create constactor with arg that have  @NonNull
@Entity // to convert to table
public class post {


    @Setter(value = AccessLevel.NONE) //to privit chang id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; // colum

    @NonNull
    private String text; //colum


    @ManyToOne // to make relation that many post have one albume

    auth auth; // to make mapping with auth table
}