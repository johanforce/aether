package com.jarvis.locale_helper.helper;

import android.app.Activity;
import android.content.Context;

import java.util.Locale;

/**
 * Created by Jarvis Nguyen on 4/8/2019.
 */

public class LocaleHelperActivityDelegateImpl implements LocaleHelperActivityDelegate {

    @Override
    public void setLocale(Activity activity, Locale newLocale) {
        LocaleHelper.getInstance().setLocale(activity, newLocale);
        activity.recreate();
    }

    @Override
    public Context attachBaseContext(Context newBase) {
        return LocaleHelper.getInstance().onAttach(newBase);
    }

}
