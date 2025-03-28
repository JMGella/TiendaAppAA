package com.svalero.tiendaapp.presenter;

import com.svalero.tiendaapp.contract.AddUserContract;
import com.svalero.tiendaapp.domain.User;
import com.svalero.tiendaapp.model.AddUserModel;

public class AddUserPresenter implements AddUserContract.Presenter, AddUserContract.Model.OnUserActionListener {
    private AddUserContract.View view;
    private AddUserContract.Model model;

    public AddUserPresenter(AddUserContract.View view) {
        this.view = view;
        this.model = new AddUserModel();
    }

    @Override
    public void saveUser(User user) {
        model.saveUser(user, this);



    }

    @Override
    public void getUserById(long userId) {
        model.getUserById(userId, this);
    }

    @Override
    public void updateLocation(double latitude, double longitude) {
        view.updateLocationFields(String.valueOf(latitude), String.valueOf(longitude));
    }

    @Override
    public void onUserLoaded(User user) {
//        view.showUser(user);
    }

    @Override
    public void onUserSaved(boolean success) {
        if (success)
            view.showSuccessMessage("Usuario guardado correctamente");
        else
            view.showErrorMessage("No se pudo guardar el usuario");

    }

    @Override
    public void onError(String error) {
        view.showErrorMessage(error);
    }
}
