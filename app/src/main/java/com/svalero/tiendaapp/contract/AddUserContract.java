package com.svalero.tiendaapp.contract;

import com.svalero.tiendaapp.domain.User;

public interface AddUserContract {

    interface Model {
        void getUserById(long userId, OnUserActionListener listener);
        void saveUser(User user, OnUserActionListener listener);

        interface OnUserActionListener {
            void onUserLoaded(User user);
            void onUserSaved(boolean success);
            void onError(String error);
        }
    }

    interface Presenter {
        void saveUser(User user);
        void getUserById(long userId);
        void updateLocation(double latitude, double longitude);
    }

    interface View {
        void showSuccessMessage(String message);
        void showErrorMessage(String message);
        void updateLocationFields(String latitude, String longitude);
        void goBack();
    }
}
