package com.prueba.examencompleto.modo1.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.prueba.examencompleto.R;
import com.prueba.examencompleto.modo1.Modo3.Pantalla1;
import com.prueba.examencompleto.modo1.Modo3.Pantalla2Re;
import com.prueba.examencompleto.modo1.Modo3.Pantalla3;
import com.prueba.examencompleto.modo1.Modo3.utils.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioRetrofit extends AppCompatActivity {


        EditText edtUsuario,edtContrasena;
        Button btnEntrar,btnRegistrar;
        public static ApiInterface apiInterface;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);


        edtUsuario=findViewById(R.id.edtUsuario);
        edtContrasena=findViewById(R.id.edtPassword);

        recuperarDatos();


        btnEntrar=findViewById(R.id.btnLogin);
        btnRegistrar=findViewById(R.id.btnRegis);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registro=new Intent(InicioRetrofit.this, RegistroRetrofit.class);
                startActivity(registro);
                finish();

            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usuario=edtUsuario.getText().toString().trim();
                final String contrasena=edtContrasena.getText().toString().trim();

                perforRegistration(usuario,contrasena);






            }
        });


    }



    public void perforRegistration(String nombre,String password) {


        Call<User> call = apiInterface.performUserLogin(nombre,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getSuccess().equals("success")){
                    Intent intent=new Intent(InicioRetrofit.this,Pantalla3.class);
                    startActivity(intent);

                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.e("error",t.getMessage());

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
