package com.pinterngoding.features.course;

import com.pinterngoding.entity.Course;
import com.pinterngoding.features.course.interfaces.ICourseRepository;
import com.pinterngoding.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CourseRepository extends BaseRepository<Course> implements ICourseRepository {

    public CourseRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);

        return query.getResultList();
    }

    @Override
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }
}
