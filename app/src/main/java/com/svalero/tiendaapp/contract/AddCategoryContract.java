package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.domain.Category;

public interface AddCategoryContract {

    interface Model {


        interface OnAddCategoryListener{
            void onAddCategoriesSuccess(Category category);
            void onAddCategoriesFailure(String error);
        }
        void addCategory(Category category, OnAddCategoryListener listener);

        interface OnGetCategoryByIdListener{
            void onGetCategoryByIdSuccess(Category category);
            void onGetCategoryByIdFailure(String error);
        }
        void getCategoryById(long id, OnGetCategoryByIdListener listener);

        interface OnUpdateCategoryListener{
            void onUpdateCategorySuccess(Category category);
            void onUpdateCategoryFailure(String error);
        }

        void updateCategory(long categoryId, Category category, OnUpdateCategoryListener listener);

    }

    interface View {

        void showErrorMessage(String message);
        void showSuceessMessage(String message);
        void goBack();
        void setCategory(Category category);

    }

    interface Presenter{
        void addCategory(Category category);
        void getCategoryById(long id);
        void updateCategory(long categoryId, Category category);
    }
}



