package com.prueba.examencompleto.modo1.Modo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prueba.examencompleto.R;
import com.prueba.examencompleto.modo1.Modo3.utils.AdapterUsuario;
import com.prueba.examencompleto.modo1.Modo3.utils.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Todos extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Usuario> usuarios;
    AdapterUsuario adapterUsuario;
    //RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        recyclerView = findViewById(R.id.recyclerView);

        //requestQueue = Volley.newRequestQueue(this);

        usuarios = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cargarWebServices();


    }


    private void cargarWebServices() {

         String ruta="http://192.168.64.2/ejemploFinal/wsJSONConsultarLista.php";


        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ruta, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Usuario usuario = null;

                //en donde inicia el array para agarrar los valores
                JSONArray jsonArray = response.optJSONArray("user");


                try {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        usuario = new Usuario();
                        JSONObject jsonObject = null;
                        //recorre cada elemento de la lista

                        jsonObject = jsonArray.getJSONObject(i);

                        usuario.setNombre(jsonObject.optString("nombre"));
                        usuario.setUsuario(jsonObject.optString("usuario"));
                        usuario.setEdad(jsonObject.optString("edad"));


                        usuarios.add(usuario);


                    }

                    adapterUsuario=new AdapterUsuario(usuarios);
                    recyclerView.setAdapter(adapterUsuario);



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Todos.this, "No hubo conexion papu", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Todos.this, "No funciono" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.e( "no",error.toString());


            }
        });
        VolleySingleton.getInstanceVolley(this).addToRequestQueue(jsonObjectRequest);

    }
}
