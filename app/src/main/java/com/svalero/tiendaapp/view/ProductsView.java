package com.svalero.tiendaapp.view;

import android.os.Bundle;
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
        }

        presenter = new ProductPresenter( this);
        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);



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