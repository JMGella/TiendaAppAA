package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.domain.Product;

public interface AddProductContract {
    public interface Model {
        interface OnAddProductListener{
            void onAddProductSuccess(Product product);
            void onAddProductFailure(String error);
        }
        void addProduct(long categoryId, Product product, OnAddProductListener listener);

        interface OnGetProductByIdListener{
            void onGetProductByIdSuccess(Product product);
            void onGetProductByIdFailure(String error);
        }
        void getProductById(long id, OnGetProductByIdListener listener);

        interface OnUpdateProductListener{
            void onUpdateProductSuccess(Product product);
            void onUpdateProductFailure(String error);
        }

        void    updateProduct(long categoryId, long productId, Product product, OnUpdateProductListener listener);
    }

    public interface View {
        void showErrorMessage(String message);
        void showSuceessMessage(String message);
        void setProduct(Product product);
        void goBack();
    }

    public interface Presenter{
        void addProduct(long categoryId, Product product);
        void getProductById(long id);
        void updateProduct(long categoryId, long productId, Product product);
    }
}
