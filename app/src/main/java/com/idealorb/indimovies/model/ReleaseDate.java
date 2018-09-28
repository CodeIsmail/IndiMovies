
package com.idealorb.indimovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReleaseDate implements Serializable {

    @SerializedName("certification")
    @Expose
    private String certification;
    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("type")
    @Expose
    private Integer type;
    private final static long serialVersionUID = 4257463386377447513L;

    /**
     * No args constructor for use in serialization
     */
    public ReleaseDate() {
    }

    /**
     * @param iso6391
     * @param certification
     * @param releaseDate
     * @param type
     * @param note
     */
    public ReleaseDate(String certification, String iso6391, String note, String releaseDate, Integer type) {
        super();
        this.certification = certification;
        this.iso6391 = iso6391;
        this.note = note;
        this.releaseDate = releaseDate;
        this.type = type;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
