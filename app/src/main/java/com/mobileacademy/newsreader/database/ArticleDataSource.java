package com.mobileacademy.newsreader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mobileacademy.newsreader.models.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDataSource {

    private static final String TAG = "ArticleDataSource";

    private SQLiteDatabase database;
    private MyDatabaseHelper databaseHelper;

    public ArticleDataSource(Context context) {
        databaseHelper = new MyDatabaseHelper(context);
    }

    public void openWritebleDb() {
        database = databaseHelper.getWritableDatabase();
    }

    public void closeDb() {
        databaseHelper.close();
    }

    public void insertArticle(Article article) {
        ContentValues values = new ContentValues();

        values.put(MyDatabaseHelper.ARTICLE_COLUMN_ID, article.getId());
        values.put(MyDatabaseHelper.ARTICLE_COLUMN_TITLE, article.getTitle());
        values.put(MyDatabaseHelper.ARTICLE_COLUMN_URL, article.getUrl());

        long rowId = database.insertWithOnConflict(MyDatabaseHelper.ARTICLE_TABLE_NAME, null,
                values, SQLiteDatabase.CONFLICT_REPLACE);
        Log.d(TAG, "just inserted rowId = " + rowId);

    }

    public ArrayList<Article> getAllArticles() {
        Cursor cursor = database.query(MyDatabaseHelper.ARTICLE_TABLE_NAME,
                MyDatabaseHelper.ARTICLE_COLUMNS, null,
                null, null, null, null);

        ArrayList<Article> result = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Article article = new Article();
                int id = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.ARTICLE_COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.ARTICLE_COLUMN_TITLE));
                String url = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.ARTICLE_COLUMN_URL));

                article.setId(id);
                article.setTitle(title);
                article.setUrl(url);

                result.add(article);

                cursor.moveToNext();
            }

            cursor.close();
        }

        Log.d(TAG, "results size=" + result.size());

        return result;
    }

}
