package com.example.bdexternas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProveedorServicios {
    @GET("usuarios")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<List<Usuarios>> getUsuarios();

    @GET("mensajes")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<List<Mensajes>> getMensajes();


    @GET("usuarios/{id}")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<List<Usuarios>> getUsuario(@Path("id") int _id);

    @POST("usuarios")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<RespuestaJson> insertarUsuario(@Body Usuarios usuario);


    @POST("mensajes")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<RespuestaJson> insertarMensaje(@Body Mensajes mensaje);



}
