package com.svalero.tiendaapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TiendaApi {

    public static TiendaApiInterface buildInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TiendaApiInterface.class);
    }
}
