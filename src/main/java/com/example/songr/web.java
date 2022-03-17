package com.example.songr;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.AttributedString;

@Controller// to create mvc
public class web {
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
}
