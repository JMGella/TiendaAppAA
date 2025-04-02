package com.svalero.tiendaapp.view;

import android.content.Intent;
import android.os.Bundle;
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

    private AddProductContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_product);

        etProductName = findViewById(R.id.etProductName);
        etProductDescription = findViewById(R.id.etProductDescription);
        etProductPrice = findViewById(R.id.etProductPrice);
        btnSaveProduct = findViewById(R.id.btnSubmit);

        btnSaveProduct.setOnClickListener(view -> saveProduct());

    }

    private void saveProduct() {
        String name = etProductName.getText().toString();
        String description = etProductDescription.getText().toString();

        String priceString = etProductPrice.getText().toString();
        float price = Float.parseFloat(priceString);

        String creationDate = LocalDate.now().toString();

        long categoryId = getIntent().getLongExtra("CATEGORY_ID", 0);

        Product product = new Product(name, description, price, creationDate, true, "noimage.jpg", categoryId);

        presenter = new AddProductPresenter(this);
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