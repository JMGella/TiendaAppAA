package com.svalero.tiendaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.svalero.tiendaapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnUsers = findViewById(R.id.btnUsers);
        Button btnCategories = findViewById(R.id.btnCategories);
        Button btnProducts = findViewById(R.id.btnProducts);
        Button btnOrders = findViewById(R.id.btnOrders);

        btnUsers.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UsersActivity.class);
            startActivity(intent);
        });

        btnCategories.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(intent);
        });

        btnProducts.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
            startActivity(intent);
        });

        btnOrders.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
            startActivity(intent);
        });





    }

    protected void setActivityTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }





    }






