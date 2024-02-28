package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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


        SharedPreferences sp = getSharedPreferences("SP", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int theme = sp.getInt("theme", 1);
        if(theme==1){
            stema.setChecked(true);
        }else{
            stema.setChecked(false);
        }

        stema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stema.isChecked()){
                    editor.putInt("theme", 0);
                }else{
                    editor.putInt("theme", 1);
                }
                    editor.commit();
                    setDayNigth();
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

    //Metodo para cambiar el modo de la interfaz
    public void setDayNigth(){
        SharedPreferences sp = getSharedPreferences("SP", this.MODE_PRIVATE);
        int theme = sp.getInt("theme",1);
        if(theme==0){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}