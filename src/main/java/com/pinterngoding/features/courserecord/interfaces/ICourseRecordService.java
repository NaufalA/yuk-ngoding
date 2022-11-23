package com.pinterngoding.features.courserecord.interfaces;

import com.pinterngoding.entity.Course;
import com.pinterngoding.entity.CourseRecord;
import com.pinterngoding.entity.Student;
import com.pinterngoding.shared.interfaces.IService;

public interface ICourseRecordService extends IService<CourseRecord> {
    Boolean registerToCourse(Student student, Course course);
}
