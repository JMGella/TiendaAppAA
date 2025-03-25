package com.svalero.tiendaapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.widget.Toast;

import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.tiendaapp.contract.AddTicketContract;
import com.svalero.tiendaapp.db.AppDatabase;
import com.svalero.tiendaapp.db.Ticket;

public class AddTicketModel implements AddTicketContract.Model {

    @Override
    public void addTicket(Ticket ticket, Context context) {

        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "tickets-db.db")
                .allowMainThreadQueries().build();

        try {

            db.ticketDao().insert(ticket);

        } catch (SQLiteConstraintException sce) {
            Toast.makeText(context, "Error al insertar el ticket", Toast.LENGTH_SHORT).show();
        }
    }
}