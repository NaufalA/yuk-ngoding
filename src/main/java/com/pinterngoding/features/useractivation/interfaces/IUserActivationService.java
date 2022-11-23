package com.pinterngoding.features.useractivation.interfaces;

import com.pinterngoding.entity.UserActivation;

public interface IUserActivationService {
    UserActivation requestActivation(String email);
    Boolean activateUser(String activationCode);
}
