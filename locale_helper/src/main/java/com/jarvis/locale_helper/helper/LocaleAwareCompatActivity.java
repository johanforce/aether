package com.jarvis.locale_helper.helper;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Jarvis Nguyen on 4/8/2019.
 */

public class LocaleAwareCompatActivity extends AppCompatActivity {

    public LocaleHelperActivityDelegateImpl localeDelegate = new LocaleHelperActivityDelegateImpl();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(localeDelegate.attachBaseContext(newBase));
    }
}
