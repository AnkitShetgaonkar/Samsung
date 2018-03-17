package com.droid.ankit.samsung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankit on 3/17/2018.
 */

public class MovieList implements Serializable{
    ArrayList<MovieData> movieList;

    public MovieList(ArrayList<MovieData> movieList) {
        this.movieList = movieList;
    }

    public ArrayList<MovieData> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<MovieData> movieList) {
        this.movieList = movieList;
    }
}
