package com.droid.ankit.samsung.presenter;
import com.droid.ankit.samsung.data.MovieData;
import com.droid.ankit.samsung.data.MovieList;
import com.droid.ankit.samsung.genre_utils.GenresUtils;
import com.droid.ankit.samsung.network.Network;
import com.droid.ankit.samsung.network.ResponsePojo;
import com.droid.ankit.samsung.network.Result;
import com.droid.ankit.samsung.views.MainView;

import java.util.ArrayList;

/**
 * Created by Ankit on 3/17/2018.
 */

public class MainPresenter {

    private MainView mView;
    private long mShowingTotalPages = 1;
    private long mUpcomingTotalPages = 1;
    private long mShowingCurrentPage = 0;
    private long mUpcomingCurrentPage = 0;

    public MainPresenter(MainView view){
        this.mView = view;
    }

    public void init(){
        mView.showLoader();
        getNowShowing();
        getUpcoming();
        mView.dismissLoader();
    }


    public void getNowShowing(){
        if(mShowingCurrentPage>=mShowingTotalPages){
            return;
        }
        Network.getInstance().getNowPlaying(++mShowingCurrentPage,new Network.Callback<ResponsePojo>() {

            @Override
            public void success(ResponsePojo responsePojo) {
                mShowingTotalPages = responsePojo.getTotalPages();
                mShowingCurrentPage = responsePojo.getPage();
                mView.showNowShowingMovies(setData(responsePojo));
            }

            @Override
            public void failure(String message) {
                mView.error(message);
            }
        });
    }


    public void getUpcoming(){
        if(mUpcomingCurrentPage>=mUpcomingTotalPages){
            return;
        }
        Network.getInstance().getUpcoming(++mUpcomingCurrentPage,new Network.Callback<ResponsePojo>() {
            @Override
            public void success(ResponsePojo responsePojo) {
                mUpcomingTotalPages = responsePojo.getTotalPages();
                mUpcomingCurrentPage = responsePojo.getPage();
                mView.showUpcomingMovies(setData(responsePojo));
            }

            @Override
            public void failure(String message) {
                mView.error(message);
            }
        });
    }


    private MovieList setData(ResponsePojo responsePojo) {
        ArrayList<MovieData> movieList = new ArrayList<>();
        for (Result result :
                responsePojo.getResults()) {
            String genre = result.getGenreIds().size()>0?GenresUtils.getInstance().getGenre(result.getGenreIds().get(0)):"Unknown genre";
            MovieData movieData = new MovieData(result.getTitle(),""+result.getPopularity(),
                    "https://image.tmdb.org/t/p/w500"+
                            result.getBackdropPath(),
                    genre);
            movieList.add(movieData);
        }
        return new MovieList(movieList);
    }
}
