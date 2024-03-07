package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class Configuraciones extends AppCompatActivity {

    //Declarar Switch
    Switch stema,saudio;

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

        //Asginar variable a Boton
        btnvolver = (ImageButton) findViewById(R.id.btnVolver);

        //Asignar audio
        mp = MediaPlayer.create(this, R.raw.pointdrop);

        //Metodo para apretar cambiar la opcion del Switch

        // get preferences from OS
        SharedPreferences sp = getSharedPreferences("MODE", Context.MODE_PRIVATE);

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
                if (isChecked) {
                    mp.start();
                } else {
                    mp.pause();
                }
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
}