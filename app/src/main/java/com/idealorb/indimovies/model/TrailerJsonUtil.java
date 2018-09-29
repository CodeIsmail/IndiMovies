
package com.idealorb.indimovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerJsonUtil {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("movieReleaseDatesJSONUtils")
    @Expose
    private List<Trailer> trailers = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TrailerJsonUtil() {
    }

    /**
     * 
     * @param id
     * @param trailers
     */
    public TrailerJsonUtil(Integer id, List<Trailer> trailers) {
        super();
        this.id = id;
        this.trailers = trailers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

}
