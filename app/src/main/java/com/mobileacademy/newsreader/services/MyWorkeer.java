package com.mobileacademy.newsreader.services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorkeer extends Worker {

    private static final String TAG = "MyWorkeer";
    public MyWorkeer(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "####### do in background work");
        return Result.SUCCESS;
    }

    public static void executeWork() {

        Constraints downConst = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

        OneTimeWorkRequest downRequest = new OneTimeWorkRequest.Builder(MyWorkeer.class).setConstraints(downConst).build();

        WorkManager.getInstance().enqueue(downRequest);

    }

    public static void executeRecurentWork() {

        PeriodicWorkRequest recurentTask = new PeriodicWorkRequest.Builder(
                MyWorkeer.class, 10, TimeUnit.HOURS, 10, TimeUnit.MINUTES).build();

        WorkManager.getInstance().enqueue(recurentTask);
    }
}
