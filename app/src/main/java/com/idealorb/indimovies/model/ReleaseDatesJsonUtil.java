
package com.idealorb.indimovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReleaseDatesJsonUtil implements Serializable {

    @SerializedName("iso_3166_1")
    @Expose
    private String iso31661;
    @SerializedName("release_dates")
    @Expose
    private List<ReleaseDate> releaseDates = null;
    private final static long serialVersionUID = -2418824438472762430L;

    /**
     * No args constructor for use in serialization
     */
    public ReleaseDatesJsonUtil() {
    }

    /**
     * @param iso31661
     * @param releaseDates
     */
    public ReleaseDatesJsonUtil(String iso31661, List<ReleaseDate> releaseDates) {
        super();
        this.iso31661 = iso31661;
        this.releaseDates = releaseDates;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public List<ReleaseDate> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(List<ReleaseDate> releaseDates) {
        this.releaseDates = releaseDates;
    }

}
