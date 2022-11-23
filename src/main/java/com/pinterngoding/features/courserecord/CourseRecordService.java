package com.pinterngoding.features.courserecord;

import com.pinterngoding.entity.Course;
import com.pinterngoding.entity.CourseRecord;
import com.pinterngoding.entity.Student;
import com.pinterngoding.features.courserecord.interfaces.ICourseRecordService;
import com.pinterngoding.shared.classes.BaseService;
import com.pinterngoding.shared.interfaces.IRepository;

public class CourseRecordService extends BaseService<CourseRecord> implements ICourseRecordService {
    public CourseRecordService(IRepository<CourseRecord> repository) {
        super(repository);
    }

    @Override
    public Boolean registerToCourse(Student student, Course course) {
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.getCourseRecordKey().setStudentId(student.getId());
        courseRecord.getCourseRecordKey().setCourseId(course.getId());
        courseRecord.setStudent(student);
        courseRecord.setCourse(course);
        courseRecord.setApproved(false);

        return create(courseRecord);
    }
}
