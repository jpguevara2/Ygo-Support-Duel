package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

public class Configuraciones extends AppCompatActivity {

    //Declarar Switch
    Switch stema;

    Boolean nightmode;

    //Declarar Imagen de Boton
    ImageButton btnvolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);


        //Asignar variable a Switch
        stema = (Switch) findViewById(R.id.swtichTema);

        //Asginar variable a Boton
        btnvolver = (ImageButton) findViewById(R.id.btnVolver);

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

        //Metodo para volver al menu Principal
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Configuraciones.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}