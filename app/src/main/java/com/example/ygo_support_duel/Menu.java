package com.example.ygo_support_duel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Menu extends AppCompatActivity {

    ImageButton btnsalir, btnmanual, btnduelo, btnopcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ✅ Aplicar tema oscuro según configuración guardada
        SharedPreferences sp = getSharedPreferences("MODE", MODE_PRIVATE);
        boolean isNightmodeActive = sp.getBoolean("night", false);
        AppCompatDelegate.setDefaultNightMode(
                isNightmodeActive ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Inicializar botones
        btnsalir = findViewById(R.id.btnSalida);
        btnmanual = findViewById(R.id.btnManual);
        btnduelo = findViewById(R.id.btnDuelo);
        btnopcion = findViewById(R.id.btnOpcion);

        // Acción salir
        btnsalir.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        // Ir a manual
        btnmanual.setOnClickListener(v -> {
            Intent i = new Intent(Menu.this, Manual.class);
            startActivity(i);
        });

        // Ir a duelo
        btnduelo.setOnClickListener(v -> {
            Intent i = new Intent(Menu.this, Duelo.class);
            startActivity(i);
        });

        // Ir a configuraciones
        btnopcion.setOnClickListener(v -> {
            Intent i = new Intent(Menu.this, Configuraciones.class);
            startActivity(i);
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getSavedLanguage(newBase)));
    }
}
