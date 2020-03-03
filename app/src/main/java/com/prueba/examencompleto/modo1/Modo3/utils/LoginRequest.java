package com.prueba.examencompleto.modo1.Modo3.utils;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String ruta="http://192.168.64.2/ejemploFinal/login.php";
    private Map<String,String> parametros;
    public LoginRequest(String usuario, String clave,Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Method.POST,ruta,listener,errorListener);
        parametros=new HashMap<>();
        parametros.put("usuario",usuario);
        parametros.put("clave",clave);

    }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
