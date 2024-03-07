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

        SharedPreferences sp;

        sp = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightmode = sp.getBoolean("night", false);


        if(nightmode){
            stema.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            //Metodo para cambiar el modo de la interfaz
        }
        stema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor;
                {
                  if(nightmode){
                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                  editor = sp.edit();
                  editor.putBoolean( "night", false);
                  }else{
                      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                      editor = sp.edit();
                      editor.putBoolean( "night", true);
                  }
                  editor.apply();
                  //editor.commit();
                }
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