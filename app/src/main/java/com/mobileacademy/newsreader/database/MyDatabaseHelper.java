package com.mobileacademy.newsreader.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "MyDatabaseHelper";

    private static final String DATABASE_NAME = "newsreader.db";
    private static final Integer DATABASE_VERSION = 1;

    // define database scheme = tables
    public static final String ARTICLE_TABLE_NAME = "article";
    public static final String ARTICLE_COLUMN_ID = "id";
    public static final String ARTICLE_COLUMN_TITLE = "title";
    public static final String ARTICLE_COLUMN_URL = "url";

    public static final String[] ARTICLE_COLUMNS = {ARTICLE_COLUMN_ID, ARTICLE_COLUMN_TITLE, ARTICLE_COLUMN_URL};

    // create table sql statement
    private static final String CREATE_ARTICLE_STATEMENT = "create table " + ARTICLE_TABLE_NAME + "("
            + ARTICLE_COLUMN_ID + " int primary key not null, "
            + ARTICLE_COLUMN_TITLE + " text, "
            + ARTICLE_COLUMN_URL + " text)";

    // take cares of database creation
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "create newsreader database");
    }

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "create article table");
        db.execSQL(CREATE_ARTICLE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade");
    }
}
