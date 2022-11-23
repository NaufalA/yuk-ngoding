package com.pinterngoding.features.student;

import com.pinterngoding.entity.Student;
import com.pinterngoding.features.student.interfaces.IStudentService;
import com.pinterngoding.shared.classes.BaseService;
import com.pinterngoding.shared.interfaces.IRepository;

public class StudentService extends BaseService<Student> implements IStudentService {
    public StudentService(IRepository<Student> repository) {
        super(repository);
    }
    @Override
    public Boolean create(Student newItem) {
        return null;
    }

    @Override
    public Student update(Student updatedItem) {
        return null;
    }
}
