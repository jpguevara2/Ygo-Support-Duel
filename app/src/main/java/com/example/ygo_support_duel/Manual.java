package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Manual extends AppCompatActivity {

    //declarar imagenes de boton
    ImageButton btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);


        //asignar variable a imagen de boton
        btnback = (ImageButton) findViewById(R.id.btnVolver);


        //metodo para volver al menu principal
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                Intent intent = new Intent(Manual.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}