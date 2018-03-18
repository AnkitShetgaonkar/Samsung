package com.droid.ankit.samsung.genre_utils;

import com.google.gson.Gson;

/**
 * Created by Ankit on 3/17/2018.
 */

public class GenresUtils {
    private String  genreString = "{ \"genres\": [ { \"id\": 28, \"name\": \"Action\" }, { \"id\": 12, \"name\": \"Adventure\" }, { \"id\": 16, \"name\": \"Animation\" }, { \"id\": 35, \"name\": \"Comedy\" }, { \"id\": 80, \"name\": \"Crime\" }, { \"id\": 99, \"name\": \"Documentary\" }, { \"id\": 18, \"name\": \"Drama\" }, { \"id\": 10751, \"name\": \"Family\" }, { \"id\": 14, \"name\": \"Fantasy\" }, { \"id\": 36, \"name\": \"History\" }, { \"id\": 27, \"name\": \"Horror\" }, { \"id\": 10402, \"name\": \"Music\" }, { \"id\": 9648, \"name\": \"Mystery\" }, { \"id\": 10749, \"name\": \"Romance\" }, { \"id\": 878, \"name\": \"Science Fiction\" }, { \"id\": 10770, \"name\": \"TV Movie\" }, { \"id\": 53, \"name\": \"Thriller\" }, { \"id\": 10752, \"name\": \"War\" }, { \"id\": 37, \"name\": \"Western\" } ] }";
    private static GenresUtils mGenresUtils;
    private Genres mGenres;

    public static GenresUtils getInstance(){
        if(mGenresUtils == null){
            mGenresUtils = new GenresUtils();
        }
        return mGenresUtils;
    }

    private GenresUtils(){
        Gson gson = new Gson();
        mGenres = gson.fromJson(genreString,Genres.class);
    }

    //can do better with hashmap over here, if we save id and name map, but the api was list
    public String getGenre(long id){
        for (Genre genre :
                mGenres.getGenres()) {
            if(genre.getId() == id){
                return genre.getName();
            }
        }
        return "Unknown Genre";
    }
}
