package com.svalero.tiendaapp.model;


import android.content.Context;


import androidx.room.Room;


import com.svalero.tiendaapp.contract.TicketsContract;
import com.svalero.tiendaapp.db.AppDatabase;
import com.svalero.tiendaapp.db.Ticket;

import java.util.List;

public class TicketsModel  implements TicketsContract.Model {

    private AppDatabase db;
    private List<Ticket> tickets;

    public TicketsModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "tickets-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public List<Ticket> getTickets( ) {
        tickets.addAll(db.ticketDao().getAll());
        return tickets;
    }

    @Override
    public void addTicket(Ticket ticket) {
        db.ticketDao().insert(ticket);
    }

    @Override
    public void deleteTicket(int id) {
        db.ticketDao().deleteById(id);
    }


}
