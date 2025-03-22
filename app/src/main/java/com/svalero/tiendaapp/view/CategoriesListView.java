package com.svalero.tiendaapp.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.adapter.CategoryAdapter;

import com.svalero.tiendaapp.contract.CategoryListContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.presenter.CategoriesListPresenter;

import java.util.ArrayList;
import java.util.List;


public class CategoriesListView extends MainActivity implements CategoryListContract.View {

    private CategoryListContract.Presenter presenter;

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<Category> categoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);

        presenter = new CategoriesListPresenter(this);

        categoryList = new ArrayList<>();

        getSupportActionBar().setTitle(R.string.categories);
        setActivityTitle(getString(R.string.categories));

        recyclerView = findViewById(R.id.recyclerViewCategories);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new CategoryAdapter(categoryList);
        recyclerView.setAdapter(adapter);



        presenter.loadCategories();



    }

    /*private void loadCategories() {
        Call<List<Category>> call = apiInterface.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body();
                    adapter.setCategories(categories);
                    adapter.notifyDataSetChanged();
                } else {
                    // Manejar error en la respuesta
                    Toast.makeText(CategoriesListView.this, "Error al cargar categorías", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Manejar fallo en la llamada
                Toast.makeText(CategoriesListView.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }*/


    @Override
    public void listCategories(List<Category> categories) {
        this.categoryList.addAll(categories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuceessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}