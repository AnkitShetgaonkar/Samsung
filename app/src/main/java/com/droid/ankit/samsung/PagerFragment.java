package com.droid.ankit.samsung;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droid.ankit.samsung.data.MovieList;


/**
 * Created by Ankit on 3/17/2018.
 */

public class PagerFragment extends Fragment {
    private TextView mMovieName;
    private TextView mPopularity;

    private static final String MOVIE_FRAG_TYPE = "pager_frag";
    private static final String MOVIE_DATA_KEY = "user_data_key";
    public static final String PAGER_NOW_SHOWING = "pager_now_showing";
    public static final String PAGER_UPCOMING = "pager_upcoming";
    private ViewGroup mRootView;
    private MovieList mMovieList;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CustomAdapter mAdapter;
    private int mVisibleItemCount;
    private int mTotalItemCount;
    private int mPastVisiblesItems;
    private String mType;
    private Callback mCallback;

    public static PagerFragment newInstance(MovieList movieList, String type) {
        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(MOVIE_DATA_KEY, movieList);
        args.putSerializable(MOVIE_FRAG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (MainActivity) context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(
                R.layout.pager_item, container, false);

        mMovieList = (MovieList) getArguments().getSerializable(MOVIE_DATA_KEY);
        mType = getArguments().getString(MOVIE_FRAG_TYPE);
        initViews();
        return mRootView;
    }

    private void initViews() {
        mRecyclerView = mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(),
                mLayoutManager.getOrientation()
        );
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CustomAdapter(mMovieList.getMovieList());
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    mVisibleItemCount = mLayoutManager.getChildCount();
                mTotalItemCount = mLayoutManager.getItemCount();
                mPastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                if ((mVisibleItemCount + mPastVisiblesItems) >= mTotalItemCount) {
                    mCallback.loadMore(mType);
                }
            }
        });
    }

    public void notifyDataChange() {
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    interface Callback {
        void loadMore(String pagerType);
    }
}
