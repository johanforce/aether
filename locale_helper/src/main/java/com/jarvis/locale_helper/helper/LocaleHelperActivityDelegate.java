package com.jarvis.locale_helper.helper;

import android.app.Activity;
import android.content.Context;

import java.util.Locale;

/**
 * Created by Jarvis Nguyen on 4/8/2019.
 */

public interface LocaleHelperActivityDelegate {

    void setLocale(Activity activity, Locale newLocale);

    Context attachBaseContext(Context newBase);
}
