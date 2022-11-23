package com.pinterngoding;

import com.pinterngoding.entity.Student;
import com.pinterngoding.entity.User;
import com.pinterngoding.entity.UserActivation;
import com.pinterngoding.features.auth.AuthService;
import com.pinterngoding.features.auth.interfaces.IAuthService;
import com.pinterngoding.features.student.StudentRepository;
import com.pinterngoding.features.student.StudentService;
import com.pinterngoding.features.student.interfaces.IStudentRepository;
import com.pinterngoding.features.student.interfaces.IStudentService;
import com.pinterngoding.features.user.UserRepository;
import com.pinterngoding.features.user.UserService;
import com.pinterngoding.features.user.interfaces.IUserRepository;
import com.pinterngoding.features.user.interfaces.IUserService;
import com.pinterngoding.features.useractivation.UserActivationRepository;
import com.pinterngoding.features.useractivation.UserActivationService;
import com.pinterngoding.features.useractivation.interfaces.IUserActivationRepository;
import com.pinterngoding.features.useractivation.interfaces.IUserActivationService;
import com.pinterngoding.shared.constants.Education;
import com.pinterngoding.shared.constants.UserType;
import com.pinterngoding.shared.utils.JPAUtility;
import jakarta.persistence.EntityManager;

public class TestingTesting {
    public static void main(String[] args) {
        EntityManager em = JPAUtility.getEntityManager();
        IUserRepository userRepository = new UserRepository(em);
        IUserService userService = new UserService(userRepository);

        IStudentRepository studentRepository = new StudentRepository(em);
        IStudentService studentService = new StudentService(studentRepository);

        IUserActivationRepository userActivationRepository = new UserActivationRepository(em);
        IUserActivationService userActivationService = new UserActivationService(userActivationRepository, userService);
        IAuthService authService = new AuthService(userService);

        User user = new User();
        user.setEmail("student@ngoding.id");
        user.setPassword("student");
        user.setUserType(UserType.STUDENT);

        Student student = new Student();
        student.setFirstName("Stu");
        student.setLastName("dent");
        student.setNickname("student");
        student.setAddress("Somewhere");
        student.setPhone("088534238491");
        student.setIdentityNumber("984127125781");
        student.setEducation(Education.D4);
        student.setUser(user);
        user.setStudent(student);

        System.out.println(userService.register(user));

//        System.out.println(userService.getByEmail(user.getEmail()));
//
//        studentService.getAll().forEach((s) -> {
//            System.out.println(s.getFirstName());
//            System.out.println(s.getUser());
//        });

        System.out.println("Login Unactivated");
        try {
            System.out.println(authService.login(user.getEmail(), user.getPassword()) == null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Request Activation");
        UserActivation userActivation = userActivationService.requestActivation(user.getEmail());
        System.out.println(userActivation.getActivationCode());

        System.out.println("Activate");
        user.setActive(userActivationService.activateUser(userActivation.getActivationCode()));
        System.out.println(user.getActive());

        System.out.println("Login Activated");
        try {
            System.out.println(authService.login(user.getEmail(), user.getPassword()) == null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JPAUtility.close();
    }
}
