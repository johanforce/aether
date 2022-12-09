package com.jarvis.weatherj.presentation.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FireBaseLogEvents {
    private static FireBaseLogEvents instance;
    private final FirebaseAnalytics mFirebaseAnalytics;

    private FireBaseLogEvents(Context context) {
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public static FireBaseLogEvents getInstance() {
        if (instance == null) {
            throw new NullPointerException("Please init AppLogEvent on App Application!");
        }
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new FireBaseLogEvents(context);
        }
    }

    public void log(@NonNull @Size(min = 1L, max = 32L) String eventName, Bundle eventBundle) {
        if (eventBundle != null) {
            this.mFirebaseAnalytics.logEvent(eventName, eventBundle);
            return;
        }
        this.mFirebaseAnalytics.logEvent(eventName, null);
    }

    public void log(@NonNull @Size(min = 1L, max = 32L) String eventName, @NonNull String paramKey, @NonNull String paramValue) {
        Bundle bundle = new Bundle();
        bundle.putString(paramKey, paramValue);
        this.mFirebaseAnalytics.logEvent(eventName, bundle);
    }

    public void log(@NonNull @Size(min = 1L, max = 32L) String eventName) {
        this.mFirebaseAnalytics.logEvent(eventName, null);
    }

    public void log(@NonNull @Size(min = 1L, max = 32L) String eventName, @NonNull String message) {
        this.mFirebaseAnalytics.logEvent(eventName, null);
    }

    public void setCurrentScreen(Activity activity, String screenName) {
        this.mFirebaseAnalytics.setCurrentScreen(activity, screenName, null);
    }

}
