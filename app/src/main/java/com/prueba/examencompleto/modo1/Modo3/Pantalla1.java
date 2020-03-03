package com.prueba.examencompleto.modo1.Modo3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.prueba.examencompleto.R;
import com.prueba.examencompleto.modo1.Modo3.utils.LoginRequest;
import com.prueba.examencompleto.modo1.Modo3.utils.RegistroRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Pantalla1 extends AppCompatActivity {


    EditText edtUsuario,edtContrasena;
    Button btnEntrar,btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edtUsuario=findViewById(R.id.edtUsuario);
        edtContrasena=findViewById(R.id.edtPassword);

        recuperarDatos();


        btnEntrar=findViewById(R.id.btnLogin);
        btnRegistrar=findViewById(R.id.btnRegis);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registro=new Intent(Pantalla1.this,Pantalla2Re.class);
                startActivity(registro);
                finish();

            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usuario=edtUsuario.getText().toString().trim();
                final String contrasena=edtContrasena.getText().toString().trim();

                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonRespuesta=new JSONObject(response);
                            boolean ok=jsonRespuesta.getBoolean("success");

                            if (ok==true){

                                String nombre=jsonRespuesta.getString("nombre");
                                int edad =jsonRespuesta.getInt("edad");

                                guardarPreferencias(usuario,contrasena);

                                Intent bienvenido=new Intent(Pantalla1.this,Pantalla3.class);
                                bienvenido.putExtra("nombre",nombre);
                                bienvenido.putExtra("edad",edad);
                                startActivity(bienvenido);
                                finish();

                            }
                            else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(Pantalla1.this);
                                alerta.setMessage("Fallo en el login")
                                        .setNegativeButton("Reintentar",null)
                                        .create().show();

                            }


                        } catch (JSONException e) {
                            e.getMessage();
                        }


                    }
                };

                Response.ErrorListener error=new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                };


                //Pasar parametros para crear la conexion
                LoginRequest loginRequest=new LoginRequest(usuario,contrasena,respuesta,error);
                RequestQueue cola= Volley.newRequestQueue(Pantalla1.this);
                cola.add(loginRequest);


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


    private void recuperarDatos(){

        SharedPreferences preferences=getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
        edtUsuario.setText(preferences.getString("usuario",""));
        edtContrasena.setText(preferences.getString("password",""));


    }



}
