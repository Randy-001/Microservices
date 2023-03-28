package com.example.MovieRatingService.models;



public class RatingResponse {

    private int movieId;
    private  int rating;

    public RatingResponse(int movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public RatingResponse() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
