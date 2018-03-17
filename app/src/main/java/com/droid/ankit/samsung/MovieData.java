package com.droid.ankit.samsung;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ankit on 3/17/2018.
 */

public class MovieData implements Serializable{
    private String movieName;
    private String popularityName;
    private String genre;
    private String imageUrl;


    public MovieData(String movieName, String popularityName,String genre, String imageUrl) {
        this.movieName = movieName;
        this.popularityName = popularityName;
        this.genre = genre;
        this.
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getPopularityName() {
        return popularityName;
    }

    public void setPopularityName(String popularityName) {
        this.popularityName = popularityName;
    }
}
