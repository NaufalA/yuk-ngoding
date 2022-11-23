package com.pinterngoding.features.user;

import com.pinterngoding.entity.Student;
import com.pinterngoding.entity.User;
import com.pinterngoding.features.user.interfaces.IUserService;
import com.pinterngoding.shared.classes.BasePresenter;
import com.pinterngoding.shared.constants.Education;
import com.pinterngoding.shared.utils.InputHelper;
import com.pinterngoding.shared.utils.StringHelper;

import java.util.Arrays;

public class UserPresenter extends BasePresenter {
    IUserService userService;

    public UserPresenter(IUserService userService) {
        this.userService = userService;
    }

    public void registerMenu() {
        User newUser = new User();
        StringHelper.printHeader("Register");
        StringHelper.printInputPrompt("E-mail");
        newUser.setEmail(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Password");
        newUser.setPassword(InputHelper.inputString(false));
        System.out.println();
        Student student = new Student();
        StringHelper.printInputPrompt("First Name");
        student.setFirstName(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Last Name");
        student.setLastName(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Nickname");
        student.setNickname(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Address");
        student.setAddress(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Phone");
        student.setPhone(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Identity Number");
        student.setIdentityNumber(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Last Education");
        Arrays.stream(Education.values()).forEach(e -> System.out.println("| " + e));
        student.setEducation(Education.valueOf(InputHelper.inputString(false).toUpperCase()));

        newUser.setStudent(student);

        try {
            userService.register(newUser);
            System.out.println("Register success");
        } catch (Exception e) {
            System.out.println("Register failed");
            System.out.println(e.getMessage());
        }
    }
}
