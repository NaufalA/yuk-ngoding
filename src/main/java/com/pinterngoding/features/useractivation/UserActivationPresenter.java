package com.pinterngoding.features.useractivation;

import com.pinterngoding.entity.UserActivation;
import com.pinterngoding.features.useractivation.interfaces.IUserActivationService;
import com.pinterngoding.shared.utils.InputHelper;
import com.pinterngoding.shared.utils.StringHelper;

public class UserActivationPresenter {
    IUserActivationService userActivationService;

    public UserActivationPresenter(IUserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    public void requestActivationMenu() {
        StringHelper.printHeader("Request Activation");
        StringHelper.printInputPrompt("E-mail");
        String email = InputHelper.inputString(false);

        try {
            UserActivation userActivation = userActivationService.requestActivation(email);
            if (userActivation == null) {
                System.out.println("E-mail is not registered");
                return;
            }
            System.out.println("Activation Code:\n" + userActivation.getActivationCode());
            System.out.println("Code is valid for 2 hours");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void activateUserMenu() {
        StringHelper.printHeader("Activate Account");
        StringHelper.printInputPrompt("Activation Code");
        String activationCode = InputHelper.inputString(false);

        try {
            Boolean activationSuccess = userActivationService.activateUser(activationCode);
            if (activationSuccess) {
                System.out.println("Activation Success");
            } else {
                System.out.println("Activation Failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
