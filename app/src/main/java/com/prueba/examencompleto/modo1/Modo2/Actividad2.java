package com.prueba.examencompleto.modo1.Modo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.prueba.examencompleto.R;

public class Actividad2 extends AppCompatActivity {

    public static final String nombres="names";

    TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        txtNombre=findViewById(R.id.textView2);

        String usuario=getIntent().getStringExtra("names");

        txtNombre.setText(usuario);
    }
}
