package com.svalero.tiendaapp.presenter;

import android.content.Context;

import com.svalero.tiendaapp.contract.AddTicketContract;
import com.svalero.tiendaapp.db.Ticket;
import com.svalero.tiendaapp.model.AddTicketModel;

public class AddTicketPresenter implements AddTicketContract.Presenter {
    private AddTicketContract.View view;
    private AddTicketContract.Model model;


    public AddTicketPresenter(AddTicketContract.View view) {
        this.view = view;
        model = new AddTicketModel();
    }

    @Override
    public void addTicket(Ticket ticket, Context context) {

       model.addTicket(ticket, context);

    }
}
