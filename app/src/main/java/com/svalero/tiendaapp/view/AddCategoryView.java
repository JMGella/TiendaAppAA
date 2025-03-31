package com.svalero.tiendaapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.contract.AddCategoryContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.presenter.AddCategoryPresenter;

import java.time.LocalDate;

public class AddCategoryView extends MainActivity implements AddCategoryContract.View {

    private AddCategoryPresenter presenter;
    private EditText etName;
    private EditText etDescription;
    private Button btnSubmit;
    private long categoryId;
    private String name;
    private String description;
    private String creationDate;
    private Boolean active;
    private String image;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_category_view);

        presenter = new AddCategoryPresenter(this);

        if(getIntent().hasExtra("CATEGORY_ID")) {
            setActivityTitle(getString(R.string.edit_category));
            categoryId = getIntent().getLongExtra("CATEGORY_ID", 0);
            presenter.getCategoryById(categoryId);

            etName = findViewById(R.id.etName);
            etDescription = findViewById(R.id.etDescription);

            btnSubmit = findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(v -> update(v));




        }
        else {

            setActivityTitle(getString(R.string.add_category));


            etName = findViewById(R.id.etName);
            etDescription = findViewById(R.id.etDescription);
            btnSubmit = findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(v -> register(v));

        }
    }

    public void register(View view) {

         name = etName.getText().toString();
         description = ((EditText) findViewById(R.id.etDescription)).getText().toString();
         creationDate = String.valueOf(LocalDate.now());
         active = true;
         image ="noimage.jpg";


        if (name.isEmpty()) {
            etName.setError(getString(R.string.required_field));
            return;
        }

        if (description.isEmpty()) {
            etDescription.setError(getString(R.string.required_field));
            return;
        }

        category = new Category(name, description, creationDate, active, image);
        presenter.addCategory(category);

    }

    private void update(View view){
        String nametoUpdate = etName.getText().toString();
        String descriptiontoUpdate = etDescription.getText().toString();

        if (nametoUpdate.isEmpty()) {
            etName.setError(getString(R.string.required_field));
            return;
        }

        if (descriptiontoUpdate.isEmpty()) {
            etDescription.setError(getString(R.string.required_field));
            return;
        }

        category.setName(nametoUpdate);
        category.setDescription(descriptiontoUpdate);


        presenter.updateCategory(categoryId, category);
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
    public void setCategory(Category categoryIn) {
        name = categoryIn.getName();
        description = categoryIn.getDescription();
        creationDate = categoryIn.getCreationDate();
        active = categoryIn.getActive();
        image = categoryIn.getImage();
        etName.setText(name);
        etDescription.setText(description);
        category = new Category(name, description, creationDate, active, image);

    }
}