package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.domain.Category;

import java.util.List;

public interface CategoryContract {

    interface Model {
        interface OnLoadCategoriesListener{
            void onLoadCategoriesSuccess(List<Category> categories);
            void onLoadCategoriesFailure(String error);
        }
        void loadCategories(OnLoadCategoriesListener listener);

        interface OnDeleteCategoryListener{
            void onDeleteCategorySuccess(Category category);
            void onDeleteCategoryFailure(String error);
        }

        void deleteCategory(Category category, OnDeleteCategoryListener listener);

    }

    interface View {
        void listCategories(List<Category> categories);
        void showErrorMessage(String message);
        void showSuceessMessage(String message);
        void cleanAndLoad();
    }

    interface Presenter{
        void loadCategories();
        void deleteCategory(Category category);
    }
}
