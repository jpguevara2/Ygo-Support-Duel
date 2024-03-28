package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import java.util.Locale;


public class Configuraciones extends AppCompatActivity {

    //Declarar Switch
    Switch stema,saudio,sidioma;

    //declarar Boolean del modo nocturno
    Boolean nightmode;

    //Declarar Imagen de Boton
    ImageButton btnvolver;

    //declarar audio
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);

        //Asignar variable a Switch
        stema = (Switch) findViewById(R.id.swtichTema);
        saudio = (Switch) findViewById(R.id.switchAudio);
        sidioma = (Switch) findViewById(R.id.switchIdioma);

        //Asginar variable a Boton
        btnvolver = (ImageButton) findViewById(R.id.btnVolver);

        //Asignar audio
        mp = MediaPlayer.create(this, R.raw.pointdrop);


        //Metodo para apretar cambiar la opcion del Switch

        // get preferences from OS
        SharedPreferences sp = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        SharedPreferences spsound = getSharedPreferences("sound", MODE_PRIVATE);
        SharedPreferences spidioma = getSharedPreferences("idioma", MODE_PRIVATE);
        saudio.setChecked(spsound.getBoolean("sound",false));
        sidioma.setChecked(spidioma.getBoolean("idioma",false));


        // get the "night" value, default to false if not found
        boolean isNightmodeActive = sp.getBoolean("night", false);
        stema.setChecked(isNightmodeActive);

        stema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // whenever the night switch is clicked, bring the settings
                SharedPreferences sp = getSharedPreferences("MODE", Context.MODE_PRIVATE);

                // bring the editr FROM the settings
                SharedPreferences.Editor editor = sp.edit();

                // check if the UI was checked or not
                boolean isChecked = stema.isChecked();

                // now let's set the values first according OS (setDefaultNightMode), then to the settings using the editor
                AppCompatDelegate.setDefaultNightMode(isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean( "night", isChecked);
                editor.apply();
            }
        });

        //Metodo para activar/desactivar audio
        saudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor spEditor = getSharedPreferences("sound", MODE_PRIVATE).edit();
                spEditor.putBoolean("sound", isChecked);

                if (isChecked) {
                    spEditor.putBoolean("silentMode", false);
                    mp.setVolume(0,1);
                } else {
                    spEditor.putBoolean("silentMode", true);
                    mp.setVolume(0,0);

                }
                spEditor.apply();
            }
        });

        //Metodo para cambiar el idioma
        sidioma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor spEditor = getSharedPreferences("idioma", MODE_PRIVATE).edit();
                spEditor.putBoolean("idioma", isChecked);

                if (isChecked) {
                    // Cambiar al español
                    spEditor.putBoolean("idioma", false);
                    cambiarIdioma("es");
                } else {
                    // Cambiar al idioma predeterminado (inglés)
                    spEditor.putBoolean("idioma", true);
                    cambiarIdioma("en");
                }
                spEditor.apply();
            }
        });

        //Metodo para volver al menu Principal
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Configuraciones.this, Menu.class);
                startActivity(intent);
            }
        });


    }
    //Metodo para liberar los recursos de media player
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
        }
    }

    private void cambiarIdioma(String codigoIdioma) {
        Locale locale = new Locale(codigoIdioma);
        Locale.setDefault(locale);

        Configuration config = new Configuration();

        getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());


        // Re-arranca la actividad para aplicar el cambio de idioma
        Intent intent = new Intent(this, Configuraciones.class); // Reemplaza TuActividadActual con la clase de tu actividad actual
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}