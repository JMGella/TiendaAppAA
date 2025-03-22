package com.svalero.tiendaapp.presenter;

import com.svalero.tiendaapp.contract.CategoryListContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.model.CategoriesListModel;

import java.util.List;

public class CategoriesListPresenter implements CategoryListContract.Presenter, CategoryListContract.Model.OnLoadCategoriesListener {

    private CategoryListContract.View view;
    private CategoryListContract.Model model;

    public CategoriesListPresenter(CategoryListContract.View view) {
        this.view = view;
        model = new CategoriesListModel();
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
}
