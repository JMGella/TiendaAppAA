package com.svalero.tiendaapp.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.domain.User;
import com.svalero.tiendaapp.util.DateUtil;
import com.svalero.tiendaapp.view.AddUserView;
import com.svalero.tiendaapp.view.UserLocationView;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;
    private final DateUtil dateUtil;



    public UserAdapter() {
        this.userList = new ArrayList<>();
        this.dateUtil = new DateUtil();
    }



    public void setUsers(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = userList.get(position);

        holder.tvName.setText(user.getName());
        holder.tvEmail.setText(user.getEmail());
        holder.tvPhone.setText(user.getPhone());
        holder.tvAddress.setText(user.getAddress());
        holder.tvBirthDate.setText(user.getBirthDate());
       holder.tvCreationDate.setText(user.getCreationDate());



        if (user.getActive() != null && user.getActive()) {
            holder.ivStatus.setImageResource(R.drawable.ic_status_active);
        } else {
            holder.ivStatus.setImageResource(R.drawable.ic_status_inactive);
        }


        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddUserView.class);
            intent.putExtra("USER_ID", user.getId());  //TODO edit user
            v.getContext().startActivity(intent);
        });


        holder.btnLocation.setOnClickListener(v -> {

            if (user.getLatitude() == null || user.getLongitude() == null ||
                    user.getLatitude().isEmpty() || user.getLongitude().isEmpty()) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(R.string.location_not_available);
                builder.setMessage(R.string.location_not_available_message);
                builder.setPositiveButton(R.string.ok, null);
                builder.show();
                return;
            }

            Intent intent = new Intent(v.getContext(), UserLocationView.class);
            intent.putExtra("USER_NAME", user.getName());
            intent.putExtra("USER_LATITUDE", user.getLatitude());
            intent.putExtra("USER_LONGITUDE", user.getLongitude());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;
        TextView tvPhone;
        TextView tvAddress;
        TextView tvBirthDate;
        TextView tvCreationDate;
        ImageView ivStatus;
        Button btnEdit;
        Button btnLocation;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvBirthDate = itemView.findViewById(R.id.tvBirthDate);
            tvCreationDate = itemView.findViewById(R.id.tvCreationDate);
            ivStatus = itemView.findViewById(R.id.ivStatus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnLocation = itemView.findViewById(R.id.btnLocation);
        }
    }
}

