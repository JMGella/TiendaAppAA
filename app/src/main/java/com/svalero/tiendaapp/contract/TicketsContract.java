package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.db.AppDatabase;
import com.svalero.tiendaapp.db.Ticket;

import java.util.List;

public interface TicketsContract {


    interface Model {
        List<Ticket> getTickets();

        void addTicket(Ticket ticket);

        void deleteTicket(int id);


    }

    interface View {
        void showTickets(List<Ticket> ticket);
        void showErrorMessage(String message);
        void showSuceessMessage(String message);

    }

    interface Presenter{
        void loadTickets();
        void addTicket(Ticket ticket);
        void deleteTicket(Ticket ticket);
    }
}
