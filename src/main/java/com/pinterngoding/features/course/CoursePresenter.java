package com.pinterngoding.features.course;

import com.pinterngoding.entity.Course;
import com.pinterngoding.entity.CourseRecord;
import com.pinterngoding.features.course.dtos.CourseReportResponseDto;
import com.pinterngoding.features.course.interfaces.ICourseService;
import com.pinterngoding.features.courserecord.interfaces.ICourseRecordService;
import com.pinterngoding.shared.classes.BasePresenter;
import com.pinterngoding.shared.constants.CourseCategory;
import com.pinterngoding.shared.constants.PricingType;
import com.pinterngoding.shared.utils.InputHelper;
import com.pinterngoding.shared.utils.StringHelper;
import com.pinterngoding.shared.utils.UserSessionUtility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CoursePresenter extends BasePresenter {
    private final ICourseService courseService;
    private final ICourseRecordService courseRecordService;

    public CoursePresenter(String title, ICourseService courseService, ICourseRecordService courseRecordService) {
        super(title);
        this.courseService = courseService;
        this.courseRecordService = courseRecordService;
    }

    public void viewRegisteredCoursesMenu() {
        StringHelper.printHeader("Registered " + title + "s");
        List<Course> courses =
                UserSessionUtility.getCurrentUser().getStudent().getCourseRecords()
                        .stream().map(CourseRecord::getCourse).collect(Collectors.toList());

        viewCoursesMenu(courses);
    }

    public void viewAllCoursesMenu() {
        List<Course> courses = courseService.getAll();
        viewCoursesMenu(courses);
    }

    public void viewCoursesMenu(List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.println(
                    i + 1 + ". " +
                            course.getTitle() + " | " +
                            course.getDescription() + " | " +
                            course.getStartDate() + " | " +
                            course.getEndDate()
            );
        }
        StringHelper.printInputPrompt("Choose index");
        int index = InputHelper.inputInt(1, courses.size()) - 1;

        viewCourseDetailMenu(courses.get(index).getId());
    }

    public void viewCourseDetailMenu(Long id) {
        Course course = courseService.getById(id);
        System.out.println(
                course.getTitle() + "\n" +
                        "..............................\n" +
                        course.getDescription() + "\n" +
                        course.getStartDate() + "\n" +
                        course.getEndDate()
        );
        StringHelper.printSeparator("-", 30);
        switch (UserSessionUtility.getCurrentUser().getUserType()) {
            case ADMIN:
                adminCourseActions(course);
                break;
            case STUDENT:
                studentCourseActions(course);
                break;
        }
    }

    public void adminCourseActions(Course course) {
        System.out.println(
                "1. Update Course\n" +
                        "2. Get Reports\n" +
                        "0. Exit"
        );
        StringHelper.printInputPrompt("Choose Action");
        Integer choice = InputHelper.inputInt();
        switch (choice) {
            case 1:
                System.out.println("Unimplemented");
                break;
            case 2:
                StringHelper.printHeader("Course Summary");
                CourseReportResponseDto report = courseRecordService.getReports(course);
                System.out.println("Passing Score\t: " + course.getMinScore());
                System.out.println("Average Score\t: " + report.averageScore);
                System.out.println("Passed Students\t: " + report.passedStudent);
                System.out.println("Total Students\t: " + report.totalStudent);
                System.out.println("Passing Percentage\t: " + ((report.passedStudent/report.totalStudent)*100) + "%");
                break;
            case 0:
                break;
            default:
                System.out.println("Action not available");
                break;
        }
    }

    public void studentCourseActions(Course course) {
        System.out.println(
                "1. Register to Course\n" +
                        "0. Exit"
        );
        StringHelper.printInputPrompt("Choose Action");
        Integer choice = InputHelper.inputInt();
        switch (choice) {
            case 1:
                try {
                    Boolean registerSuccess = courseRecordService.registerToCourse(UserSessionUtility.getCurrentUser().getStudent(), course);
                    if (registerSuccess) {
                        System.out.println("Successfully Registered");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Action not available");
                break;
        }
    }

    public void manageCourseMenu() {
        StringHelper.printHeader("Manage " + title);
        System.out.println(
                "1. Create " + title + " \n" +
                        "2. View All " + title + "s\n" +
                        "3. View All Course Registrations\n" +
                        "0. Exit"
        );
        StringHelper.printInputPrompt("Select Action");
        Integer choice = InputHelper.inputInt();
        switch (choice) {
            case 1:
                createCourseMenu();
                break;
            case 2:
                viewAllCoursesMenu();
                break;
            case 3:
                viewAllRegistrationMenu();
                break;
            case 0:
                break;
            default:
                System.out.println("Action not available");
                break;
        }
    }

    public void createCourseMenu() {
        StringHelper.printHeader("Create " + title);
        Course newCourse = new Course();

        StringHelper.printInputPrompt("Title");
        newCourse.setTitle(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Description");
        newCourse.setDescription(InputHelper.inputString(false));
        StringBuilder categoryPrint = new StringBuilder();
        Arrays.stream(CourseCategory.values()).forEach(e -> categoryPrint.append("| ").append(e).append("\n"));
        StringHelper.printInputPrompt("Category\n" + categoryPrint);
        newCourse.setCourseCategory(CourseCategory.valueOf(InputHelper.inputString(false)));
        StringBuilder pricingPrint = new StringBuilder();
        Arrays.stream(PricingType.values()).forEach(e -> pricingPrint.append("| ").append(e).append("\n"));
        StringHelper.printInputPrompt("Pricing Type\n" + pricingPrint);
        newCourse.setPricingType(PricingType.valueOf(InputHelper.inputString(false)));
        StringHelper.printInputPrompt("Price");
        newCourse.setPrice(InputHelper.inputInt());
        StringHelper.printInputPrompt("Teacher");
        newCourse.setTeacherName(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Minimum Passing Score");
        newCourse.setMinScore(InputHelper.inputInt(0, 100));
        StringHelper.printInputPrompt("Duration (days)");
        newCourse.setDuration(InputHelper.inputInt());
        StringHelper.printInputPrompt("Start Date (yyyy-mm-dd)");
        newCourse.setStartDate(InputHelper.inputDate());
        StringHelper.printInputPrompt("Scheduled Start Time (hh:mm:ss)");
        newCourse.setScheduleStart(InputHelper.inputTime());
        StringHelper.printInputPrompt("Scheduled Finish Time (hh:mm:ss)");
        newCourse.setScheduleEnd(InputHelper.inputTime());

        try {
            courseService.create(newCourse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewAllRegistrationMenu() {
        StringHelper.printHeader("View All  Registrations");
        List<CourseRecord> courseRecords = courseRecordService.getAll();

        for (int i = 0; i < courseRecords.size(); i++) {
            CourseRecord cr = courseRecords.get(i);
            System.out.println(
                    (i + 1) + "\t | " +
                            cr.getStudent().getUser().getEmail() + "\t | " +
                            cr.getCourse().getTitle() + "\t | " +
                            cr.getApproved() + "\t | " +
                            cr.getScore()
            );
        }
    }
}
