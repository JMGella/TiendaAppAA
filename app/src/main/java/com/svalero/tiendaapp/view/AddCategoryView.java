package com.svalero.tiendaapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText etName;
    private EditText etDescription;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_category_view);
        setActivityTitle(getString(R.string.add_category));

        presenter = new AddCategoryPresenter(this);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> register(v));


    }

    public void register(View view) {

        String name = etName.getText().toString();
        String description = ((EditText) findViewById(R.id.etDescription)).getText().toString();
        Boolean active = true;
        String image ="noimage.jpg";

        if (name.isEmpty()) {
            etName.setError(getString(R.string.required_field));
            return;
        }

        if (description.isEmpty()) {
            etDescription.setError(getString(R.string.required_field));
            return;
        }

        Category category = new Category(name, description, active, image);
        presenter.addCategory(category);

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
}