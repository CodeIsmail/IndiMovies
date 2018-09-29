
package com.idealorb.indimovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReleaseDates implements Serializable {

    @SerializedName("results")
    @Expose
    private List<ReleaseDatesJsonUtil> releaseDatesJsonUtils = null;
    private final static long serialVersionUID = 4944632574702177838L;

    /**
     * No args constructor for use in serialization
     */
    public ReleaseDates() {
    }

    /**
     * @param releaseDatesJsonUtils
     */
    public ReleaseDates(List<ReleaseDatesJsonUtil> releaseDatesJsonUtils) {
        super();
        this.releaseDatesJsonUtils = releaseDatesJsonUtils;
    }

    public List<ReleaseDatesJsonUtil> getReleaseDatesJsonUtils() {
        return releaseDatesJsonUtils;
    }

    public void setReleaseDatesJsonUtils(List<ReleaseDatesJsonUtil> releaseDatesJsonUtils) {
        this.releaseDatesJsonUtils = releaseDatesJsonUtils;
    }

}
