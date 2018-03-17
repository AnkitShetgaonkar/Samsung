package com.droid.ankit.samsung;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private List<PagerFragment> mPagerFragments;
    private ResponsePojo mResponsePojoNowMovies;
    private ResponsePojo mResponsePojoUpComingMovies;
    private MovieList mNowPlayingMovieList;
    private MovieList mUpcomingMovieList;
    private Object upcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = findViewById(R.id.moviePager);
        mPagerFragments = new ArrayList<>();
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mPager);
        new NowPlayingNetworkCall().execute();
        new UpComingNetworkCall().execute();
    }

    private void setNowPlaying() {
        ArrayList<MovieData> movieList = new ArrayList<>();
        for (Result result :
                mResponsePojoNowMovies.getResults()) {
            MovieData movieData = new MovieData(result.getTitle(),""+result.getPopularity());
            movieList.add(movieData);
        }
        mNowPlayingMovieList = new MovieList(movieList);
        mPagerFragments.add(PagerFragment.newInstance(mNowPlayingMovieList));

    }

    public void setUpcoming() {
        ArrayList<MovieData> movieList = new ArrayList<>();
        for (Result result :
                mResponsePojoUpComingMovies.getResults()) {
            MovieData movieData = new MovieData(result.getTitle(),""+result.getPopularity());
            movieList.add(movieData);
        }
        mUpcomingMovieList = new MovieList(movieList);
        mPagerFragments.add(PagerFragment.newInstance(mUpcomingMovieList));
        mPagerAdapter = new ScreenSlidePagerAdapter(mPagerFragments, getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private class NowPlayingNetworkCall extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // this might take a while ...
            Network network = new Network();
            mResponsePojoNowMovies = network.getNowPlaying();
            return null;
        }

        @Override
        protected void onPostExecute(Void param) {

            runOnUiThread(new Runnable() {
                public void run() {
                    setNowPlaying();
                }
            });

        }
    }

    private class UpComingNetworkCall extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // this might take a while ...
            Network network = new Network();
            mResponsePojoUpComingMovies = network.getUpcoming();
            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            runOnUiThread(new Runnable() {
                public void run() {
                    setUpcoming();
                }
            });
        }
    }
}
