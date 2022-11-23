package com.pinterngoding;

import com.pinterngoding.entity.*;
import com.pinterngoding.features.auth.AuthService;
import com.pinterngoding.features.auth.interfaces.IAuthService;
import com.pinterngoding.features.course.CourseRepository;
import com.pinterngoding.features.course.CourseService;
import com.pinterngoding.features.course.interfaces.ICourseRepository;
import com.pinterngoding.features.course.interfaces.ICourseService;
import com.pinterngoding.features.courserecord.CourseRecordRepository;
import com.pinterngoding.features.courserecord.CourseRecordService;
import com.pinterngoding.features.courserecord.interfaces.ICourseRecordRepository;
import com.pinterngoding.features.courserecord.interfaces.ICourseRecordService;
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
import com.pinterngoding.shared.constants.PricingType;
import com.pinterngoding.shared.constants.CourseCategory;
import com.pinterngoding.shared.constants.UserType;
import com.pinterngoding.shared.utils.JPAUtility;
import jakarta.persistence.EntityManager;

import java.sql.Date;

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

        ICourseRepository courseRepository = new CourseRepository(em);
        ICourseService courseService = new CourseService(courseRepository);

        ICourseRecordRepository courseRecordRepository = new CourseRecordRepository(em);
        ICourseRecordService courseRecordService = new CourseRecordService(courseRecordRepository);

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

//        System.out.println(userService.register(user));
        student = studentService.getById(3L);

//        System.out.println(userService.getByEmail(user.getEmail()));
//
//        studentService.getAll().forEach((s) -> {
//            System.out.println(s.getFirstName());
//            System.out.println(s.getUser());
//        });

//        System.out.println("Login Unactivated");
//        try {
//            System.out.println(authService.login(user.getEmail(), user.getPassword()) == null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Request Activation");
//        UserActivation userActivation = userActivationService.requestActivation(user.getEmail());
//        System.out.println(userActivation.getActivationCode());
//
//        System.out.println("Activate");
//        user.setActive(userActivationService.activateUser(userActivation.getActivationCode()));
//        System.out.println(user.getActive());

        System.out.println("Login Activated");
        try {
            System.out.println(authService.login(user.getEmail(), user.getPassword()) == null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Create Course");
        Course course = new Course();
        course.setTitle("React");
        course.setDescription("30 days basic react and redux course");
        course.setStartDate(Date.valueOf("2022-11-25"));
        course.setDuration(30);
        course.setPricingType(PricingType.PREMIUM);
        course.setPrice(500000);
        course.setTrainingCategory(CourseCategory.FRONTEND);
        course.setMinScore(70);

//        courseService.create(course);

        course = courseService.getById(1L);

        System.out.println(course);

        System.out.println(courseRecordService.registerToCourse(student, course));

        CourseRecord courseRecord = student.getCourseRecords().get(0);

        courseRecord.setApproved(true);
        courseRecord.setScore(80);
        courseRecordService.update(courseRecord);
        System.out.println(courseRecord.getScore());

        JPAUtility.close();
    }
}
