package com.example.ygo_support_duel;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import java.util.Locale;

public class LocaleHelper {

    public static Context setLocale(Context context, String languageCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, languageCode);
        }
        return updateResourcesLegacy(context, languageCode);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);
        return context.createConfigurationContext(config);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        config.setLayoutDirection(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
        return context;
    }

    public static String getSavedLanguage(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return prefs.getString("language", "es"); // español por defecto
    }

    public static void saveLanguage(Context context, String langCode) {
        SharedPreferences prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        prefs.edit().putString("language", langCode).apply();
    }


}
