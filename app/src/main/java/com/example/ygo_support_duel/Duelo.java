package com.example.ygo_support_duel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Duelo extends AppCompatActivity {

    // Audio
    MediaPlayer mp;

    // UI elements
    ImageButton btndado, btnmoneda, btnvolver2;
    TextView txtdado, txtmoneda, txtlp1, txtlp2;
    Button btneditar,btneditar2, btnd, btnd2, btnreiniciar, btnreiniciar2;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duelo);

        // Botones
        btndado = findViewById(R.id.btnDado);
        btnmoneda = findViewById(R.id.btnMoneda);
        btnvolver2 = findViewById(R.id.btnVolver2);

        // TextViews
        txtdado = findViewById(R.id.txtDado);
        txtmoneda = findViewById(R.id.txtMoneda);
        txtlp1 = findViewById(R.id.txtLP1);
        txtlp2 = findViewById(R.id.txtLP2);

        // Botones LP
        btneditar = findViewById(R.id.btnEditarLP1);
        btneditar2 = findViewById(R.id.btnEditarLP2);
        btnd = findViewById(R.id.btnDiv);
        btnd2 = findViewById(R.id.btnDiv2);
        btnreiniciar = findViewById(R.id.btnReiniciar);
        btnreiniciar2 = findViewById(R.id.btnReiniciar2);

        // RadioButtons
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);

        // Audio
        mp = MediaPlayer.create(this, R.raw.pointdrop);

        // Dividir LP
        btnd.setOnClickListener(v -> dividirLP(txtlp1));
        btnd2.setOnClickListener(v -> dividirLP(txtlp2));

        // Reiniciar duelo
        btnreiniciar.setOnClickListener(v -> reiniciarDuelo(false));
        btnreiniciar2.setOnClickListener(v -> reiniciarDuelo(true));

        btneditar.setOnClickListener(v -> mostrarDialogoModificarLP(txtlp1));
        btneditar2.setOnClickListener(v -> mostrarDialogoModificarLP(txtlp2));
        // Volver al menú
        btnvolver2.setOnClickListener(v -> {
            Intent intent = new Intent(Duelo.this, Menu.class);
            startActivity(intent);
        });

        // Dado
        btndado.setOnClickListener(v -> {
            txtdado.setText(""); // Borra el texto primero

            new Handler().postDelayed(() -> {
                int number = new Random().nextInt(6) + 1;
                txtdado.setText(String.valueOf(number));
            }, 300); // Espera 300 milisegundos antes de mostrar el resultado
        });

        // Moneda
        btnmoneda.setOnClickListener(v -> {
            txtmoneda.setText(""); // Borra el texto primero

            new Handler().postDelayed(() -> {
                int number = new Random().nextInt(2) + 1;
                String resultado = (number == 1) ? getString(R.string.coin_heads) : getString(R.string.coin_tails);
                txtmoneda.setText(resultado);
            }, 300); // 300 ms de espera
        });
    }

    // 🔢 Mostrar teclado numérico al modificar LP
    private void mostrarDialogoConOpcion(TextView txtLP) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modificar LP");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        input.setHint("Ingrese cantidad");
        builder.setView(input);

        builder.setPositiveButton("Sumar", (dialog, which) -> aplicarCambioALP(txtLP, input.getText().toString(), true));
        builder.setNegativeButton("Restar", (dialog, which) -> aplicarCambioALP(txtLP, input.getText().toString(), false));
        builder.setNeutralButton("Cancelar", null);

        builder.show();
    }

    // 🧮 Aplicar cambio a LP
    private void aplicarCambioALP(TextView txtLP, String valorTexto, boolean esSuma) {
        if (!valorTexto.isEmpty()) {
            try {
                int valor = Integer.parseInt(valorTexto);
                if (!esSuma) valor *= -1;

                int valorActual = Integer.parseInt(txtLP.getText().toString());
                int resultado = valorActual + valor;

                animateNumberChange(txtLP, valorActual, resultado);

                // Reproducir sonido solo si ES RESTA y el modo silencioso está desactivado
                if (!esSuma) {
                    SharedPreferences soundSettings = getSharedPreferences("sound", MODE_PRIVATE);
                    if (!soundSettings.getBoolean("silentMode", false)) {
                        mp.start();
                    }
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Valor no válido", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //  Dividir LP a la mitad
    private void dividirLP(TextView txtLP) {
        int valorActual = Integer.parseInt(txtLP.getText().toString());
        int resultado = valorActual / 2;
        animateNumberChange(txtLP, valorActual, resultado);

        SharedPreferences soundSettings = getSharedPreferences("sound", MODE_PRIVATE);
        if (!soundSettings.getBoolean("silentMode", false)) {
            mp.start();
        }
    }

    // 🔄 Animación al cambiar número
    private void animateNumberChange(final TextView textView, final int start, final int end) {
        final Handler handler = new Handler(Looper.getMainLooper());

        final int step = (Math.abs(end - start) >= 1000) ? (start < end ? 25 : -25) :
                (Math.abs(end - start) >= 500) ? (start < end ? 10 : -10) :
                        (start < end ? 1 : -1);

        final Runnable[] runnable = new Runnable[1];
        runnable[0] = new Runnable() {
            int current = start;

            @Override
            public void run() {
                if ((step > 0 && current >= end) || (step < 0 && current <= end)) {
                    textView.setText(String.valueOf(end));
                    return;
                }

                current += step;
                if ((step > 0 && current > end) || (step < 0 && current < end)) {
                    current = end;
                }

                textView.setText(String.valueOf(current));
                handler.postDelayed(this, 20);
            }
        };

        handler.post(runnable[0]);
    }

    private void mostrarDialogoModificarLP(TextView txtLP) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_title_modify_lp));

        final EditText input = new EditText(this);
        input.setHint(getString(R.string.dialog_hint_example));
        input.setInputType(InputType.TYPE_CLASS_NUMBER);  // Teclado numérico
        builder.setView(input);

        builder.setPositiveButton(getString(R.string.button_add), (dialog, which) -> {
            aplicarCambioALP(txtLP, input.getText().toString(), true);
        });

        builder.setNegativeButton(getString(R.string.button_subtract), (dialog, which) -> {
            aplicarCambioALP(txtLP, input.getText().toString(), false);
        });

        builder.setNeutralButton(getString(R.string.button_cancel), null);

        builder.show();
    }



    // 🔁 Reiniciar partida o duelo
    private void reiniciarDuelo(boolean todo) {
        txtlp1.setText("8000");
        txtlp2.setText("8000");
        txtdado.setText("");
        txtmoneda.setText("");

        if (todo) {
            rbtn1.setChecked(false);
            rbtn2.setChecked(false);
            rbtn3.setChecked(false);
            rbtn4.setChecked(false);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getSavedLanguage(newBase)));
    }

}
