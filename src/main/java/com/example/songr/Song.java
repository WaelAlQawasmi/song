import com.example.songr.Album;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;

@JsonIgnoreProperties({"cart"})
@Setter// use to create setter for all attribute
@Getter// use to create getter for all attribute
@NoArgsConstructor // to create deflate constructor ( constactor with out any args)
@RequiredArgsConstructor// to create constactor with arg that have  @NonNull
@Entity // to crate table for this class
public class Song {

    @Id // to ceate id
    @GeneratedValue // to auto genrate
    private  int id;

    @NonNull // to make "title" as argumant in the constactor
    private   String title;

    private   int trackNumber;

    @NonNull// to make "length" as argumant in the constactor
    private   int length ;

    @ManyToOne // to make relation that many song have one albume
    Album album;
}
