package com.example.MovieRatingService.controllers;


import com.example.MovieRatingService.models.AllRatings;
import com.example.MovieRatingService.models.RatingResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @GetMapping("/{id}")
    public AllRatings getAllRatings(@RequestParam List<Integer> list, @PathVariable int id)
    {

        list.forEach(System.out::println);
        List<RatingResponse> ratingResponseList = new ArrayList<>();
        ratingResponseList.add(new RatingResponse(100,5));
        ratingResponseList.add(new RatingResponse(101,1));
        AllRatings allRatings = new AllRatings();
        allRatings.setRatingResponseList(ratingResponseList);
        return allRatings;

    }
}
