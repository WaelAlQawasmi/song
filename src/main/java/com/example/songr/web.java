package com.example.songr;

import com.example.songr.Repositoryes.AlbumRepository;
import com.example.songr.Repositoryes.songRebository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller// to create mvc
public class web {
    public web(com.example.songr.Repositoryes.AlbumRepository albumRepository, com.example.songr.Repositoryes.songRebository songRebository) {
        AlbumRepository = albumRepository;
        this.songRebository = songRebository;
    }

    @ResponseStatus(value = HttpStatus.OK) // to cheake the resopnse
    @GetMapping("/hello") // to create  route
  String hello(){
     System.out.println("hello world !");
     return "hello";

 }

private final AlbumRepository AlbumRepository;
    private final songRebository songRebository;

    @GetMapping("/capitalize/{word}")
    String capitalize(@PathVariable String word) {

      //  id=id.toUpperCase();


    return "cap";
    }


    @GetMapping("/albums")
    String albums( ModelMap model) {
Album album1=new Album("album1","wael",100,250,"album1.com");
        Album album2=new Album("album2","yazan",100,250,"album1.com");
        Album album3=new Album("album3","ahmad",350,450,"album3.com");
        Album []albums={album1,album2,album3};

System.out.println(albums[1]);
        String IO="HI";

    model.addAttribute("data",album1);
        model.addAttribute("data2",album2);
        model.addAttribute("data3",album3);
        return "albums";
    }



//////////////////////////// ALBUM DB////////////////////////////


    @GetMapping("/addnewalbum")    // FORM ADIND
    public  String adding(){
        return "addingform";
    }




    @PostMapping("/addalbum")
    public RedirectView added(@ModelAttribute Album newAlbum){ // TO ADD FOR DB
        AlbumRepository.save(newAlbum);
        return new RedirectView("/dbalbum");
    }



    @GetMapping("/dbalbum")
    public  String dbAlbumes(Model albu){    // TO GET ALL ALBUM FROM DB
albu.addAttribute("allAlbums",AlbumRepository.findAll());
        return "dbalbum";
    }

///////////////SONG DB//////////////

    @GetMapping("/addsongform/{album_id}")         // ADING FORM
    public  String addSongForm(@PathVariable Long album_id, Model albu){
        Album Abbu = AlbumRepository.findById(album_id).orElseThrow();
        albu.addAttribute("{album_id", Abbu.getId());
        return "addSong";

    }





    @PostMapping("/addsong/{id}")
    public RedirectView addedSong(@ModelAttribute  Song newSong, @PathVariable Long id){   // TO ADD FOR DB BY ALBUME ID

        Album Abbu = AlbumRepository.findById(id).orElseThrow();
        newSong.setAlbum(Abbu);

        songRebository.save(newSong);
        return new RedirectView("/dbalbum");

    }



    @GetMapping("/dbalbumsongs/{id}")   // TO GET ALBUME FROM DBS
    public  String dbsongs(Model adu,@PathVariable Long id){
        System.out.println(".........................."+songRebository.findByalbum_id(id));
        adu.addAttribute("allsong",songRebository.findByalbum_id(id));

        return "dbalbum";

    }


    @GetMapping("/dballsongs")   // TO GET all song
    public  String allsongs(Model songs){

        songs.addAttribute("allsong",songRebository.findAll());

        return "dbalbum";

    }



////////////////// TEST FROM JSON///////////



    @ResponseBody
    @PostMapping("/addalbumbyjson")
    public Album addedalbum(@RequestBody Album newAlbum){

        return    AlbumRepository.save(newAlbum);

    }


    @ResponseBody
@PostMapping("/addsongjson/{id}")
public Song addedSongJson(@RequestBody Song newSong, @PathVariable Long id){

    Album Abbu = AlbumRepository.findById(id).orElseThrow();
    newSong.setAlbum(Abbu);


    return songRebository.save(newSong);

}



    @ResponseBody
    @GetMapping ("/getalbumjson/{id}")
    public List<Song> getalbumjson(@PathVariable Long id){


        return songRebository.findByalbum_id(id);

    }



}
