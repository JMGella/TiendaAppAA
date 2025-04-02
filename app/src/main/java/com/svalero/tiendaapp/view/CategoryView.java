package com.svalero.tiendaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.adapter.CategoryAdapter;

import com.svalero.tiendaapp.adapter.CategoryAddProductAdapter;
import com.svalero.tiendaapp.contract.CategoryContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;


public class CategoryView extends MainActivity implements CategoryContract.View {

    private CategoryContract.Presenter presenter;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private CategoryAddProductAdapter categoryAddProductAdapter;
    private List<Category> categoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);

        presenter = new CategoryPresenter(this);
        categoryList = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.categories);
            setActivityTitle(getString(R.string.categories));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent().hasExtra("add_product")) {

            Toast.makeText(this, "Selecciona una categoría", Toast.LENGTH_SHORT).show();

            recyclerView = findViewById(R.id.recyclerViewCategories);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            categoryAddProductAdapter = new CategoryAddProductAdapter(categoryList);
            recyclerView.setAdapter(categoryAddProductAdapter);

        } else {

            recyclerView = findViewById(R.id.recyclerViewCategories);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            categoryAdapter = new CategoryAdapter(categoryList);
            recyclerView.setAdapter(categoryAdapter);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!getIntent().hasExtra("add_product")) {
            getMenuInflater().inflate(R.menu.menu_categories, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_new_category) {
            Intent intent = new Intent(this, AddCategoryView.class);
            startActivity(intent);
            return true;

        }
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void listCategories(List<Category> categories) {
        this.categoryList.addAll(categories);
        if (categoryAdapter != null) {
            categoryAdapter.notifyDataSetChanged();
        } else {
            categoryAddProductAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuceessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        cleanAndLoad();
    }


@Override
public void cleanAndLoad() {
        categoryList.clear();
        presenter.loadCategories();
    }
}