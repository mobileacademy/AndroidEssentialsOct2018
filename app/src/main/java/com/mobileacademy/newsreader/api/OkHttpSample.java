package com.mobileacademy.newsreader.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpSample {

    private static final String TAG = "OkHttpSample";


    public static Response getStoryIdsSyncOkhttp(String url) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            Log.e(TAG, "getArticles: ", e);
            return null;
        }
    }

}
