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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginBS extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {


    private RequestQueue requestQueue;
    private JsonRequest jsonRequest;

    EditText edtUsuario,edtContrasena;
    Button btnEntrar,btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario=findViewById(R.id.edtUsuario);
        edtContrasena=findViewById(R.id.edtPassword);

        btnEntrar=findViewById(R.id.btnLogin);
        btnRegistrar=findViewById(R.id.btnRegis);



        requestQueue= Volley.newRequestQueue(this);



        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iniciarSesion();

            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(LoginBS.this,Registro.class);
                startActivity(intent);
            }
        });


    }

    private void iniciarSesion() {

        String usuario=edtUsuario.getText().toString().trim();
        String contra=edtContrasena.getText().toString().trim();

        if (!usuario.isEmpty()&&!contra.isEmpty()){

            String url="http://192.168.64.2/ejemploFinal/sesion.php?user="+usuario+"&pwd="+contra;

            jsonRequest =new JsonObjectRequest(Request.Method.GET, url,null, this, this);

            requestQueue.add(jsonRequest);


        }
        else {

            Toast.makeText(this, "Revisa que tengas todos los campos llenos.", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {

      //  Toast.makeText(this, "Hubo un problema"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.e("Error",error.toString());
        Toast.makeText(this, "Hubo un error revisa que el usuario sea correcto", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        User user=new User();
        Toast.makeText(this, "Entro bien", Toast.LENGTH_SHORT).show();

        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;

        try {
            jsonObject =jsonArray.getJSONObject(0);
            user.setUser(jsonObject.optString("user"));
            user.setUser(jsonObject.optString("pwd"));
            user.setUser(jsonObject.optString("names"));



        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent=new Intent(this,Actividad2.class);
        intent.putExtra(Actividad2.nombres,user.getNames());
        startActivity(intent);
    }
}
