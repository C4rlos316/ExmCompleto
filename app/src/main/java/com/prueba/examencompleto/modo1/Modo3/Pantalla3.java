package com.prueba.examencompleto.modo1.Modo3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.prueba.examencompleto.R;
import com.prueba.examencompleto.modo1.Modo0.MainActivity;
import com.prueba.examencompleto.modo1.Modo3.utils.AdapterUsuario;
import com.prueba.examencompleto.modo1.Modo3.utils.Usuario;

import java.util.ArrayList;

public class Pantalla3 extends AppCompatActivity {

    TextView textView;

    Button btnCerrarSesion,btnTodos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Intent i = this.getIntent();
        String nombre=i.getStringExtra("nombre");
        int edad =i.getIntExtra("edad",-1);

        textView = findViewById(R.id.textView);

        textView.setText("Bienvenido "+nombre+" Su edad: "+edad+".");

        btnTodos=findViewById(R.id.btnTodos);



        btnCerrarSesion=findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences=getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();

                Intent intent=new Intent(getApplicationContext(), Pantalla1.class);
                startActivity(intent);
                finish();

            }
        });

        btnTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(), Todos.class);
                startActivity(intent);

            }
        });


    }
}
