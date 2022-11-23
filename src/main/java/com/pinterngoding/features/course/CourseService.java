package com.pinterngoding.features.course;

import com.pinterngoding.entity.Course;
import com.pinterngoding.features.course.interfaces.ICourseService;
import com.pinterngoding.shared.classes.BaseService;
import com.pinterngoding.shared.interfaces.IRepository;

public class CourseService extends BaseService<Course> implements ICourseService {

    public CourseService(IRepository<Course> repository) {
        super(repository);
    }
}
