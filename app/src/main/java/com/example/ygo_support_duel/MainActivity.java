package com.example.ygo_support_duel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // get the preferences from OS and check the "night" value as soon as we start the app
        SharedPreferences sp = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean isNightmodeActive = sp.getBoolean("night", false);

        // if active, set the night mode to ON
        if(isNightmodeActive){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Menu.class);
                startActivity(i);
                finish();
            }
        }, 4000);

    }

}
