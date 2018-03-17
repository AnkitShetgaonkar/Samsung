package com.droid.ankit.samsung;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Ankit on 3/17/2018.
 */

public class Network {


    private static final String api_key = "da307ea1e5b21571c5a908f8681e3edb";
    private ResponsePojo upcoming;


    public ResponsePojo getNowPlaying(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, "{}");
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/" +
                        "now_playing?page=1&language=en-US&api_key=da307ea1e5b21571c5a908f8681e3edb")
                .get()
                .build();

        ResponsePojo responsePojo= null;
        try {
            Response responses = client.newCall(request).execute();
            String jsonData = responses.body().string();
            Gson gson = new Gson();
            responsePojo = gson.fromJson(jsonData,ResponsePojo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responsePojo;
    }

    public ResponsePojo getUpcoming() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, "{}");
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/upcoming?page=1&language=en-US&api_key=da307ea1e5b21571c5a908f8681e3edb")
                .get()
                .build();

        ResponsePojo responsePojo= null;
        try {
            Response responses = client.newCall(request).execute();
            String jsonData = responses.body().string();
            Gson gson = new Gson();
            responsePojo = gson.fromJson(jsonData,ResponsePojo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responsePojo;
    }
}
