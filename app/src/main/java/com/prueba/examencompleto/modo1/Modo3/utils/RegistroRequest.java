package com.prueba.examencompleto.modo1.Modo3.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {

    private static final String ruta="http://192.168.64.2/ejemploFinal/registro.php";
    private Map<String,String> parametros;
    public RegistroRequest(String nombre, String usuario, String clave, String edad, Response.Listener<String> listener,Response.ErrorListener errorListener){

        super(Method.POST,ruta,listener,errorListener);
        parametros=new HashMap<>();
        parametros.put("nombre",nombre);
        parametros.put("usuario",usuario);
        parametros.put("clave",clave);
        parametros.put("edad",edad+"");

    }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
