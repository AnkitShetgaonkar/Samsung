package com.droid.ankit.samsung.network;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Ankit on 3/17/2018.
 */

public class Network {


    private static Network mNetwork;

    public static Network getInstance() {
        if (mNetwork == null) {
            mNetwork = new Network();
        }
        return mNetwork;
    }

    private Network() {

    }

    public void getNowPlaying(long pageNumber, final Callback<ResponsePojo> callback) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, "{}");
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/" +
                        "now_playing?page="+pageNumber+"&language=en-US&api_key=da307ea1e5b21571c5a908f8681e3edb")
                .get()
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.failure("Failed to fetch nowplaying movies");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponsePojo responsePojo;
                String jsonData = response.body().string();
                Gson gson = new Gson();
                responsePojo = gson.fromJson(jsonData, ResponsePojo.class);
                callback.success(responsePojo);
            }
        });

    }

    public void getUpcoming(long pageNumber, final Callback<ResponsePojo> callback) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, "{}");
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/" +
                        "upcoming?page="+pageNumber+"&language=en-US&api_key=da307ea1e5b21571c5a908f8681e3edb")
                .get()
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.failure("Failed to fetch upcoming movies");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponsePojo responsePojo;
                String jsonData = response.body().string();
                Gson gson = new Gson();
                responsePojo = gson.fromJson(jsonData, ResponsePojo.class);
                callback.success(responsePojo);
            }
        });
    }

    /**
     * network call back
     *
     * @param <T>
     */
    public interface Callback<T> {
        void success(T t);
        void failure(String message);
    }
}
