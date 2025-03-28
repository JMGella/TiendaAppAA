package com.svalero.tiendaapp.model;

import com.svalero.tiendaapp.api.TiendaApi;
import com.svalero.tiendaapp.api.TiendaApiInterface;
import com.svalero.tiendaapp.contract.CategoryListContract;
import com.svalero.tiendaapp.contract.UserContract;
import com.svalero.tiendaapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserModel implements UserContract.Model {

    @Override
    public void getUsers(UserContract.Model.OnLoadUsersListener listener) {

        TiendaApiInterface apiInterface = TiendaApi.buildInstance();

        Call<List<User>> call = apiInterface.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listener.onLoadUsersSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onLoadUsersFailure(t.getMessage());
            }
        });

    }


}
