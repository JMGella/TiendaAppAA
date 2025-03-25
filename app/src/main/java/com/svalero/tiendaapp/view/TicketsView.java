package com.svalero.tiendaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.adapter.TicketsAdapter;
import com.svalero.tiendaapp.db.AppDatabase;
import com.svalero.tiendaapp.db.Ticket;

import java.util.List;

public class TicketsView extends MainActivity {

    private RecyclerView recyclerView;
    private TicketsAdapter ticketsAdapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_view);


        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getString(R.string.tickets));
        }

        recyclerView = findViewById(R.id.recyclerViewTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ticketsAdapter = new TicketsAdapter();
        recyclerView.setAdapter(ticketsAdapter);


        db = AppDatabase.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTickets();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tickets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, AddTicketView.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadTickets() {
        new Thread(() -> {
            List<Ticket> tickets = db.ticketDao().getAll();
            runOnUiThread(() -> {
                ticketsAdapter.setTickets(tickets);
                if (tickets.isEmpty()) {
                    Toast.makeText(this, getString(R.string.notickets), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}
