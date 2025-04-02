package com.svalero.tiendaapp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.svalero.tiendaapp.api.TiendaApi;
import com.svalero.tiendaapp.api.TiendaApiInterface;
import com.svalero.tiendaapp.contract.AddProductContract;
import com.svalero.tiendaapp.domain.Product;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductModel implements AddProductContract.Model {
    private TiendaApiInterface apiInterface;

    @Override
    public void addProduct(long categoryId, Product product, OnAddProductListener listener) {
        apiInterface = TiendaApi.buildInstance();
        apiInterface.addProduct(categoryId,product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {


                switch (response.code()) {
                    case 201:
                        listener.onAddProductSuccess(response.body());
                        break;
                    case 400:
                        listener.onAddProductFailure("Error en la petición");
                        break;
                    case 500:
                        listener.onAddProductFailure("Error en el servidor");
                        break;
                    default:
                        listener.onAddProductFailure("Error desconocido");
                        break;
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {


                listener.onAddProductFailure(t.getMessage());
            }
        });

    }





    @Override
    public void getProductById(long id, OnGetProductByIdListener listener) {
        apiInterface = TiendaApi.buildInstance();
        apiInterface.getProductById(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                switch (response.code()) {
                    case 200:
                        listener.onGetProductByIdSuccess(response.body());
                        break;
                    case 404:
                        listener.onGetProductByIdFailure("Producto no encontrado");
                        break;
                    default:
                        listener.onGetProductByIdFailure("Error desconocido");
                        break;
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                listener.onGetProductByIdFailure(t.getMessage());
            }
        });

    }

    @Override
    public void updateProduct(long categoryId, long productId, Product product, OnUpdateProductListener listener) {
        apiInterface = TiendaApi.buildInstance();
        apiInterface.updateProduct(categoryId,productId, product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                switch (response.code()) {
                    case 200:
                        listener.onUpdateProductSuccess(response.body());
                        break;
                    case 400:
                        listener.onUpdateProductFailure("Error en la petición");
                        break;
                    case 500:
                        listener.onUpdateProductFailure("Error en el servidor");
                        break;
                    default:
                        listener.onUpdateProductFailure("Error desconocido");
                        break;
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Product> call, Throwable t) {
                listener.onUpdateProductFailure(t.getMessage());
            }
        });

    }
}
