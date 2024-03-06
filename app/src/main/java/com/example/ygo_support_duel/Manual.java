package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Manual extends AppCompatActivity {

    //declarar imagenes de boton
    ImageButton btnback;

    //declarar botones
    Button btnp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);


        //asignar variable a los botones
        btnback = (ImageButton) findViewById(R.id.btnVolver);
        btnp2 = (Button) findViewById(R.id.btnPagina2);


        //metodo para volver al menu principal
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                Intent intent = new Intent(Manual.this, Menu.class);
                startActivity(intent);
            }
        });

        btnp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manual.this, Manual2.class);
                startActivity(intent);
            }
        });

    }
}