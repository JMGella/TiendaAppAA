package com.svalero.tiendaapp.presenter;

import com.svalero.tiendaapp.contract.CategoryContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.model.CategoryModel;

import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter, CategoryContract.Model.OnLoadCategoriesListener{

    private CategoryContract.View view;
    private CategoryContract.Model model;

    public CategoryPresenter(CategoryContract.View view) {
        this.view = view;
        model = new CategoryModel();
    }

    @Override
    public void loadCategories() {
        model.loadCategories(this);
    }



    @Override
    public void onLoadCategoriesSuccess(List<Category> categories) {
        view.listCategories(categories);
    }

    @Override
    public void onLoadCategoriesFailure(String error) {
        view.showErrorMessage(error);
    }

    @Override
    public void deleteCategory(Category category) {
        model.deleteCategory(category,new CategoryContract.Model.OnDeleteCategoryListener() {
            @Override
            public void onDeleteCategorySuccess(Category category) {
                view.showSuceessMessage("Categoría " + category.getName() + " eliminada correctamente");
                view.cleanAndLoad();
            }

            @Override
            public void onDeleteCategoryFailure(String error) {
                view.showErrorMessage(error);
            }
        });
    }



}
