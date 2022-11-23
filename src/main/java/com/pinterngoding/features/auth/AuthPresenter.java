package com.pinterngoding.features.auth;

import com.pinterngoding.entity.User;
import com.pinterngoding.features.auth.interfaces.IAuthService;
import com.pinterngoding.shared.utils.InputHelper;
import com.pinterngoding.shared.utils.StringHelper;

public class AuthPresenter {
    IAuthService authService;

    public AuthPresenter(IAuthService authService) {
        this.authService = authService;
    }

    public void loginMenu() {
        User user = new User();
        StringHelper.printHeader("Login");
        StringHelper.printInputPrompt("E-mail");
        user.setEmail(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Password");
        user.setPassword(InputHelper.inputString(false));
        try {
            authService.login(user.getEmail(), user.getPassword());
            System.out.println("Login Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void logoutMenu() {
        StringHelper.printHeader("Logout");

        try {
            if (InputHelper.confirmation("Are you sure?")) {
                if (authService.logout()) {
                    System.out.println("Log out success");
                } else {
                    System.out.println("Log out fail");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
