package com.prueba.examencompleto.modo1.Retrofit;

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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.prueba.examencompleto.R;
import com.prueba.examencompleto.modo1.Modo3.Pantalla2Re;
import com.prueba.examencompleto.modo1.Modo3.Pantalla3;
import com.prueba.examencompleto.modo1.Modo3.utils.RegistroRequest;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroRetrofit extends AppCompatActivity {

    EditText edtUsuario, edtPass, edtNombre,edtEdad;
    Button btnRegistro, btnInic;

    public static ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);


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


                perforRegistration(nombre,correo,edad,pass);






            }
        });



    }


    public void perforRegistration(String nombre,String usuario,String edad,String password) {


        Call<User> call = apiInterface.performRegistration(nombre,usuario,edad,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {


                    Intent i = new Intent(RegistroRetrofit.this,Pantalla3.class);
                    startActivity(i);
                    finish();


                }



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        edtNombre.setText("");
        edtPass.setText("");
        edtUsuario.setText("");


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
