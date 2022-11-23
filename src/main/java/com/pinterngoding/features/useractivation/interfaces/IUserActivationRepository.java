package com.pinterngoding.features.useractivation.interfaces;

import com.pinterngoding.entity.UserActivation;
import com.pinterngoding.shared.interfaces.IRepository;

public interface IUserActivationRepository extends IRepository<UserActivation> {
    UserActivation findByCode(String activationCode);
}
