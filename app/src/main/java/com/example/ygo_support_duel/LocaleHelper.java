package com.example.ygo_support_duel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    // Llamar esto para guardar el idioma y aplicarlo
    public static void saveLanguage(Context context, String language) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(SELECTED_LANGUAGE, language).apply();
    }

    // Obtener idioma guardado
    public static String getSavedLanguage(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(SELECTED_LANGUAGE, "es"); // español por defecto
    }

    // Cambiar idioma del contexto
    public static Context setLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        context = context.createConfigurationContext(config);

        // Guardar idioma en SharedPreferences también
        saveLanguage(context, language);

        return context;
    }

}
