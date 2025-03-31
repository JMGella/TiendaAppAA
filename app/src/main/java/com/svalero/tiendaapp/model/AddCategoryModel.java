package com.svalero.tiendaapp.model;

import com.svalero.tiendaapp.api.TiendaApi;
import com.svalero.tiendaapp.api.TiendaApiInterface;
import com.svalero.tiendaapp.contract.AddCategoryContract;
import com.svalero.tiendaapp.domain.Category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryModel implements AddCategoryContract.Model {

    @Override
    public void addCategory(Category category, OnAddCategoryListener listener) {
        TiendaApiInterface apiInterface = TiendaApi.buildInstance();
        Call<Category> call = apiInterface.addCategory(category);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call call, Response response) {
                switch (response.code()) {
                    case 201:
                        listener.onAddCategoriesSuccess((Category) response.body());
                        break;
                    case 400:
                        listener.onAddCategoriesFailure("Error en la petición");
                        break;
                    case 500:
                        listener.onAddCategoriesFailure("Error en el servidor");
                        break;
                    default:
                        listener.onAddCategoriesFailure("Error desconocido");
                        break;
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.onAddCategoriesFailure(t.getMessage());
            }


        });

    }

    @Override
    public void getCategoryById(long id, OnGetCategoryByIdListener listener) {
        TiendaApiInterface apiInterface = TiendaApi.buildInstance();
        Call<Category> call = apiInterface.getCategoryById(id);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call call, Response response) {
                switch (response.code()) {
                    case 200:
                        listener.onGetCategoryByIdSuccess((Category) response.body());
                        break;
                    case 404:
                        listener.onGetCategoryByIdFailure("Categoría no encontrada");
                        break;
                    case 500:
                        listener.onGetCategoryByIdFailure("Error en el servidor");
                        break;
                    default:
                        listener.onGetCategoryByIdFailure("Error desconocido");
                        break;
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.onGetCategoryByIdFailure(t.getMessage());
            }

        });
    }


    @Override
    public void updateCategory(long categoryId, Category category, OnUpdateCategoryListener listener) {
        TiendaApiInterface apiInterface = TiendaApi.buildInstance();
        Call<Category> call = apiInterface.updateCategory(categoryId, category);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call call, Response response) {
                switch (response.code()) {
                    case 200:
                        listener.onUpdateCategorySuccess((Category) response.body());
                        break;
                    case 400:
                        listener.onUpdateCategoryFailure("Error en la petición");
                        break;
                    case 500:
                        listener.onUpdateCategoryFailure("Error en el servidor");
                        break;
                    default:
                        listener.onUpdateCategoryFailure("Error desconocido");
                        break;
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.onUpdateCategoryFailure(t.getMessage());
            }

        });
    }


}
