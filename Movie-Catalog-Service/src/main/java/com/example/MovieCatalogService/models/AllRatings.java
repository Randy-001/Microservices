package com.example.MovieCatalogService.models;

import java.util.List;

public class AllRatings {


    private List<RatingResponse> ratingResponseList;
    public AllRatings(List<RatingResponse> ratingResponseList) {
        this.ratingResponseList = ratingResponseList;
    }

    public AllRatings() {
    }

    public List<RatingResponse> getRatingResponseList() {
        return ratingResponseList;
    }

    public void setRatingResponseList(List<RatingResponse> ratingResponseList) {
        this.ratingResponseList = ratingResponseList;
    }


}
