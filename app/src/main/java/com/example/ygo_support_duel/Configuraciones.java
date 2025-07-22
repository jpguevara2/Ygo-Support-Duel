package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class Configuraciones extends AppCompatActivity {

    // Declarar Switches
    Switch stema, saudio, sidioma;

    // Declarar botón
    ImageButton btnvolver;

    // Declarar audio
    MediaPlayer mp;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getSavedLanguage(newBase)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);

        // Asignar vistas a variables
        stema = findViewById(R.id.swtichTema);
        saudio = findViewById(R.id.switchAudio);
        sidioma = findViewById(R.id.switchIdioma);
        btnvolver = findViewById(R.id.btnVolver);
        mp = MediaPlayer.create(this, R.raw.pointdrop);

        // Leer preferencias
        SharedPreferences sp = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        SharedPreferences spsound = getSharedPreferences("sound", MODE_PRIVATE);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);

        // Aplicar estados guardados a los switches
        boolean isNightmodeActive = sp.getBoolean("night", false);
        stema.setChecked(isNightmodeActive);

        boolean isSoundActive = spsound.getBoolean("sound", false);
        saudio.setChecked(isSoundActive);
        // Ajustar volumen inicial
        mp.setVolume(isSoundActive ? 1f : 0f, isSoundActive ? 1f : 0f);

        boolean estadoSwitchIdioma = prefs.getBoolean("switchIdiomaChecked", false);
        sidioma.setChecked(estadoSwitchIdioma);

        // Cambiar tema
        stema.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                AppCompatDelegate.setDefaultNightMode(isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("night", isChecked);
                editor.apply();
                recreate(); // Reiniciar actividad para aplicar tema
            }
        });

        // Activar/desactivar sonido
        saudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor spEditor = getSharedPreferences("sound", MODE_PRIVATE).edit();
                spEditor.putBoolean("sound", isChecked);
                spEditor.putBoolean("silentMode", !isChecked);
                spEditor.apply();
                mp.setVolume(isChecked ? 1f : 0f, isChecked ? 1f : 0f);
            }
        });

        // Cambiar idioma
        sidioma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String langCode = isChecked ? "en" : "es";
                LocaleHelper.saveLanguage(Configuraciones.this, langCode);

                SharedPreferences.Editor spEditor = getSharedPreferences("settings", MODE_PRIVATE).edit();
                spEditor.putBoolean("switchIdiomaChecked", isChecked);
                spEditor.apply();

                recreate(); // Reiniciar actividad para aplicar idioma
            }
        });

        // Volver al menú principal
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Configuraciones.this, Menu.class);
                startActivity(intent);
                finish(); // Cerrar esta actividad para evitar apilar
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
        }
    }
}
