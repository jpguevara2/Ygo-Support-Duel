package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity {

    //declarar botones
    Button n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, n00, n000, nmenos, nmas, ndividir, nigual, nborrar, nvolver;
    TextView pantalla;

    // Variables para la lógica de la calculadora
    double numero1 = 0, numero2 = 0, resultado = 0;
    String operador = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);


        //asignar variables a botones
        n1 = (Button) findViewById(R.id.btn1);
        n2 = (Button) findViewById(R.id.btn2);
        n3 = (Button) findViewById(R.id.btn3);
        n4 = (Button) findViewById(R.id.btn4);
        n5 = (Button) findViewById(R.id.btn5);
        n6 = (Button) findViewById(R.id.btn6);
        n7 = (Button) findViewById(R.id.btn7);
        n8 = (Button) findViewById(R.id.btn8);
        n9 = (Button) findViewById(R.id.btn9);
        n0 = (Button) findViewById(R.id.btn0);
        n00 = (Button) findViewById(R.id.btn00);
        n000 = (Button) findViewById(R.id.btn000);
        nmenos = (Button) findViewById(R.id.btnMenos);
        nmas = (Button) findViewById(R.id.btnMas);
        ndividir = (Button) findViewById(R.id.btnDividir);
        nigual = (Button) findViewById(R.id.btnIgual);
        nborrar = (Button) findViewById(R.id.btnBorrar);
        nvolver = (Button) findViewById(R.id.btnSalir);
        pantalla = (TextView) findViewById(R.id.txtLp1);

        // Configurar clics de botón
        Button[] botonesNumericos = {n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n00, n000};
        for (Button boton : botonesNumericos) {
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNumeroClick(((Button) view).getText().toString());
                }
            });
        }


        nmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperadorClick("+");
            }
        });

        nmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperadorClick("-");
            }
        });

        ndividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperadorClick("/2");
            }
        });


        nigual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularResultado();
            }
        });

       nborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrar();
            }
        });


    }

    private void onNumeroClick(String numero) {
        pantalla.append(numero);
    }

    private void onOperadorClick(String nuevoOperador) {
        operador = nuevoOperador;
        if (!pantalla.getText().toString().isEmpty()) {
            numero1 = Double.parseDouble(pantalla.getText().toString());
            pantalla.setText("");
        }
    }

    private void calcularResultado() {
        if (!pantalla.getText().toString().isEmpty() && !operador.isEmpty()) {
            numero2 = Double.parseDouble(pantalla.getText().toString());

            switch (operador) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        pantalla.setText("Error");
                        return;
                    }
                    break;
            }

            pantalla.setText(String.valueOf(resultado));
            operador = "";
        }
    }

    //Metodo para borrar numeros del calculo
    private void borrar() {
        String textoActual = pantalla.getText().toString();
        if (!textoActual.isEmpty()) {
            pantalla.setText(textoActual.substring(0, textoActual.length() - 1));
        }
    }

}