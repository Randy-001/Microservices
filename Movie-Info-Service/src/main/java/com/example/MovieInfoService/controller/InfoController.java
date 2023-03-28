package com.example.MovieInfoService.controller;


import com.example.MovieInfoService.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/info")
public class InfoController {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public InfoController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable int movieId)
    {
       Movie movie = new Movie();
       if(movieId==100)
       {movie.setOverview("Thriller");
           movie.setTitle("Vanam");

       }
       else{
           movie.setOverview("crime");
           movie.setTitle("Thul");
       }

        return movie;
    }

}
