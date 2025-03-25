package com.svalero.tiendaapp.presenter;

import android.content.Context;

import com.svalero.tiendaapp.contract.TicketsContract;
import com.svalero.tiendaapp.db.AppDatabase;
import com.svalero.tiendaapp.db.Ticket;
import com.svalero.tiendaapp.model.TicketsModel;

import java.util.List;

public class TicketsPresenter implements TicketsContract.Presenter {
    private TicketsModel model;
    private TicketsContract.View view;

    public TicketsPresenter(TicketsContract.View view) {
        this.view = view;
        model = new TicketsModel((Context) view);
    }

    @Override
    public void loadTickets() {
        List <Ticket> tickets = model.getTickets();
        view.showTickets(tickets);
    }

    @Override
    public void addTicket(Ticket ticket) {
        model.addTicket(ticket);
        // Actualiza la vista con el nuevo ticket
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        model.deleteTicket(ticket.getId());
        // Actualiza la vista eliminando el ticket
    }


}


