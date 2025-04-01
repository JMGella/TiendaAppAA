package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.domain.Product;

import java.util.List;

public interface ProductsContract {

    interface Model{

        void loadProducts(OnLoadProductsListener listener);

        void deleteProduct(long productId, OnDeleteProductListener listener);

        interface OnLoadProductsListener{
            void onLoadProductsSuccess(List<Product> products);
            void onLoadProductsError(String error);
        }
        interface OnDeleteProductListener{
            void onDeleteProductSuccess();
            void onDeleteProductError(String error);
        }


    }

    interface Presenter{
        void loadProducts();
        void deleteProduct(long productId);


    }

    interface View{
        void showProducts(List<Product> products);
        void showMessages(String message);
        void refreshList();
    }
}
