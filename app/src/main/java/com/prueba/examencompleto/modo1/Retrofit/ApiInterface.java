package com.prueba.examencompleto.modo1.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("registro.php")
    Call<User> performRegistration(@Query("nombre")String nombre,
                                   @Query("usuario") String usuario,
                                   @Query("edad")String edad,
                                    @Query("clave")String clave);

    @POST("login.php")
    Call<User> performUserLogin( @Query("usuario") String usuario,
                                 @Query("clave")String clave);
}
