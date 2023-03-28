package com.example.MovieCatalogService.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {

    @JsonProperty("title")
    private String original_title;
    private String overview;


    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
