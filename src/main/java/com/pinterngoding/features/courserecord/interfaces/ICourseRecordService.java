package com.pinterngoding.features.courserecord.interfaces;

import com.pinterngoding.entity.Course;
import com.pinterngoding.entity.CourseRecord;
import com.pinterngoding.entity.Student;
import com.pinterngoding.features.course.dtos.CourseReportResponseDto;
import com.pinterngoding.shared.interfaces.IService;

public interface ICourseRecordService extends IService<CourseRecord> {
    Boolean registerToCourse(Student student, Course course);
    CourseReportResponseDto getReports(Course course);
}
