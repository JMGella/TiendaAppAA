package com.svalero.tiendaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;


import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.contract.AddProductContract;

import com.svalero.tiendaapp.domain.Product;
import com.svalero.tiendaapp.presenter.AddProductPresenter;


import java.time.LocalDate;

public class AddProductView extends MainActivity implements AddProductContract.View {

    private EditText etProductName;
    private EditText etProductDescription;
    private EditText etProductPrice;
    private Button btnSaveProduct;
    private long categoryId;

    private AddProductContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etProductName = findViewById(R.id.etProductName);
        etProductDescription = findViewById(R.id.etProductDescription);
        etProductPrice = findViewById(R.id.etProductPrice);
        btnSaveProduct = findViewById(R.id.btnSubmit);
        presenter = new AddProductPresenter(this);

        if (getIntent().hasExtra("PRODUCT_ID")) {
            setActivityTitle(getString(R.string.edit_product));
            long productId = getIntent().getLongExtra("PRODUCT_ID", 0);
            presenter.getProductById(productId);
            btnSaveProduct.setOnClickListener(view -> {
                String name = etProductName.getText().toString();
                String description = etProductDescription.getText().toString();
                String priceString = etProductPrice.getText().toString();
                float price = Float.parseFloat(priceString);
                Product productToUpdate = new Product(name, description, price, LocalDate.now().toString(), true, "noimage.jpg", categoryId);

                presenter.updateProduct(categoryId, productId, productToUpdate);
            });


        } else {
            setActivityTitle(getString(R.string.add_product));
            btnSaveProduct.setOnClickListener(view -> saveProduct());
        }

    }

    private void saveProduct() {
        String name = etProductName.getText().toString();
        String description = etProductDescription.getText().toString();

        String priceString = etProductPrice.getText().toString();
        float price = Float.parseFloat(priceString);

        String creationDate = LocalDate.now().toString();

        if (getIntent().hasExtra("CATEGORY_ID")) {
            categoryId = getIntent().getLongExtra("CATEGORY_ID", 0);
        }

        Product product = new Product(name, description, price, creationDate, true, "noimage.jpg", categoryId);

        presenter.addProduct(categoryId, product);
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
    public void setProduct(Product product) {
        String name = product.getName();
        String description = product.getDescription();
        Float price = product.getPrice();
        categoryId = product.getCategoryId();
        Log.d("AddProductView", "Category ID: " + categoryId);

        etProductName.setText(name);
        etProductDescription.setText(description);
        etProductPrice.setText(String.valueOf(price));


    }

   @Override
    public void goBack() {
       Intent intent = new Intent(this, ProductsView.class);
         startActivity(intent);
    }
}