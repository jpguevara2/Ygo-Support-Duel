package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //declarar imagenes de botones
    ImageButton btnsalir,btnmanual,btnduelo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignar variables a imagenes de botones
        btnsalir = (ImageButton) findViewById(R.id.btnSalida);
        btnmanual = (ImageButton) findViewById(R.id.btnManual);
        btnduelo = (ImageButton) findViewById(R.id.btnDuelo);

        //metodo para salir de la app
        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
            }
        });

            //metodo para ir a la pantalla del manual de la app
            btnmanual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Manual.class);
                    startActivity(i);
                }
            });

        //metodo para ir a la pantalla de duelo
        btnduelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Duelo.class);
                startActivity(i);
            }
        });


    }



    }
