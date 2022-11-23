package com.pinterngoding.features.auth;

import com.pinterngoding.entity.User;
import com.pinterngoding.features.auth.interfaces.IAuthService;
import com.pinterngoding.features.user.interfaces.IUserService;

public class AuthService implements IAuthService {
    private final IUserService userService;

    public AuthService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public User login(String email, String password) {
        User user = userService.getByEmail(email);
        if (user == null) {
            throw new RuntimeException("Wrong Email or Password");
        }
        if(!password.equals(user.getPassword())) {
            throw new RuntimeException("Wrong Email or Password");
        }
        if (!user.getActive()) {
            throw new RuntimeException("User Status Inactive");
        }

        return user;
    }

    @Override
    public Boolean logout() {
        return true;
    }
}
