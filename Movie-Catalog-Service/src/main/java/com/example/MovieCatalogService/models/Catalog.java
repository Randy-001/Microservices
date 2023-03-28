package com.example.MovieCatalogService.models;

public class Catalog {

    private int movieId;
    private String moviename;
    private String movieDesc;
    private  int rating;

    public Catalog() {
    }

    public Catalog(int movieId, String moviename, String movieDesc, int rating) {
        this.movieId = movieId;
        this.moviename = moviename;
        this.movieDesc = movieDesc;
        this.rating = rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
