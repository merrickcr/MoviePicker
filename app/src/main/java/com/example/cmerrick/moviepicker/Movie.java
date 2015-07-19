package com.example.cmerrick.moviepicker;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by atv684 on 7/15/15.
 */
public class Movie implements Serializable {

    String backdropPath;

    String fullBackDropPath;

    Drawable backdrop;

    Bitmap backdropBitmap;

    String title;

    String overview;

    double voteAverage;

    double popularity;

    String releaseDate;

    public Movie(JSONObject json) {
        try {
            backdropPath = json.optString("backdrop_path");
            title = json.optString("title");
            overview = json.optString("overview");
            voteAverage = json.optDouble("vote_average");
            popularity = json.optDouble("popularity");
            releaseDate = json.optString("release_date");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Drawable getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(Drawable backdrop) {
        this.backdrop = backdrop;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Bitmap getBackdropBitmap() {
        return backdropBitmap;
    }

    public void setBackdropBitmap(Bitmap backdropBitmap) {
        this.backdropBitmap = backdropBitmap;
    }

    public String getFullBackDropPath() {
        return fullBackDropPath;
    }

    public void setFullBackDropPath(String fullBackDropPath) {
        this.fullBackDropPath = fullBackDropPath;
    }
}
