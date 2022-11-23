package com.pinterngoding;

import com.pinterngoding.entity.Student;
import com.pinterngoding.entity.User;
import com.pinterngoding.features.student.StudentRepository;
import com.pinterngoding.features.student.StudentService;
import com.pinterngoding.features.student.interfaces.IStudentRepository;
import com.pinterngoding.features.student.interfaces.IStudentService;
import com.pinterngoding.features.user.UserRepository;
import com.pinterngoding.features.user.UserService;
import com.pinterngoding.features.user.interfaces.IUserRepository;
import com.pinterngoding.features.user.interfaces.IUserService;
import com.pinterngoding.shared.constants.Education;
import com.pinterngoding.shared.utils.JPAUtility;
import jakarta.persistence.EntityManager;

public class TestingTesting {
    public static void main(String[] args) {
        EntityManager em = JPAUtility.getEntityManager();
        IUserRepository userRepository = new UserRepository(em);
        IUserService userService = new UserService(userRepository);

        IStudentRepository studentRepository = new StudentRepository(em);
        IStudentService studentService = new StudentService(studentRepository);

        User user = new User();
        user.setEmail("student1@ngoding.id");
        user.setPassword("student");

        Student student = new Student();
        student.setFirstName("1Stu");
        student.setLastName("dent");
        student.setNickname("student");
        student.setAddress("Somewhere");
        student.setPhone("088984238491");
        student.setIdentityNumber("984127123413");
        student.setEducation(Education.S1);
        student.setUser(user);
        user.setStudent(student);

//        System.out.println(userService.register(user));

        System.out.println(userService.getByEmail(user.getEmail()));

        studentService.getAll().forEach((s) -> {
            System.out.println(s.getFirstName());
            System.out.println(s.getUser());
        });





        JPAUtility.close();
    }
}
