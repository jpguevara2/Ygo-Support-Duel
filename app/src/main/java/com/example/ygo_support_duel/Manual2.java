package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Manual2 extends AppCompatActivity {

    //declarar imagenes de boton
    ImageButton btnback;

    //declarar botones
    Button btnp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual2);

        //asignar variable a los botones
        btnback = (ImageButton) findViewById(R.id.btnVolver);
        btnp1 = (Button) findViewById(R.id.btnPagina1);

        //metodo para volver al menu principal
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                Intent intent = new Intent(Manual2.this, Menu.class);
                startActivity(intent);
            }
        });

        //metodo para volver a la pagina 1 del manual
        btnp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manual2.this, Manual.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getSavedLanguage(newBase)));
    }

}