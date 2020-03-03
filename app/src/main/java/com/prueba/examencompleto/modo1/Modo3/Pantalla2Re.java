package com.prueba.examencompleto.modo1.Modo3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.prueba.examencompleto.R;
import com.prueba.examencompleto.modo1.Modo3.utils.RegistroRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Pantalla2Re extends AppCompatActivity {

    EditText edtUsuario, edtPass, edtNombre,edtEdad;
    Button btnRegistro, btnInic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        edtNombre = findViewById(R.id.edtNombre);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPass = findViewById(R.id.edtPassword);

        edtEdad = findViewById(R.id.edtEdad);


        btnRegistro = findViewById(R.id.btnLogin);

        btnInic = findViewById(R.id.btnInic);


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String correo=edtUsuario.getText().toString().trim();
                final String nombre=edtNombre.getText().toString().trim();
                final String pass=edtPass.getText().toString().trim();
                final String edad= edtEdad.getText().toString().trim();

                Response.Listener<String> response = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonRespuesta=new JSONObject(response);
                            //Viene de el php
                            boolean ok = jsonRespuesta.getBoolean("success");

                            if (ok==true){

                                guardarPreferencias(correo,pass);
                                Intent i = new Intent(Pantalla2Re.this,Pantalla3.class);
                                i.putExtra("nombre",nombre);
                                i.putExtra("edad",edad);
                                startActivity(i);
                                finish();

                            }
                            else {

                                AlertDialog.Builder alerta = new AlertDialog.Builder(Pantalla2Re.this);
                                alerta.setMessage("Fallo en el registro")
                                .setNegativeButton("Reintentar",null)
                                .create().show();

                            }

                        } catch (JSONException e) {
                            e.getMessage();
                        }


                    }
                };

                Response.ErrorListener error = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Pantalla2Re.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                    }
                };

                //Pasar parametros para crear la conexion
                RegistroRequest registroRequest=new RegistroRequest(nombre,correo,pass,edad,response,error);
                RequestQueue cola= Volley.newRequestQueue(Pantalla2Re.this);
                cola.add(registroRequest);



            }
        });


    }

    private void guardarPreferencias(String usuario,String password){
        SharedPreferences preferences=getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("password",password);
        editor.putBoolean("sesion",true);
        editor.commit();

    }


}
