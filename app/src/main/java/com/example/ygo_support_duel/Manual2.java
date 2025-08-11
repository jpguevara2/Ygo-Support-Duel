package com.example.ygo_support_duel;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Manual2 extends AppCompatActivity {

    ImageButton btnback;
    Button btnp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual2);

        btnback = (ImageButton) findViewById(R.id.btnVolver);
        btnp1 = (Button) findViewById(R.id.btnPagina1);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manual2.this, Menu.class);
                startActivity(intent);
            }
        });

        btnp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manual2.this, Manual.class);
                startActivity(intent);
            }
        });

    }
    }


