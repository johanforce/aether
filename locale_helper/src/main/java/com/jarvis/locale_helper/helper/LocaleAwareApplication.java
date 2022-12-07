package com.jarvis.locale_helper.helper;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

/**
 * Created by camlh on 4/8/2019.
 */

public class LocaleAwareApplication extends Application {

    public LocaleHelperApplicationDelegate localeAppDelegate;

    public LocaleAwareApplication() {
        this.localeAppDelegate = new LocaleHelperApplicationDelegate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(this.localeAppDelegate.attachBaseContext(base));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.localeAppDelegate.onConfigurationChanged(this);
    }
}
