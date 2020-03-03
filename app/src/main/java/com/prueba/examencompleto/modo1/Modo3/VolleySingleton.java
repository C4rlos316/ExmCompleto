package com.prueba.examencompleto.modo1.Modo3;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton instanceVolley;
    private RequestQueue requestQueue;
    private Context contexto;

    private VolleySingleton(Context context) {

        contexto=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue() {


        if (requestQueue==null){

            requestQueue= Volley.newRequestQueue(contexto.getApplicationContext());
        }

        return requestQueue;
    }

    public static synchronized VolleySingleton getInstanceVolley(Context context) {

        if (instanceVolley==null){
            instanceVolley=new VolleySingleton(context);

        }

        return instanceVolley;
    }

    public <T> void addToRequestQueue(Request<T> request){

        getRequestQueue().add(request);

    }
}