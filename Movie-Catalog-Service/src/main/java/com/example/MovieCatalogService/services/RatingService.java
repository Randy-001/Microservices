package com.example.MovieCatalogService.services;

import com.example.MovieCatalogService.models.AllRatings;
import com.example.MovieCatalogService.models.RatingResponse;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {
    private static final String BASE_URL = "http://movie-rating-service" ;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @HystrixCommand(fallbackMethod = "ratingFallback",
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty( name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty( name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty( name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    public AllRatings getAllRatings()
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
        allRatings.getRatingResponseList().forEach(System.out::println);
        return allRatings;

    }
    public AllRatings ratingFallback()
    {
        List<RatingResponse> ratingResponseList = new ArrayList<>();
        ratingResponseList.add(new RatingResponse());
        return new AllRatings(ratingResponseList);
    }


}
