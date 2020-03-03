package com.prueba.examencompleto.modo1.Modo0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.prueba.examencompleto.R;
import com.prueba.examencompleto.modo1.Modo0.MainActivity;

public class Principal extends AppCompatActivity {

    Button btnCerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        btnCerrarSesion=findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences=getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();

                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }



}
