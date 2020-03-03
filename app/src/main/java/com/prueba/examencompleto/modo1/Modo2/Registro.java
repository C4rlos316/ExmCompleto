package com.prueba.examencompleto.modo1.Modo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.prueba.examencompleto.R;

import org.json.JSONObject;

public class Registro extends AppCompatActivity  implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText edtUsuario,edtPass,edtNombre;
    Button btnRegistro,btnInic;

    RequestQueue requestQueue;

    JsonRequest jsonRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        edtNombre=findViewById(R.id.edtNombre);
        edtUsuario=findViewById(R.id.edtUsuario);
        edtPass=findViewById(R.id.edtPassword);

        btnRegistro=findViewById(R.id.btnLogin);

        btnInic=findViewById(R.id.btnInic);

        requestQueue= Volley.newRequestQueue(this);


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                registroUsuario();


            }
        });

        btnInic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Registro.this,LoginBS.class);
                startActivity(intent);


            }
        });
    }

    private void registroUsuario() {

        String usuario=edtUsuario.getText().toString().trim();
        String nombre=edtNombre.getText().toString().trim();
        String contra=edtPass.getText().toString().trim();

        if (!usuario.isEmpty()&&!contra.isEmpty()&&!nombre.isEmpty()){

            String url="http://192.168.64.2/ejemploFinal/registrar.php?names"+nombre+"&user="+usuario+"&pwd="+contra;

            jsonRequest =new JsonObjectRequest(Request.Method.GET, url,null, this, this);

            requestQueue.add(jsonRequest);


        }
        else {

            Toast.makeText(this, "Revisa que tengas todos los campos llenos.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
        Log.e("ErrorR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(this, "Se Registro el usuario.", Toast.LENGTH_SHORT).show();
        limpiarCajas();
    }

    private void limpiarCajas() {

        edtUsuario.setText("");
        edtNombre.setText("");
        edtPass.setText("");

    }
}
