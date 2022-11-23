package com.pinterngoding.features.menu;

import com.pinterngoding.features.auth.AuthPresenter;
import com.pinterngoding.features.user.UserPresenter;
import com.pinterngoding.features.useractivation.UserActivationPresenter;
import com.pinterngoding.shared.utils.InputHelper;
import com.pinterngoding.shared.utils.StringHelper;

public class Menu {
    private final AuthPresenter authPresenter;
    private final UserPresenter userPresenter;
    private final UserActivationPresenter userActivationPresenter;

    public Menu(AuthPresenter authPresenter, UserPresenter userPresenter, UserActivationPresenter userActivationPresenter) {
        this.authPresenter = authPresenter;
        this.userPresenter = userPresenter;
        this.userActivationPresenter = userActivationPresenter;
    }

    public Boolean entryMenu() {
        boolean stop = false;
        StringHelper.printHeader("Yuk Ngoding!");
        System.out.println(
                "1. Login\n" +
                "2. Register\n" +
                "3. Request Account Activation\n" +
                "4. Activate Account\n" +
                "0. Exit"
        );
        StringHelper.printInputPrompt("Select Action");
        Integer choice = InputHelper.inputInt();
        switch (choice) {
            case 1:
                authPresenter.loginMenu();
                break;
            case 2:
                userPresenter.registerMenu();
                break;
            case 3:
                userActivationPresenter.requestActivationMenu();
                break;
            case 4:
                userActivationPresenter.activateUserMenu();
                break;
            case 0:
                stop = true;
                break;
            default:
                System.out.println("Action not available");
                break;
        }

        return stop;
    }

    public void studentMenu() {
        StringHelper.printHeader("Yuk Ngoding!");
        System.out.println(
                "1. View Courses\n" +
                "2. Register to Course\n" +
                "3. Manage Account\n" +
                "0. Logout"
        );

        StringHelper.printInputPrompt("Select Action");
        Integer choice = InputHelper.inputInt();
        switch (choice) {
            case 1:
                authPresenter.loginMenu();
                break;
            case 2:
                break;
            case 0:
                authPresenter.logoutMenu();
                break;
            default:
                System.out.println("Action not available");
                break;
        }
    }

    public void adminMenu() {
        StringHelper.printHeader("Yuk Ngoding!");
        System.out.println(
                "1. Manage Courses\n" +
                "2. Manage Course Registrations\n" +
                "3. Manage Users\n" +
                "0. Logout"
        );

        StringHelper.printInputPrompt("Select Action");
        Integer choice = InputHelper.inputInt();
        switch (choice) {
            case 1:
                authPresenter.loginMenu();
                break;
            case 2:
                break;
            case 3:
                break;
            case 0:
                authPresenter.logoutMenu();
                break;
            default:
                System.out.println("Action not available");
                break;
        }
    }
}
