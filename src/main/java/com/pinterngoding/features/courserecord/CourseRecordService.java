package com.pinterngoding.features.courserecord;

import com.pinterngoding.entity.Course;
import com.pinterngoding.entity.CourseRecord;
import com.pinterngoding.entity.Student;
import com.pinterngoding.features.course.dtos.CourseReportResponseDto;
import com.pinterngoding.features.courserecord.interfaces.ICourseRecordService;
import com.pinterngoding.shared.classes.BaseService;
import com.pinterngoding.shared.interfaces.IRepository;

import java.util.List;

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

    @Override
    public CourseReportResponseDto getReports(Course course) {
        List<CourseRecord> courseRecords = course.getCourseRecords();
        CourseReportResponseDto courseReportResponseDto = new CourseReportResponseDto();
        courseRecords.forEach(cr -> {
            courseReportResponseDto.averageScore += cr.getScore();
            courseReportResponseDto.totalStudent += 1;
            if (cr.getScore() >= course.getMinScore()) {
                courseReportResponseDto.passedStudent += 1;
            }
        });
        courseReportResponseDto.averageScore = courseReportResponseDto.averageScore / courseReportResponseDto.totalStudent;

        return courseReportResponseDto;
    }
}
