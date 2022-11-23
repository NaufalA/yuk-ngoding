package com.pinterngoding;

import com.pinterngoding.features.auth.AuthPresenter;
import com.pinterngoding.features.auth.AuthService;
import com.pinterngoding.features.auth.interfaces.IAuthService;
import com.pinterngoding.features.course.CoursePresenter;
import com.pinterngoding.features.course.CourseRepository;
import com.pinterngoding.features.course.CourseService;
import com.pinterngoding.features.course.interfaces.ICourseRepository;
import com.pinterngoding.features.course.interfaces.ICourseService;
import com.pinterngoding.features.courserecord.CourseRecordRepository;
import com.pinterngoding.features.courserecord.CourseRecordService;
import com.pinterngoding.features.courserecord.interfaces.ICourseRecordRepository;
import com.pinterngoding.features.courserecord.interfaces.ICourseRecordService;
import com.pinterngoding.features.menu.Menu;
import com.pinterngoding.features.student.StudentRepository;
import com.pinterngoding.features.student.StudentService;
import com.pinterngoding.features.student.interfaces.IStudentRepository;
import com.pinterngoding.features.student.interfaces.IStudentService;
import com.pinterngoding.features.user.UserPresenter;
import com.pinterngoding.features.user.UserRepository;
import com.pinterngoding.features.user.UserService;
import com.pinterngoding.features.user.interfaces.IUserRepository;
import com.pinterngoding.features.user.interfaces.IUserService;
import com.pinterngoding.features.useractivation.UserActivationPresenter;
import com.pinterngoding.features.useractivation.UserActivationRepository;
import com.pinterngoding.features.useractivation.UserActivationService;
import com.pinterngoding.features.useractivation.interfaces.IUserActivationRepository;
import com.pinterngoding.features.useractivation.interfaces.IUserActivationService;
import com.pinterngoding.shared.utils.JPAUtility;
import com.pinterngoding.shared.utils.UserSessionUtility;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtility.getEntityManager();
        IUserRepository userRepository = new UserRepository(em);
        IUserService userService = new UserService(userRepository);
        UserPresenter userPresenter = new UserPresenter("User", userService);

        IStudentRepository studentRepository = new StudentRepository(em);
        IStudentService studentService = new StudentService(studentRepository);

        IUserActivationRepository userActivationRepository = new UserActivationRepository(em);
        IUserActivationService userActivationService = new UserActivationService(userActivationRepository, userService);
        UserActivationPresenter userActivationPresenter = new UserActivationPresenter(userActivationService);

        IAuthService authService = new AuthService(userService);
        AuthPresenter authPresenter = new AuthPresenter(authService);

        ICourseRepository courseRepository = new CourseRepository(em);
        ICourseService courseService = new CourseService(courseRepository);

        ICourseRecordRepository courseRecordRepository = new CourseRecordRepository(em);
        ICourseRecordService courseRecordService = new CourseRecordService(courseRecordRepository);

        CoursePresenter coursePresenter = new CoursePresenter("Course", courseService, courseRecordService);

        Menu menu = new Menu(authPresenter, userPresenter, userActivationPresenter, coursePresenter);

        boolean stop = false;
        while (!stop) {
            if (!UserSessionUtility.getIsLoggedIn()) {
                stop = menu.entryMenu();
            } else {
                switch (UserSessionUtility.getCurrentUser().getUserType()) {
                    case STUDENT :
                        menu.studentMenu();
                        break;
                    case ADMIN:
                        menu.adminMenu();
                        break;
                }
            }
        }

        JPAUtility.close();
    }
}