package com.prueba.examencompleto.modo1.Modo0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prueba.examencompleto.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre,edtContrasena;
    Button btnEntrar;
    String usuario,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre=findViewById(R.id.edtUsuario);
        edtContrasena=findViewById(R.id.edtPassword);

        btnEntrar=findViewById(R.id.btnLogin);

        recuperarDatos();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario=edtNombre.getText().toString().trim();
                password=edtContrasena.getText().toString().trim();

                if (!usuario.isEmpty()&&!password.isEmpty()) {

                    validarUsuario("http://192.168.64.2/ejemploExamen/validar_usuario.php");

                }
                else {

                    Toast.makeText(MainActivity.this, "Revisa que tengas todos los campos llenos.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void validarUsuario(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()){
                    guardarPreferencias();
                    Intent intent=new Intent(getApplicationContext(), Principal.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(MainActivity.this, "El usuario y contrasenas incorrectas.", Toast.LENGTH_SHORT).show();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("usuario",usuario);
                parametros.put("password",password);

                return parametros;
            }
        };


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void guardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("password",password);
        editor.putBoolean("sesion",true);
        editor.commit();

    }


    private void recuperarDatos(){

        SharedPreferences preferences=getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
        edtNombre.setText(preferences.getString("usuario",""));
        edtContrasena.setText(preferences.getString("password",""));


    }
}
