package com.svalero.tiendaapp.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.db.AppDatabase;
import com.svalero.tiendaapp.db.Ticket;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTicketView extends MainActivity {

    private EditText etName;
    private EditText etCategory;
    private EditText etDescription;
    private EditText etCreationDate;
    private EditText etExpirationDate;
    private SwitchMaterial swImportant;
    private Button btnSubmit;
    private AppDatabase db;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private int ticketId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket_view);

        db = AppDatabase.getInstance(this);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        etName = findViewById(R.id.etName);
        etCategory = findViewById(R.id.etCategory);
        etDescription = findViewById(R.id.etDescription);
        etCreationDate = findViewById(R.id.etCreationDate);
        etExpirationDate = findViewById(R.id.etExpirationDate);
        swImportant = findViewById(R.id.swImportant);
        btnSubmit = findViewById(R.id.btnSubmit);

        if (getIntent().hasExtra("TICKET_ID")) {
            ticketId = getIntent().getIntExtra("TICKET_ID", -1);
        }

        if (ticketId>0) {
            getSupportActionBar().setTitle(getString(R.string.edit_ticket));
            new Thread(() -> {
                Ticket ticket = db.ticketDao().getById(ticketId);
                runOnUiThread(() -> {
                    etName.setText(ticket.getName());
                    etCategory.setText(ticket.getCategory());
                    etDescription.setText(ticket.getDescription());
                    etCreationDate.setText(ticket.getCreationDate());
                    etExpirationDate.setText(ticket.getExpirationDate());
                    swImportant.setChecked(ticket.isImportant());
                });
            }).start();

        } else {
            getSupportActionBar().setTitle(getString(R.string.add_ticket));
            calendar = Calendar.getInstance();
            dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            etCreationDate.setText(dateFormat.format(calendar.getTime()));
            etExpirationDate.setText("31/12/2025");

        }





        btnSubmit.setOnClickListener(v -> register(v));
    }


    public void register(View view) {

        String name = etName.getText().toString();
        String category = etCategory.getText().toString();
        String description = etDescription.getText().toString();
        String creationDate = etCreationDate.getText().toString();
        String expirationDate = etExpirationDate.getText().toString();
        boolean important = swImportant.isChecked();

        if (name.isEmpty()) {
            etName.setError(getString(R.string.required_field));
            return;
        }

        if (category.isEmpty()) {
            etCategory.setError(getString(R.string.required_field));
            return;
        }

        if (description.isEmpty()) {
            etDescription.setError(getString(R.string.required_field));
            return;
        }

        if (creationDate.isEmpty()) {
            etCreationDate.setError(getString(R.string.required_field));
            return;
        }

        if (expirationDate.isEmpty()) {
            etExpirationDate.setError(getString(R.string.required_field));
            return;
        }


        Ticket ticket = new Ticket(name, category, description, creationDate, expirationDate, important);

        if (ticketId > 0) {
            ticket.setId(ticketId);
            new Thread(() -> {
                db.ticketDao().update(ticket);
                runOnUiThread(() -> {
                    Toast.makeText(AddTicketView.this, "Ticket actualizado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }).start();

        } else {

            new Thread(() -> {
                db.ticketDao().insert(ticket);
                runOnUiThread(() -> {
                    Toast.makeText(AddTicketView.this, "Ticket guardado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // Volver a la actividad anterior
                });
            }).start();

        }
    }
}

