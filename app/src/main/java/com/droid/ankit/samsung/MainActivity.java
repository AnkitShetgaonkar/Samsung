package com.droid.ankit.samsung;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.droid.ankit.samsung.data.MovieData;
import com.droid.ankit.samsung.data.MovieList;
import com.droid.ankit.samsung.genre_utils.GenresUtils;
import com.droid.ankit.samsung.network.Network;
import com.droid.ankit.samsung.network.ResponsePojo;
import com.droid.ankit.samsung.network.Result;
import com.droid.ankit.samsung.presenter.MainPresenter;
import com.droid.ankit.samsung.views.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, PagerFragment.Callback {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private List<PagerFragment> mPagerFragments;
    private MainPresenter mPresenter;
    private ProgressBar mProgressBar;
    private View mMovieGroup;
    private MovieList mShowingMovieList;
    private MovieList mUpcomingMovieList;
    private PagerFragment mNowShowingFragment;
    private PagerFragment mUpcomingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = findViewById(R.id.moviePager);
        mPagerFragments = new ArrayList<>();
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mPager);
        mProgressBar = findViewById(R.id.progressBar);
        mMovieGroup = findViewById(R.id.movieGroup);
        mPresenter = new MainPresenter(this);

        mShowingMovieList = new MovieList(new ArrayList<MovieData>());
        mUpcomingMovieList = new MovieList(new ArrayList<MovieData>());
        mNowShowingFragment = PagerFragment.newInstance(mShowingMovieList,PagerFragment.PAGER_NOW_SHOWING);
        mUpcomingFragment = PagerFragment.newInstance(mUpcomingMovieList,PagerFragment.PAGER_UPCOMING);
        mPagerFragments.add(mNowShowingFragment);
        mPagerFragments.add(mUpcomingFragment);
        mPagerAdapter = new ScreenSlidePagerAdapter(mPagerFragments, getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPresenter.init();
    }


    @Override
    public void showLoader() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
                mMovieGroup.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void dismissLoader() {
        //dismiss loader
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
                mMovieGroup.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void showMovies(MovieList showingMovieList, MovieList upcomingMovieList) {
        mShowingMovieList.getMovieList().addAll(showingMovieList.getMovieList());
        mUpcomingMovieList.getMovieList().addAll(upcomingMovieList.getMovieList());
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpcomingMovies(MovieList movieList) {
        mUpcomingMovieList.getMovieList().addAll(movieList.getMovieList());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUpcomingFragment.notifyDataChange();
            }
        });
    }

    @Override
    public void showNowShowingMovies(MovieList movieList) {
        mShowingMovieList.getMovieList().addAll(movieList.getMovieList());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mNowShowingFragment.notifyDataChange();
            }
        });
    }

    @Override
    public void loadMore(String pagerType) {
        if(pagerType.equals(PagerFragment.PAGER_NOW_SHOWING)){
            mPresenter.getNowShowing();
        }else{
            mPresenter.getUpcoming();
        }
    }
}
