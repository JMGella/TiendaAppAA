package com.svalero.tiendaapp.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.contract.AddCategoryContract;
import com.svalero.tiendaapp.contract.CategoryListContract;
import com.svalero.tiendaapp.contract.UserContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.presenter.AddCategoryPresenter;
import com.svalero.tiendaapp.presenter.CategoriesListPresenter;
import com.svalero.tiendaapp.presenter.UserPresenter;
import com.svalero.tiendaapp.util.DateUtil;
import com.svalero.tiendaapp.view.AddCategoryView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList = new ArrayList<>();


    public CategoryAdapter(List <Category> categoryList) {
        this.categoryList = categoryList;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);

        holder.name.setText(category.getName());
        holder.description.setText(category.getDescription());
        holder.creationDate.setText(category.getCreationDate());
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddCategoryView.class);
            intent.putExtra("CATEGORY_ID", category.getId());
            v.getContext().startActivity(intent);
        });


        holder.btnEdit.setOnClickListener(
            v -> {
                Intent intent = new Intent(v.getContext(), AddCategoryView.class);
                intent.putExtra("CATEGORY_ID", category.getId());
                v.getContext().startActivity(intent);
            }
        );

        holder.btnDelete.setOnClickListener(
            v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Eliminar categoría");
                builder.setMessage("¿Estás seguro de que quieres eliminar la categoría?");
                builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                    CategoriesListPresenter presenter = new CategoriesListPresenter((CategoryListContract.View) v.getContext());
                    presenter.deleteCategory(category);
                });
                builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
                builder.show();


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
        private Button btnEdit;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
            creationDate = itemView.findViewById(R.id.item_creation_date);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public void setCategories(List<Category> categories) {
        this.categoryList = categories;
        notifyDataSetChanged();
    }
}
