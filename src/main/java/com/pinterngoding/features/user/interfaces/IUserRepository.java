package com.pinterngoding.features.user.interfaces;

import com.pinterngoding.entity.User;
import com.pinterngoding.shared.interfaces.IRepository;

public interface IUserRepository extends IRepository<User> {
    User findByEmail(String email);
}
