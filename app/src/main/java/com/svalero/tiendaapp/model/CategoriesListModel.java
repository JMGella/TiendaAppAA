package com.svalero.tiendaapp.model;

import com.svalero.tiendaapp.api.TiendaApi;
import com.svalero.tiendaapp.api.TiendaApiInterface;
import com.svalero.tiendaapp.contract.CategoryListContract;
import com.svalero.tiendaapp.domain.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesListModel implements CategoryListContract.Model {
    @Override
    public void loadCategories(OnLoadCategoriesListener listener) {

        TiendaApiInterface apiInterface = TiendaApi.buildInstance();

        Call<List<Category>> call = apiInterface.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
            listener.onLoadCategoriesSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
            listener.onLoadCategoriesFailure(t.getMessage());
            }
        });

    }

    @Override
    public void deleteCategory(Category category, OnDeleteCategoryListener listener) {
        TiendaApiInterface apiInterface = TiendaApi.buildInstance();
        Call<Void> call = apiInterface.deleteCategory(category.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteCategorySuccess(category);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteCategoryFailure(t.getMessage());
            }
        });

    }
}
