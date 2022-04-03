package com.example.songr;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity// use to convert this class to table
@Setter// use to create setter for all attribute
@Getter// use to create getter for all attribute
@NoArgsConstructor // to create deflate constructor ( constactor with out any args)
@RequiredArgsConstructor// to create constactor with arg that have  @NonNull
public class auth {
    @GeneratedValue // to auto genrate
  @Id
    private  Long id;
    @NonNull// to add to constracter
    private String username;
    @NonNull // to add to constracter
    private String password;

@OneToMany(mappedBy = "auth")
// to create  relation with  one of (auth) to many (post) where auth the name of  @ManyToOne
  List<post> posts;
// the many post of one auth
}
