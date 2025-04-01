package com.svalero.tiendaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.svalero.tiendaapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.app_name);
            setActivityTitle(getString(R.string.app_name));
        }

        Button btnUsers = findViewById(R.id.btnUsers);
        Button btnCategories = findViewById(R.id.btnCategories);
        Button btnProducts = findViewById(R.id.btnProducts);
        Button btnTickets = findViewById(R.id.btnTickets);




        btnUsers.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UsersView.class);
            startActivity(intent);
        });

        btnCategories.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoryView.class);
            startActivity(intent);
        });

        btnProducts.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductsView.class);
            startActivity(intent);
        });


        btnTickets.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TicketsView.class);
            startActivity(intent);
        });





    }

    protected void setActivityTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }





    }






