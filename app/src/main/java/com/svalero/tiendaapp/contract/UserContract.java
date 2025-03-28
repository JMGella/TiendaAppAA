package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.domain.User;

import java.util.List;

public interface UserContract {

    interface Model {
        interface OnLoadUsersListener{
            void onLoadUsersSuccess(List<User> users);
            void onLoadUsersFailure(String error);
        }
        void getUsers(UserContract.Model.OnLoadUsersListener listener);

    }

    interface View {
        void listUsers(List<User> users);
        void showErrorMessage(String message);
        void showSuceessMessage(String message);

    }

    interface Presenter{
        void loadUsers();

    }
}
