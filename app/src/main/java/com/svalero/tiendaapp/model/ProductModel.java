package com.svalero.tiendaapp.model;

import com.svalero.tiendaapp.api.TiendaApi;
import com.svalero.tiendaapp.api.TiendaApiInterface;
import com.svalero.tiendaapp.contract.ProductsContract;
import com.svalero.tiendaapp.domain.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductModel implements ProductsContract.Model {


    @Override
    public void loadProducts(OnLoadProductsListener listener) {
        TiendaApiInterface apiInterface = TiendaApi.buildInstance();

        Call<List<Product>> call = apiInterface.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listener.onLoadProductsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                listener.onLoadProductsError(t.getMessage());
            }
        });
    }


    @Override
    public void deleteProduct(long productId, OnDeleteProductListener listener) {
        TiendaApiInterface apiInterface = TiendaApi.buildInstance();
        Call<Void> call = apiInterface.deleteProductById(productId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteProductSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteProductError(t.getMessage());
            }
        });
    }


}
