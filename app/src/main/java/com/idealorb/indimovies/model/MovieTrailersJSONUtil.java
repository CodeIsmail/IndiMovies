
package com.idealorb.indimovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailersJSONUtil {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("movieReleaseDatesJSONUtils")
    @Expose
    private List<MovieTrailer> movieTrailers = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MovieTrailersJSONUtil() {
    }

    /**
     * 
     * @param id
     * @param movieTrailers
     */
    public MovieTrailersJSONUtil(Integer id, List<MovieTrailer> movieTrailers) {
        super();
        this.id = id;
        this.movieTrailers = movieTrailers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieTrailer> getMovieTrailers() {
        return movieTrailers;
    }

    public void setMovieTrailers(List<MovieTrailer> movieTrailers) {
        this.movieTrailers = movieTrailers;
    }

}
