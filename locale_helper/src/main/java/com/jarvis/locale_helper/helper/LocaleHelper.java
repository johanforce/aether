package com.jarvis.locale_helper.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import welly.training.localize.helper.R;

/**
 * Created by Jarvis Nguyen on 4/5/2019.
 */

@SuppressWarnings("ALL")
public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    private static final String SELECTED_COUNTRY = "Locale.Helper.Selected.Country";

    private static LocaleHelper instance;
    private LocaleData localeData = new LocaleData();

    private LocaleHelper() {
    }

    public static LocaleHelper getInstance() {
        if (instance == null) {
            instance = new LocaleHelper();
        }
        return instance;
    }

    Context onAttach(Context context) {
        Locale locale = load(context);
        return setLocale(context, locale);
    }

    Context setLocale(Context context, Locale locale) {
        persist(context, locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, locale);
        } else {
            return updateResourcesLegacy(context, locale);
        }
    }

    boolean isRTL(Locale locale) {
        return Locales.RTL.contains(locale.getDisplayLanguage());
    }

    public Locale getLocale(Context context) {
        return load(context);
    }

    public String getCurrentDisplayLanguage(Context context) {
        Locale locale = LocaleHelper.getInstance().getLocale(context);
        String language = locale.getLanguage();
        if (TextUtils.equals(language, "zh")) {
            language += "_" + locale.getCountry();
        }
        return this.localeData.get(language);
    }

    public String getCurrentLanguageCode(Context context) {
        Locale locale = LocaleHelper.getInstance().getLocale(context);
        return locale.getLanguage();
    }

    public void changeLanguage(final Context context, final LocaleHelperActivityDelegate delegate, final OnLocaleChangeListener listener) {

        final List<String> languageCodes = new ArrayList<>(new LocaleData().keySet());

        final List<String> displayLanguages = new ArrayList<>(new LocaleData().values());

        Locale locale = LocaleHelper.getInstance().getLocale(context);
        String currLanguageCode = locale.getLanguage();

        if (currLanguageCode.isEmpty()) {
            currLanguageCode = Locale.getDefault().getLanguage();
        }
        if (TextUtils.equals(currLanguageCode, "zh")) {
            currLanguageCode += "_" + locale.getCountry();
        }
        if (!languageCodes.contains(currLanguageCode)) {
            currLanguageCode = "en";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.localize_change_language_title)
                .setSingleChoiceItems(displayLanguages.toArray(new String[0]), languageCodes.indexOf(currLanguageCode), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        String languageCode = languageCodes.get(i);
                        if (!TextUtils.isEmpty(languageCode) && languageCode.contains("_")) {
                            try {
                                String[] locale = languageCode.split("_");
                                delegate.setLocale((Activity) context, new Locale(locale[0], locale[1]));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            delegate.setLocale((Activity) context, new Locale(languageCode));
                        }

                        if (listener != null) {
                            listener.onLocaleChanged();
                        }
                    }
                }).show();
    }

    private Locale load(Context context) {
        SharedPreferences preferences = getPreferences(context);
        String language = preferences.getString(SELECTED_LANGUAGE, Locale.getDefault().getLanguage());
        String country = preferences.getString(SELECTED_COUNTRY, Locale.getDefault().getCountry());
        return new Locale(language, country);
    }

    private SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(LocaleHelper.class.getName(), Context.MODE_PRIVATE);
    }

    private void persist(Context context, Locale locale) {
        if (locale == null) {
            return;
        }

        getPreferences(context).edit().putString(SELECTED_LANGUAGE, locale.getLanguage()).putString(SELECTED_COUNTRY, locale.getCountry()).apply();
    }

    private Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }

    private Context updateResourcesLegacy(Context context, Locale locale) {
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();

        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }

    public interface OnLocaleChangeListener {

        void onLocaleChanged();
    }
}
