package com.idealorb.indimovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieJsonUtil {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalMovies;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Movie> movieList = null;
    private final static long serialVersionUID = 960481827586600146L;

    /**
     * No args constructor for use in serialization
     */
    public MovieJsonUtil() {
    }

    /**
     * @param movieList
     * @param totalMovies
     * @param page
     * @param totalPages
     */
    public MovieJsonUtil(Integer page, Integer totalMovies, Integer totalPages, List<Movie> movieList) {
        super();
        this.page = page;
        this.totalMovies = totalMovies;
        this.totalPages = totalPages;
        this.movieList = movieList;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(Integer totalMovies) {
        this.totalMovies = totalMovies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return movieList;
    }

    public void setMovies(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
