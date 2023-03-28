package com.example.MovieCatalogService.controller;

import com.example.MovieCatalogService.models.AllRatings;
import com.example.MovieCatalogService.models.Catalog;
import com.example.MovieCatalogService.models.Movie;
import com.example.MovieCatalogService.models.RatingResponse;
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

    @Autowired
    public CatalogController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    @GetMapping
    public List<Catalog> getCatalogs(@RequestParam String userId)
    {
        List<Integer> lt = new ArrayList<>();
        lt.add(1);
        lt.add(2);
        lt.add(3);

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        WebClient webClient = webClientBuilder
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .build();
        AllRatings allRatings = webClient.get()
                .uri(uriBuilder -> uriBuilder
                             .path("/rating/12")
                            .queryParam("list",lt)
                            .build()
                )
                .retrieve()
                .bodyToMono(AllRatings.class)
                .block();
        List<Catalog> catalogList = allRatings.getRatingResponseList().stream().map((x)->{
            Movie movie = webClientBuilder.build().get()
                    .uri("http://movie-info-service/info/"+x.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new Catalog(x.getMovieId(),movie.getOriginal_title(),movie.getOverview(),x.getRating());
        }).collect(Collectors.toList());
        System.out.println("Entered the Next rating service...");

        return catalogList;
    }
}
