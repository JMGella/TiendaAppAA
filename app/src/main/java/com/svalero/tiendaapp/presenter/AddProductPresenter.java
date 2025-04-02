package com.svalero.tiendaapp.presenter;

import com.svalero.tiendaapp.contract.AddProductContract;
import com.svalero.tiendaapp.domain.Product;
import com.svalero.tiendaapp.model.AddProductModel;

public class AddProductPresenter implements AddProductContract.Presenter {
    private AddProductContract.Model model;
    private AddProductContract.View view;

    public AddProductPresenter(AddProductContract.View view){
        this.view = view;
        model = new AddProductModel();
    }

    @Override
    public void addProduct(long categoryId, Product product) {
        model.addProduct(categoryId, product, new AddProductContract.Model.OnAddProductListener() {
            @Override
            public void onAddProductSuccess(Product product) {
                view.showSuceessMessage("Producto añadido correctamente");
                view.goBack();
            }

            @Override
            public void onAddProductFailure(String error) {
                view.showErrorMessage(error);
            }
        });
    }

    @Override
    public void getProductById(long id) {
        model.getProductById(id, new AddProductContract.Model.OnGetProductByIdListener() {
            @Override
            public void onGetProductByIdSuccess(Product product) {
                view.setProduct(product);
            }

            @Override
            public void onGetProductByIdFailure(String error) {
                view.showErrorMessage(error);
            }
        });
    }

    @Override
    public void updateProduct(long categoryId, long productId, Product product) {
        model.updateProduct(categoryId, productId, product, new AddProductContract.Model.OnUpdateProductListener() {
            @Override
            public void onUpdateProductSuccess(Product product) {
                view.showSuceessMessage("Producto actualizado correctamente");
                view.goBack();
            }

            @Override
            public void onUpdateProductFailure(String error) {
                view.showErrorMessage(error);
            }
        });
    }

}
