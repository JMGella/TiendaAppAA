package com.svalero.tiendaapp.contract;

import android.content.Context;

import com.svalero.tiendaapp.db.Ticket;

public interface AddTicketContract {

    interface Model {

        void addTicket(Ticket ticket,  Context context);

    }

    interface View {

        void showErrorMessage(String message);
        void showSuceessMessage(String message);

    }

    interface Presenter{
        void addTicket(Ticket ticket, Context context);
    }
}
