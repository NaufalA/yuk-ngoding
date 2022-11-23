package com.pinterngoding.features.auth.interfaces;

import com.pinterngoding.entity.User;

public interface IAuthService {
    User login(String email, String password);
    Boolean logout();
}
