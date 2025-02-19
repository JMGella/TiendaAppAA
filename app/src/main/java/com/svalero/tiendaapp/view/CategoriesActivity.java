package com.svalero.tiendaapp.view;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.adapter.CategoryAdapter;
import com.svalero.tiendaapp.api.TiendaApi;
import com.svalero.tiendaapp.api.TiendaApiInterface;
import com.svalero.tiendaapp.domain.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends MainActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private TiendaApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setTitle(R.string.categories);

        recyclerView = findViewById(R.id.recyclerViewCategories);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);

        apiInterface = TiendaApi.buildInstance();
        loadCategories();




        setActivityTitle(getString(R.string.categories));
    }

    private void loadCategories() {
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
                    Toast.makeText(CategoriesActivity.this, "Error al cargar categorías", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Manejar fallo en la llamada
                Toast.makeText(CategoriesActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }



}