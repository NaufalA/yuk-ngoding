package com.pinterngoding.features.user.interfaces;

import com.pinterngoding.entity.User;
import com.pinterngoding.shared.interfaces.IService;

public interface IUserService extends IService<User> {
    Boolean register(User newUser);
    Boolean activate(String activationCode);

    User getByEmail(String email);
}
