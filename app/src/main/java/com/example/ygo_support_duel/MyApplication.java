package com.example.ygo_support_duel;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        String language = LocaleHelper.getSavedLanguage(base);
        super.attachBaseContext(LocaleHelper.setLocale(base, language));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LocaleHelper.setLocale(this, LocaleHelper.getSavedLanguage(this));
    }
}
