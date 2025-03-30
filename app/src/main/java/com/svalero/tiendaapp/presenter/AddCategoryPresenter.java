package com.svalero.tiendaapp.presenter;

import com.svalero.tiendaapp.contract.AddCategoryContract;
import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.model.AddCategoryModel;

public class AddCategoryPresenter implements AddCategoryContract.Presenter, AddCategoryContract.Model.OnAddCategoryListener {

    private AddCategoryContract.View view;
    private AddCategoryContract.Model model;

    public AddCategoryPresenter(AddCategoryContract.View view) {
        this.view = view;
        model = new AddCategoryModel();
    }

    @Override
    public void addCategory(Category category) {
        model.addCategory(category,this);

    }

    @Override
    public void onAddCategoriesSuccess(Category category) {
        view.showSuceessMessage("Categoría " + category.getName() + " añadida correctamente");
        view.goBack();
    }

    @Override
    public void onAddCategoriesFailure(String error) {
        view.showErrorMessage(error);

    }
}
