package com.droid.ankit.samsung;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ankit on 3/17/2018.
 */

public class PagerFragment extends Fragment {
    private TextView mMovieName;
    private TextView mPopularity;

    private static final String MOVIE_DATA_KEY = "user_data_key";
    private ViewGroup mRootView;
    private MovieList mMovieList;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CustomAdapter mAdapter;

    public static PagerFragment newInstance(MovieList movieList) {
        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(MOVIE_DATA_KEY, movieList);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(
                R.layout.pager_item, container, false);

        mMovieList = (MovieList) getArguments().getSerializable(MOVIE_DATA_KEY);
        initViews();
        return mRootView;
    }

    private void initViews() {
        mRecyclerView =  mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CustomAdapter(mMovieList.getMovieList());
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
    }
}
