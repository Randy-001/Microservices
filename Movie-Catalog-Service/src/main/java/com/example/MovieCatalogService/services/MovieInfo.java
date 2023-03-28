package com.example.MovieCatalogService.services;

import com.example.MovieCatalogService.models.Movie;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfo {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @HystrixCommand(fallbackMethod = "movieInfoFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty( name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty( name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty( name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            })
    public Movie getMovie(int movieId)
    {
        return webClientBuilder.build().get()
                .uri("http://movie-info-service/info/"+movieId)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }
    public Movie movieInfoFallback(int movieId)
    {
        return new Movie();
    }


}
