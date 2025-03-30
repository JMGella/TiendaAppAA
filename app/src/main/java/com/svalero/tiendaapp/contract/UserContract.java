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
        interface OnDeleteUserListener{
            void onDeleteUserSuccess();
            void onDeleteUserFailure(String error);
        }
        void deleteUser(long userId, UserContract.Model.OnDeleteUserListener listener);
    }

    interface View {
        void listUsers(List<User> users);
        void showErrorMessage(String message);
        void showSuceessMessage(String message);
        void goBack();


    }

    interface Presenter{
        void loadUsers();
        void deleteUser(long UserId);

    }
}
