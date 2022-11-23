package com.pinterngoding.features.student;

import com.pinterngoding.entity.Student;
import com.pinterngoding.features.student.interfaces.IStudentRepository;
import com.pinterngoding.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentRepository extends BaseRepository<Student> implements IStudentRepository {
    public StudentRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);

        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }
}
