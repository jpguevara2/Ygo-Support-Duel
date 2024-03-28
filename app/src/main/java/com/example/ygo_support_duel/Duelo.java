package com.example.ygo_support_duel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class Duelo extends AppCompatActivity {

    //declarar audio
    MediaPlayer mp;

    //declarar imagenes de botones
    ImageButton btndado,btnmoneda,btnvolver2;

    //declarar txtview
    TextView txtdado,txtmoneda,txtlp1,txtlp2;

    //declarar botones
    Button btnm1000, btnm500, btnm100, btnm50, btnd,
            btnp1000, btnp500, btnp100, btnp50,
            btnm10002, btnm5002, btnm1002, btnm502, btnd2,
            btnp10002, btnp5002, btnp1002, btnp502, btnreiniciar,
            btnreiniciar2;

    //declarar radio buttons
    RadioButton rbtn1,rbtn2,rbtn3,rbtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duelo);

        //asignar variables a imagenes debotones
        btndado = (ImageButton) findViewById(R.id.btnDado);
        btnmoneda = (ImageButton) findViewById(R.id.btnMoneda);
        btnvolver2 = (ImageButton)  findViewById(R.id.btnVolver2);

        //asignar variables a  textview
        txtdado = (TextView) findViewById(R.id.txtDado);
        txtmoneda = (TextView) findViewById(R.id.txtMoneda);
        txtlp1 = (TextView) findViewById(R.id.txtLP1);
        txtlp2 = (TextView) findViewById(R.id.txtLP2);

        //asginar variable a botones
        btnm1000 = (Button) findViewById(R.id.btnM1000);
        btnm500= (Button) findViewById(R.id.btnM500);
        btnm100= (Button) findViewById(R.id.btnM100);
        btnm50= (Button) findViewById(R.id.btnM50);
        btnd= (Button) findViewById(R.id.btnDiv);
        btnp1000= (Button) findViewById(R.id.btnP1000);
        btnp500= (Button) findViewById(R.id.btnP500);
        btnp100= (Button) findViewById(R.id.btnP100);
        btnp50= (Button) findViewById(R.id.btnP50);
        btnm10002= (Button) findViewById(R.id.btnM10002);
        btnm5002= (Button) findViewById(R.id.btnM5002);
        btnm1002= (Button) findViewById(R.id.btnM1002);
        btnm502= (Button) findViewById(R.id.btnM502);
        btnd2= (Button) findViewById(R.id.btnDiv2);
        btnp10002= (Button) findViewById(R.id.btnP10002);
        btnp5002 = (Button) findViewById(R.id.btnP5002);
        btnp1002= (Button) findViewById(R.id.btnP1002);
        btnp502= (Button) findViewById(R.id.btnP502);
        btnreiniciar = (Button) findViewById(R.id.btnReiniciar);
        btnreiniciar2 = (Button) findViewById(R.id.btnReiniciar2);

        //asignar variable a radio button
        rbtn1 = (RadioButton) findViewById(R.id.rbtn1);
        rbtn2 = (RadioButton) findViewById(R.id.rbtn2);
        rbtn3 = (RadioButton) findViewById(R.id.rbtn3);
        rbtn4 = (RadioButton) findViewById(R.id.rbtn4);

        //asginar audio
        mp = MediaPlayer.create(this, R.raw.pointdrop);


        // Configurar clics de botón
        Button[] botonesNumericos = {btnm1000, btnm500, btnm100, btnm50,  btnd, btnp1000, btnp500, btnp100,
                                     btnp50, btnm10002, btnm5002, btnm1002,btnm5002, btnm1002,btnm502,
                                     btnd2,btnp10002, btnp5002, btnp1002,btnp502};

        for (Button boton : botonesNumericos) {
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View clickedButton) {
                    SharedPreferences soundSettings = getSharedPreferences("sound", MODE_PRIVATE);
                    // Extract value from "text" not from "ID", ID is given by android resources.
                    String textInButton = ((Button) clickedButton).getText().toString();

                    // get the rest of the text AFTER the sign "-" or "+"
                    int value = Integer.parseInt(textInButton.substring(1));

                    // ok, only two cases, if it's "-", then just multiply by -1 and just add.
                    if (textInButton.startsWith("-")) {
                        value *= -1;
                    }

                    // which player is this?
                    String getFullResourceId = getResources().getResourceName(clickedButton.getId());
                    boolean isPlayerOne = !getFullResourceId.endsWith("2");

                    // add the result.
                    if (isPlayerOne) {
                        int valorActual = Integer.parseInt(txtlp1.getText().toString());
                        txtlp1.setText(String.valueOf(valorActual + value));
                        if(!soundSettings.getBoolean("silentMode", false)){
                            mp.start();
                        }

                    } else {
                        int valorActual = Integer.parseInt(txtlp2.getText().toString());
                        txtlp2.setText(String.valueOf(valorActual + value));
                        if(!soundSettings.getBoolean("silentMode", false)){
                            mp.start();
                        }

                    }
                }
            });
        }


        //metodo para dividir los puntos de vida del jugador 1
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences soundSettings = getSharedPreferences("sound", MODE_PRIVATE);
                int valorActual = Integer.parseInt(txtlp1.getText().toString());
                int resultado = valorActual/2;
                txtlp1.setText(""+resultado);
                if(!soundSettings.getBoolean("silentMode", false)){
                    mp.start();
                }
            }
        });

        //metodo para dividir los puntos de vida del jugador 2
        btnd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences soundSettings = getSharedPreferences("sound", MODE_PRIVATE);
                int valorActual = Integer.parseInt(txtlp2.getText().toString());
                int resultado = valorActual/2;
                txtlp2.setText(""+resultado);
                if(!soundSettings.getBoolean("silentMode", false)){
                    mp.start();
                }
            }
        });

        //metodo para reiniciar duelo
       btnreiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valorActual = Integer.parseInt(txtlp1.getText().toString());
                int valorActual2 = Integer.parseInt(txtlp2.getText().toString());
                txtlp1.setText("8000");
                txtlp2.setText("8000");
            }
        });

        //metodo para reiniciar partida
        btnreiniciar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valorActual = Integer.parseInt(txtlp1.getText().toString());
                int valorActual2 = Integer.parseInt(txtlp2.getText().toString());
                txtlp1.setText("8000");
                txtlp2.setText("8000");
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
            }
        });

        //metodo para volver al menu principal
        btnvolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Duelo.this, Menu.class);
                startActivity(intent);
            }
        });

        //metodo para tirar el dado
        btndado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int number = random.nextInt(6) + 1;
                EditText editText = (EditText) findViewById(R.id.txtDado);
                txtdado.setText(""+number);
            }
        });

        //metodo para tirar la moneda
        btnmoneda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int number = random.nextInt(2) + 1;
                EditText editText = (EditText) findViewById(R.id.txtMoneda);
                if(number==1){
                    txtmoneda.setText("cara");
                }else{
                    txtmoneda.setText("cruz");
                }
            }
        });

    }

}