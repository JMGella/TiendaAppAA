package com.svalero.tiendaapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.contract.AddCategoryContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.presenter.AddCategoryPresenter;

public class AddCategoryView extends MainActivity implements AddCategoryContract.View {

    private AddCategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_category_view);
        setActivityTitle(getString(R.string.add_category));

        presenter = new AddCategoryPresenter(this);




    }

    public void register(View view) {

        String name = ((EditText) findViewById(R.id.etName)).getText().toString();
        String description = ((EditText) findViewById(R.id.etDescription)).getText().toString();
        Boolean active = true;



    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(findViewById(R.id.btnSubmit), message, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void showSuceessMessage(String message) {
        Snackbar.make(findViewById(R.id.btnSubmit), message, Snackbar.LENGTH_LONG).show();

    }
}