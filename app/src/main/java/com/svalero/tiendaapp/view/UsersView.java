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
import com.svalero.tiendaapp.adapter.UserAdapter;
import com.svalero.tiendaapp.contract.UserContract;
import com.svalero.tiendaapp.domain.User;
import com.svalero.tiendaapp.presenter.UserPresenter;

import java.util.List;

public class UsersView extends MainActivity implements UserContract.View {

    private RecyclerView recyclerView;
    private UserAdapter usersAdapter;
    private UserContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_users);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.users));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setActivityTitle(getString(R.string.users));

        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter = new UserAdapter();
        recyclerView.setAdapter(usersAdapter);
        presenter = new UserPresenter(this);
        presenter.loadUsers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_user) {
            Intent intent = new Intent(this, AddUserView.class);
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
    public void listUsers(List<User> users) {
        usersAdapter.setUsers(users);


    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showSuceessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadUsers();
    }



}