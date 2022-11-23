package com.pinterngoding.features.useractivation;

import com.pinterngoding.entity.User;
import com.pinterngoding.entity.UserActivation;
import com.pinterngoding.features.user.interfaces.IUserService;
import com.pinterngoding.features.useractivation.interfaces.IUserActivationRepository;
import com.pinterngoding.features.useractivation.interfaces.IUserActivationService;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UserActivationService implements IUserActivationService {
    private final IUserActivationRepository userActivationRepository;
    private final IUserService userService;

    public UserActivationService(IUserActivationRepository userActivationRepository, IUserService userService) {
        this.userActivationRepository = userActivationRepository;
        this.userService = userService;
    }

    @Override
    public UserActivation requestActivation(String email) {
        User relatedUser = userService.getByEmail(email);
        if (relatedUser == null) {
            throw new RuntimeException("Invalid Email");
        }
        UserActivation newActivation = new UserActivation();
        newActivation.setActivationCode(UUID.randomUUID().toString());
        newActivation.setExpireDate(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2)));
        newActivation.setRelatedUser(relatedUser);

        userActivationRepository.insert(newActivation);

        newActivation.setId(newActivation.getId());
        return newActivation;
    }

    @Override
    public Boolean activateUser(String activationCode) {
        UserActivation userActivation = userActivationRepository.findByCode(activationCode);
        if (userActivation == null) {
            throw new RuntimeException("Invalid Code");
        }
        if (System.currentTimeMillis() >= userActivation.getExpireDate().getTime()) {
            throw new RuntimeException("Activation Expired");
        }

        userActivation.getRelatedUser().setActive(true);
        userService.update(userActivation.getRelatedUser());

        return true;
    }
}
