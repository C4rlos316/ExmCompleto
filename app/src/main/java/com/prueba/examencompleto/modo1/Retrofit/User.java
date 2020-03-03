package com.prueba.examencompleto.modo1.Retrofit;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("success")
    public Boolean success;

    @SerializedName("nombre")
    public String nombre;

    @SerializedName("usuario")
    public String usuario;


    @SerializedName("edad")
    public String edad;


    @SerializedName("clave")
    public String clave;

    public Boolean getSuccess() {
        return success;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEdad() {
        return edad;
    }

    public String getClave() {
        return clave;
    }
}
