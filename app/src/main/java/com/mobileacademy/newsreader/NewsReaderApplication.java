package com.mobileacademy.newsreader;

import android.app.Application;
import android.util.Log;

import com.mobileacademy.newsreader.database.ArticleDataSource;
import com.mobileacademy.newsreader.services.MyWorkeer;

public class NewsReaderApplication extends Application {

    private static NewsReaderApplication INSTANCE;

    private static final String TAG = "NewsReaderApplication";
    private static ArticleDataSource dataSource;

    public static NewsReaderApplication getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsReaderApplication();
        }

        return INSTANCE;
    }

    public static ArticleDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "application onCreate");

        // here is database init
        dataSource = new ArticleDataSource(this);
        dataSource.openWritebleDb(); //database create

        MyWorkeer.executeWork();
    }
}
