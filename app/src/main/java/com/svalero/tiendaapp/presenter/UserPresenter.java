package com.svalero.tiendaapp.presenter;

import com.svalero.tiendaapp.contract.UserContract;
import com.svalero.tiendaapp.domain.User;
import com.svalero.tiendaapp.model.UserModel;

import java.util.List;

public class UserPresenter implements UserContract.Presenter {

    private UserContract.View view;
    private final UserContract.Model model;

    public UserPresenter(UserContract.View view) {
        this.view = view;
        model = new UserModel();
    }

    @Override
    public void loadUsers() {

        model.getUsers(new UserContract.Model.OnLoadUsersListener() {
            @Override
            public void onLoadUsersSuccess(List<User> users) {
                view.listUsers(users);
            }

            @Override
            public void onLoadUsersFailure(String error) {
                view.showErrorMessage(error);
            }
        });

    }

}
