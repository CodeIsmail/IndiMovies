
package com.idealorb.indimovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReleaseDates implements Serializable {

    @SerializedName("results")
    @Expose
    private List<MovieReleaseDatesJSONUtil> movieReleaseDatesJSONUtils = null;
    private final static long serialVersionUID = 4944632574702177838L;

    /**
     * No args constructor for use in serialization
     */
    public ReleaseDates() {
    }

    /**
     * @param movieReleaseDatesJSONUtils
     */
    public ReleaseDates(List<MovieReleaseDatesJSONUtil> movieReleaseDatesJSONUtils) {
        super();
        this.movieReleaseDatesJSONUtils = movieReleaseDatesJSONUtils;
    }

    public List<MovieReleaseDatesJSONUtil> getMovieReleaseDatesJSONUtils() {
        return movieReleaseDatesJSONUtils;
    }

    public void setMovieReleaseDatesJSONUtils(List<MovieReleaseDatesJSONUtil> movieReleaseDatesJSONUtils) {
        this.movieReleaseDatesJSONUtils = movieReleaseDatesJSONUtils;
    }

}
