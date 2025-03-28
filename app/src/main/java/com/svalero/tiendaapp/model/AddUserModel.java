package com.svalero.tiendaapp.model;

import com.svalero.tiendaapp.api.TiendaApi;
import com.svalero.tiendaapp.api.TiendaApiInterface;
import com.svalero.tiendaapp.contract.AddUserContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserModel implements AddUserContract.Model {


    private final TiendaApiInterface apiInterface;

    public AddUserModel() {
        this.apiInterface = TiendaApi.buildInstance();
    }

    @Override
    public void getUserById(long userId, OnUserActionListener listener) {
        Call<User> call = apiInterface.getUserById(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onUserLoaded(response.body());
                } else if (response.code() == 404) {
                    listener.onError("Usuario no encontrado");
                } else {
                    listener.onError("Error al obtener el usuario");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onError("Error de red: " + t.getMessage());
            }
        });
    }

    @Override
    public void saveUser(User user, OnUserActionListener listener) {
        Call<User> call = apiInterface.addUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 201 && response.body() != null) {
                    listener.onUserSaved(true);
                } else if (response.code() == 400) {
                    listener.onError("Datos inválidos");
                } else if (response.code() == 500) {
                    listener.onError("Error del servidor");
                } else {
                    listener.onError("Error desconocido: código " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onError("Error de red: " + t.getMessage());
            }
        });
    }
}