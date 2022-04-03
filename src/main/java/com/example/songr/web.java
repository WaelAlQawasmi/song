package com.example.songr;

import com.example.songr.Repositoryes.AlbumRepository;
import com.example.songr.Repositoryes.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AlbumRepository AlbumRepository; // call repo
    private final songRebository songRebository;// call repo
    private final athuRebository athuRebository;// call repo
    private final postRebository postRebository; // call repo

    public web(com.example.songr.Repositoryes.AlbumRepository albumRepository, com.example.songr.Repositoryes.songRebository songRebository, com.example.songr.Repositoryes.athuRebository athuRebository, com.example.songr.Repositoryes.postRebository postRebository) {
        AlbumRepository = albumRepository; //
        this.songRebository = songRebository;
        this.athuRebository = athuRebository;
        this.postRebository = postRebository;
    }
    // when you use findBy in controller you must use constracter insted of using @Atoword


    @ResponseStatus(value = HttpStatus.OK) // to cheake the resopnse
    @GetMapping("/hello") // to create  route
  String hello(){
     System.out.println("hello world !");
     return "hello";

 }

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
    public RedirectView added(@ModelAttribute Album newAlbum){ // TO ADD FOR DB @ModelAttribute to get data from front end
        AlbumRepository.save(newAlbum); // to save the  ModelAttribute in db ysing repo
        return new RedirectView("/dbalbum"); // to redirect at rout /dbalbum
    }



    @GetMapping("/dbalbum")
    public  String dbAlbumes(Model albu){    // TO GET ALL ALBUM FROM DB  by using modle => from db to front end
albu.addAttribute("allAlbums",AlbumRepository.findAll());
        return "dbalbum";
    }

///////////////SONG DB//////////////

    @GetMapping("/addsongform/{album_id}")         // ADING FORM
    public  String addSongForm(@PathVariable Long album_id, Model albu){ // we create @PathVariable to get pathVariable from url
        Album Abbu = AlbumRepository.findById(album_id).orElseThrow();
        albu.addAttribute("album_id", Abbu.getId()); //to create atebute in modle to pass data to front end called  album_id
        return "addSong";

    }





    @PostMapping("/addsong/{id}")
    public RedirectView addedSong(@ModelAttribute  Song newSong, @PathVariable Long id){   // TO ADD FOR DB BY ALBUME ID

        Album Abbu = AlbumRepository.findById(id).orElseThrow(); // to find the spesific album by id using repository
        newSong.setAlbum(Abbu); //to set this song with specific album because we have relation many song to one album

        songRebository.save(newSong); //add song to repo
        return new RedirectView("/dbalbum");

    }



    @GetMapping("/dbalbumsongs/{id}")   // TO GET ALBUME FROM DBS
    public  String dbsongs(Model adu,@PathVariable Long id){ //to pass data to front end we use modle and to get path var we use path varuable
        System.out.println(".........................."+songRebository.findByalbum_id(id));
        adu.addAttribute("allsong",songRebository.findByalbum_id(id)); //to get list of song thant come from bd using rebo

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


    @PostMapping("/login")   // TO login

    public  RedirectView login(HttpServletRequest request, String username, String password){
        auth Input=athuRebository.findByusername(username); // to find username
        if(Input.getUsername()==null||!BCrypt.checkpw(password, Input.getPassword())){ // to check if the user name correct
            // and the password after hashing is correct for this tuser

            return new RedirectView("/signup") ;
        }
        HttpSession session = request.getSession(); // to create session
        session.setAttribute("username",username);// to set session



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

    public  RedirectView signup(  String username,  String password){ // to get This from form  from input name"username" and input name "password"
        // we dont use @ModelAttribute this anntaion just for class
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10)); // to hach and sult the pass
auth user= new auth(username,hashedPassword); // to create instane  from user

athuRebository.save(user); // to save it
        return new RedirectView("/login") ;

}
//////////////log out


    @PostMapping("/logout")
    public RedirectView logOut(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();// to destroy  session

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
