package com.svalero.tiendaapp.api;

import com.svalero.tiendaapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TiendaApiInterface {

    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call <User> addUser(@Body User user);
}
