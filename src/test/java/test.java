import com.example.songr.Album;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class test {
    @Test
    void test1(){

        Album album1=new Album("album1","wael",100,250,"album1.com");
        Album album2=new Album("album2","yazan",100,250,"album1.com");
        Album album3=new Album("album3","ahmad",350,450,"album3.com");
        Album []albums={album1,album2,album3};
        Album []albums2=new Album[3];
        albums2[0]=album1;
        albums2[1]=album2;
        albums2[2]=album3;
        assertArrayEquals(albums,albums2);
    }


    @Test
    void Test2(){

        Album album1=new Album("album1","wael",100,250,"album1.com");

        assertEquals(album1.toString(),"Album{title='album1', artist='wael', songCount=100, length='250', imageUrl='album1.com'}");
    }

    @Test
    void Test3(){

        Album album1=new Album("album1","wael",100,250,"album1.com");
        assertEquals(album1.getArtist(),"wael");
        assertEquals(album1.getImageUrl(),"album1.com");
        assertEquals(album1.getSongCount(),100);
        assertEquals(album1.getLength(),250);
        assertEquals(album1.getTitle(),"album1");
    }
    @Test
    void Test4(){

        Album album1=new Album("album1","wael",100,250,"album1.com");
        album1.setArtist("yazan");
        assertEquals(album1.getArtist(),"yazan");
        album1.setImageUrl("al.com");
        assertEquals(album1.getImageUrl(),"al.com");
        album1.setSongCount(200);
        assertEquals(album1.getSongCount(),200);
        album1.setLength(200);
        assertEquals(album1.getLength(),200);
        album1.setTitle("new ALB");
        assertEquals(album1.getTitle(),"new ALB");
    }

}
