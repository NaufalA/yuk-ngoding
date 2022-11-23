package com.pinterngoding.features.user;

import com.pinterngoding.entity.User;
import com.pinterngoding.features.user.interfaces.IUserRepository;
import com.pinterngoding.features.user.interfaces.IUserService;
import com.pinterngoding.shared.classes.BaseService;
import com.pinterngoding.shared.constants.UserType;
import jakarta.persistence.NoResultException;

public class UserService extends BaseService<User> implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Boolean register(User newUser) {
        if (getByEmail(newUser.getEmail()) != null) {
            return false;
        }
        newUser.setUserType(UserType.STUDENT);
        newUser.setActive(false);

        return create(newUser);
    }

    @Override
    public Boolean activate(String activationCode) {
        return false;
    }

    @Override
    public User getByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (NoResultException e) {
            return null;
        }
    }
}
