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
    public void getCategoryById(long id) {
        model.getCategoryById(id, new AddCategoryContract.Model.OnGetCategoryByIdListener() {
            @Override
            public void onGetCategoryByIdSuccess(Category category) {
                view.setCategory(category);

            }

            @Override
            public void onGetCategoryByIdFailure(String error) {
                view.showErrorMessage(error);
            }
        });

    }

    @Override
    public void updateCategory(long categoryId, Category category) {
        model.updateCategory(categoryId, category, new AddCategoryContract.Model.OnUpdateCategoryListener() {
            @Override
            public void onUpdateCategorySuccess(Category category) {
                view.showSuceessMessage("Categoría " + category.getName() + " actualizada correctamente");
                view.goBack();
            }

            @Override
            public void onUpdateCategoryFailure(String error) {
                view.showErrorMessage(error);
            }
        });
    }

    @Override
    public void onAddCategoriesSuccess(Category category) {
        view.showSuceessMessage("Categoría " + category.getName() + " añadida correctamente");
        view.goBack();
    }

    @Override
    public void onAddCategoriesFailure(String error) {

    }
}
