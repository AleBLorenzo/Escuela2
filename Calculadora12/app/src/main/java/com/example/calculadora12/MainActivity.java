package com.example.calculadora12;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Button boton;
    EditText operador1;
    EditText operador2;
    TextView resultado;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        boton = (Button)findViewById(R.id.button);
        operador1 = (EditText)findViewById(R.id.editTextNumber);
        operador2 = (EditText)findViewById(R.id.editTextNumber2);
        resultado = (TextView)findViewById(R.id.textView);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int num1 = Integer.parseInt(operador1.getText().toString());
                    int num2 = Integer.parseInt(operador2.getText().toString());
                    int suma = num1 + num2;
                    resultado.setText(String.valueOf(suma));
                } catch (NumberFormatException e) {
                    // Mensaje de error si alguno no es un número entero
                    resultado.setText("Error: introduce solo números");
                }
            }
        });

    }


}