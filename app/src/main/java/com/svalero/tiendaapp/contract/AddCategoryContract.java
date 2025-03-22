package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.domain.Category;

import java.util.List;

public interface AddCategoryContract {

    interface Model {
        interface OnAddCategoryListener{
            void onAddCategoriesSuccess(Category category);
            void onAddCategoriesFailure(String error);
        }
        void addCategory(Category category, OnAddCategoryListener listener);

    }

    interface View {

        void showErrorMessage(String message);
        void showSuceessMessage(String message);

    }

    interface Presenter{
        void addCategory(Category category);
    }
}



