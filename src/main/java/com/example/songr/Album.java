package com.example.songr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;


@Setter// use to create setter for all attribute
@Getter// use to create getter for all attribute
@NoArgsConstructor // to create deflate constructor ( constactor with out any args)

@Entity // to crate table for this class
public class Album {

    @Id
    @GeneratedValue
    private  Long id;
    private   String title;
    private   String artist;
    private   int songCount;
    private   int length ;
    private   String imageUrl ;


//////////////////////////



    public Album(String title, String artist, int songCount, int length, String imageUrl) {
        this.title = title;
        this.artist = artist;
        this.songCount = songCount;
        this.length = length;
        this.imageUrl = imageUrl;
    }




    @OneToMany(mappedBy = "album")
    private List<Song> Songs;


    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", songCount=" + songCount +
                ", length='" + length + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
