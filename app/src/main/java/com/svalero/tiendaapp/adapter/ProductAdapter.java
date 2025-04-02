package com.svalero.tiendaapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.api.TiendaApi;
import com.svalero.tiendaapp.api.TiendaApiInterface;
import com.svalero.tiendaapp.contract.AddCategoryContract;
import com.svalero.tiendaapp.contract.CategoryContract;
import com.svalero.tiendaapp.contract.ProductsContract;
import com.svalero.tiendaapp.domain.Product;
import com.svalero.tiendaapp.presenter.AddCategoryPresenter;
import com.svalero.tiendaapp.presenter.CategoryPresenter;
import com.svalero.tiendaapp.presenter.ProductPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;




    public ProductAdapter(List<Product> productList) {
        this.productList = productList;

    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.tvProductName.setText(product.getName());
        holder. tvCategory.setText(String.valueOf(product.getCategoryId()));
        holder.tvProductDescription.setText(product.getDescription());
        holder.tvProductPrice.setText(String.format(Locale.getDefault(), "%.2f €", product.getPrice()));
        holder.tvProductDate.setText(product.getCreationDate());

        holder.btnDeleteProduct.setOnClickListener(
                v -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle(R.string.delete_product);
                    builder.setMessage(R.string.delete_product_dialog);
                    builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                        ProductPresenter productPresenter = new ProductPresenter((ProductsContract.View) v.getContext());
                        productPresenter.deleteProduct(product.getId());
                    });
                    builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
                    builder.show();


                });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName;
        TextView tvCategory;
        TextView tvProductDescription;
        TextView tvProductPrice;
        TextView tvProductDate;
        Button btnEditProduct;
        Button btnDeleteProduct;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductDate = itemView.findViewById(R.id.tvProductDate);
            btnEditProduct = itemView.findViewById(R.id.btnEdit);
            btnDeleteProduct = itemView.findViewById(R.id.btnDelete);

        }
    }

}
