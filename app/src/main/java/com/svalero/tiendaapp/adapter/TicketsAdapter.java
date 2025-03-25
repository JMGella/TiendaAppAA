package com.svalero.tiendaapp.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.db.AppDatabase;
import com.svalero.tiendaapp.db.Ticket;
import com.svalero.tiendaapp.view.AddTicketView;


import java.util.ArrayList;
import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.ViewHolder> {

    private List<Ticket> ticketList;

    public TicketsAdapter() {
        ticketList = new ArrayList<>();
    }

    public void setTickets(List<Ticket> ticketList) {
        this.ticketList = ticketList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        holder.tvName.setText(ticket.getName());
        holder.tvCategory.setText("Categoría: " + ticket.getCategory());
        holder.tvDescription.setText(ticket.getDescription());
        holder.tvCreationDate.setText("Creado: " + ticket.getCreationDate());
        holder.tvExpirationDate.setText("Expira: " + ticket.getExpirationDate());


        if (ticket.isImportant()) {
            holder.ivImportant.setVisibility(View.VISIBLE);
        } else {
            holder.ivImportant.setVisibility(View.GONE);
        }


        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddTicketView.class);
            intent.putExtra("TICKET_ID", ticket.getId());
            v.getContext().startActivity(intent);
        });


        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(R.string.delete_ticket);
            builder.setMessage(R.string.delete_ticket_dialog);
            builder.setPositiveButton(R.string.yes, (dialog, which) -> {

                new Thread(() -> {
                    AppDatabase db = AppDatabase.getInstance(v.getContext());
                    db.ticketDao().deleteById(ticket.getId());


                    ((android.app.Activity)v.getContext()).runOnUiThread(() -> {
                        int position1 = ticketList.indexOf(ticket);
                        if (position1 != -1) {
                            ticketList.remove(position1);
                            notifyItemRemoved(position1);
                            Toast.makeText(v.getContext(), R.string.deleted, Toast.LENGTH_SHORT).show();
                        }
                    });
                }).start();
            });
            builder.setNegativeButton("No", null);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvCategory;
        TextView tvDescription;
        TextView tvCreationDate;
        TextView tvExpirationDate;
        ImageView ivImportant;
        Button btnEdit;
        Button btnDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreationDate = itemView.findViewById(R.id.tvCreationDate);
            tvExpirationDate = itemView.findViewById(R.id.tvExpirationDate);
            ivImportant = itemView.findViewById(R.id.ivImportant);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
