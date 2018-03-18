
package com.droid.ankit.samsung.genre_utils;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Genres {

    @SerializedName("genres")
    private List<Genre> mGenres;

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

}
