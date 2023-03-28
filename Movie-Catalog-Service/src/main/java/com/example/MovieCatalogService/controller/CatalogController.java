package com.example.MovieCatalogService.controller;

import com.example.MovieCatalogService.models.AllRatings;
import com.example.MovieCatalogService.models.Catalog;
import com.example.MovieCatalogService.models.Movie;
import com.example.MovieCatalogService.models.RatingResponse;
import com.example.MovieCatalogService.services.MovieInfo;
import com.example.MovieCatalogService.services.RatingService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private static final String BASE_URL = "http://movie-rating-service" ;
    private final WebClient.Builder webClientBuilder;
    private final MovieInfo movieInfo;
    private final RatingService ratingService;
    @Autowired
    public CatalogController(WebClient.Builder webClientBuilder, MovieInfo movieInfo, RatingService ratingService) {
        this.webClientBuilder = webClientBuilder;
        this.movieInfo = movieInfo;
        this.ratingService = ratingService;
    }




    @GetMapping
    public List<Catalog> getCatalogs(@RequestParam String userId)
    {

        List<Catalog> catalogList = this.ratingService.getAllRatings().getRatingResponseList().stream().map((x)->{
            System.out.println(x.getMovieId());
            Movie movie =this.movieInfo.getMovie(x.getMovieId());
            return new Catalog(x.getMovieId(),movie.getOriginal_title(),movie.getOverview(),x.getRating());
        }).collect(Collectors.toList());
        System.out.println("Entered the Next rating service...");

        return catalogList;
    }
}
