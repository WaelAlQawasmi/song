package com.example.songr;

import com.example.songr.Repositoryes.AlbumRepository;
import com.example.songr.Repositoryes.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller// to create mvc
public class web {
    public web(com.example.songr.Repositoryes.AlbumRepository albumRepository, com.example.songr.Repositoryes.songRebository songRebository, com.example.songr.Repositoryes.athuRebository athuRebository, com.example.songr.Repositoryes.postRebository postRebository) {
        AlbumRepository = albumRepository;
        this.songRebository = songRebository;
        this.athuRebository = athuRebository;
        this.postRebository = postRebository;
    }

    @ResponseStatus(value = HttpStatus.OK) // to cheake the resopnse
    @GetMapping("/hello") // to create  route
  String hello(){
     System.out.println("hello world !");
     return "hello";

 }

private final AlbumRepository AlbumRepository;
    private final songRebository songRebository;
    private final athuRebository athuRebository;
    private final postRebository postRebository;
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

    ///////////////login/////////////////



    @GetMapping("/login")   // TO GET all song
    public  String loginform(){

        return "login";

    }


    @PostMapping("/login")   // TO GET all song

    public  RedirectView login(HttpServletRequest request, String username, String password){
        auth Input=athuRebository.findByusername(username);
        if(Input.getUsername()==null||!BCrypt.checkpw(password, Input.getPassword())){

            return new RedirectView("/signup") ;
        }
        HttpSession session = request.getSession();
        session.setAttribute("username",username);



        return new RedirectView("/dashboard") ;

    }

///////////////////dash bord
    @GetMapping("/dashboard")
    public String getHomepageWithSecret(Model posts,HttpServletRequest request, Model m)
    {

        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        m.addAttribute("username", username);
auth user=athuRebository.findByusername(username);

        List<post> postsDB=postRebository.findByauth_id(user.getId());
        posts.addAttribute("allpost",postsDB);
        return "dash.html";
    }


    ////////////////////sign up

    @GetMapping("/signup")   // TO GET all song
    public  String signup(){

        return "signup";

    }

    @PostMapping("/signup")   // TO GET all song

    public  RedirectView signup(String username,String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10)); // to hach and sult the pass
auth user= new auth(username,hashedPassword);

athuRebository.save(user);
        return new RedirectView("/login") ;

}
//////////////log out


    @PostMapping("/logout")
    public RedirectView logOut(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();

        return new RedirectView("/login");
    }
/////////////add post


    @PostMapping("/addpost/{username}")
    public RedirectView addedPost(@ModelAttribute  post newpost, @PathVariable String username){   // TO ADD FOR DB BY ALBUME ID

        auth user = athuRebository.findByusername(username);
        newpost.setAuth(user);

        postRebository.save(newpost);
        return new RedirectView("/dashboard");

    }



    @PostMapping("/allpost")
    public String allpost(Model posts,HttpServletRequest request, Model m)
    {

        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();


        m.addAttribute("username", username);
        List<post> postsDB=postRebository.findAll();
        posts.addAttribute("allpost",postsDB);
        return "dash.html";
    }
}
