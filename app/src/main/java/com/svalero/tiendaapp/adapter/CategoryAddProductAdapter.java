package com.svalero.tiendaapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.view.AddProductView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAddProductAdapter extends RecyclerView.Adapter<CategoryAddProductAdapter.ViewHolder> {

    private List<Category> categoryList = new ArrayList<>();

    public CategoryAddProductAdapter(List <Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAddProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_add_product_item, parent, false);
        return new CategoryAddProductAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryAddProductAdapter.ViewHolder holder, int position) {
        Category category = categoryList.get(position);

        holder.name.setText(category.getName());
        holder.description.setText(category.getDescription());
        holder.creationDate.setText(category.getCreationDate());

        holder.btnSelect.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddProductView.class);
            intent.putExtra("CATEGORY_ID", category.getId());
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;
        private TextView creationDate;
        private Button btnSelect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
            creationDate = itemView.findViewById(R.id.item_creation_date);
            btnSelect = itemView.findViewById(R.id.btnSelect);
        }
    }

    public void setCategories(List<Category> categories) {
        this.categoryList = categories;
        notifyDataSetChanged();
    }
}
