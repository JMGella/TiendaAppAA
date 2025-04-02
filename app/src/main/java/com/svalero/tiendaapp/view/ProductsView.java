package com.svalero.tiendaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.adapter.ProductAdapter;
import com.svalero.tiendaapp.contract.ProductsContract;
import com.svalero.tiendaapp.domain.Product;
import com.svalero.tiendaapp.presenter.ProductPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProductsView extends MainActivity implements ProductsContract.View {

    private ProductsContract.Presenter presenter;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_products);


        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.products);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new ProductPresenter( this);
        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_categories) {
            Intent intent = new Intent(this, CategoryView.class);
            intent.putExtra("add_product", true);
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
    public void showProducts(List<Product> products) {
        productList.clear();
        productList.addAll(products);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessages(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void refreshList() {
        presenter.loadProducts();

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }
}