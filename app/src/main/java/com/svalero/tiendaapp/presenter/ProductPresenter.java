package com.svalero.tiendaapp.presenter;

import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.contract.ProductsContract;
import com.svalero.tiendaapp.domain.Product;
import com.svalero.tiendaapp.model.ProductModel;

import java.util.List;

public class ProductPresenter implements ProductsContract.Presenter, ProductsContract.Model.OnLoadProductsListener, ProductsContract.Model.OnDeleteProductListener {
    ProductsContract.View view;
    ProductsContract.Model model;

    public ProductPresenter(ProductsContract.View view) {
        this.view = view;
        model = new ProductModel();
    }

    @Override
    public void onDeleteProductSuccess() {
        view.showMessages("Producto eliminado correctamente");
        view.refreshList();
    }

    @Override
    public void onDeleteProductError(String error) {
        view.showMessages(error);
    }

    @Override
    public void onLoadProductsSuccess(List<Product> products) {
        view.showProducts(products);

    }

    @Override
    public void onLoadProductsError(String error) {
        view.showMessages(error);
    }

    @Override
    public void loadProducts() {
        model.loadProducts(this);
    }

    @Override
    public void deleteProduct(long productId) {
        model.deleteProduct(productId, this);
    }
}
