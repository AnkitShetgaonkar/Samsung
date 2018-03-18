package com.droid.ankit.samsung.views;

import com.droid.ankit.samsung.data.MovieList;

/**
 * Created by Ankit on 3/17/2018.
 */

public interface MainView {

    void showLoader();

    void dismissLoader();

    void showMovies(MovieList showingMovieList, MovieList upcomingMovieList);

    void error(String message);

    void showUpcomingMovies(MovieList movieList);

    void showNowShowingMovies(MovieList movieList);
}
