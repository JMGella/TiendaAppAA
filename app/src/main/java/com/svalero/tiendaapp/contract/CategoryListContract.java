package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.domain.Category;

import java.util.List;

public interface CategoryListContract {

    interface Model {
        interface OnLoadCategoriesListener{
            void onLoadCategoriesSuccess(List<Category> categories);
            void onLoadCategoriesFailure(String error);
        }
        void loadCategories(OnLoadCategoriesListener listener);

    }

    interface View {
        void listCategories(List<Category> categories);
        void showErrorMessage(String message);
        void showSuceessMessage(String message);

    }

    interface Presenter{
        void loadCategories();
    }
}
