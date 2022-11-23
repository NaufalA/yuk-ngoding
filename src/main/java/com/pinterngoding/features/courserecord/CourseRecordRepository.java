package com.pinterngoding.features.courserecord;

import com.pinterngoding.entity.CourseRecord;
import com.pinterngoding.features.courserecord.interfaces.ICourseRecordRepository;
import com.pinterngoding.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CourseRecordRepository extends BaseRepository<CourseRecord> implements ICourseRecordRepository {
    public CourseRecordRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<CourseRecord> findAll() {
        TypedQuery<CourseRecord> query = entityManager.createQuery("SELECT c FROM CourseRecord c", CourseRecord.class);

        return query.getResultList();
    }

    @Override
    public CourseRecord findById(Long id) {
        return entityManager.find(CourseRecord.class, id);
    }
}
