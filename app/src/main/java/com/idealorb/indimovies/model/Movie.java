package com.idealorb.indimovies.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Entity(tableName = "favorite_movie")
@Parcel
public class Movie {

    @Ignore
    @SerializedName("vote_count")
    @Expose
    Integer voteCount;
    @PrimaryKey
    @SerializedName("id")
    @Expose
    Integer id;
    @Ignore
    @SerializedName("video")
    @Expose
    Boolean video;
    @SerializedName("vote_average")
    @Expose
    Float voteAverage;
    @Ignore
    @SerializedName("title")
    @Expose
    String title;
    @Ignore
    @SerializedName("popularity")
    @Expose
    Float popularity;
    @SerializedName("poster_path")
    @Expose
    String posterPath;
    @Ignore
    @SerializedName("original_language")
    @Expose
    String originalLanguage;
    @SerializedName("original_title")
    @Expose
    String originalTitle;
    @Ignore
    @SerializedName("genre_ids")
    @Expose
    List<Integer> genreIds = null;
    @Ignore
    @SerializedName("backdrop_path")
    @Expose
    String backdropPath;
    @Ignore
    @SerializedName("adult")
    @Expose
    Boolean adult;
    @SerializedName("overview")
    @Expose
    String overview;
    @SerializedName("release_date")
    @Expose
    String releaseDate;
    boolean isFavorite;
    final static long serialVersionUID = -6936711426332184557L;

    /**
     * No args constructor for use in serialization
     */
    @Ignore
    public Movie() {
    }

    /**
     * @param genreIds
     * @param id
     * @param title
     * @param releaseDate
     * @param overview
     * @param posterPath
     * @param originalTitle
     * @param voteAverage
     * @param originalLanguage
     * @param adult
     * @param backdropPath
     * @param voteCount
     * @param video
     * @param popularity
     */
    @Ignore
    public Movie(Integer voteCount, Integer id, Boolean video, Float voteAverage, String title,
                 Float popularity, String posterPath, String originalLanguage, String originalTitle,
                 List<Integer> genreIds, String backdropPath, Boolean adult, String overview,
                 String releaseDate) {
        super();
        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Movie(Integer id, String originalTitle, Float voteAverage, String posterPath,
                 String overview, String releaseDate, boolean isFavorite) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.isFavorite = isFavorite;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
