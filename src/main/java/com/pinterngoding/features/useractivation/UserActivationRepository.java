package com.pinterngoding.features.useractivation;

import com.pinterngoding.entity.UserActivation;
import com.pinterngoding.features.useractivation.interfaces.IUserActivationRepository;
import com.pinterngoding.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserActivationRepository extends BaseRepository<UserActivation> implements IUserActivationRepository {
    public UserActivationRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<UserActivation> findAll() {
        return null;
    }

    @Override
    public UserActivation findById(Long id) {
        return entityManager.find(UserActivation.class, id);
    }

    @Override
    public UserActivation findByCode(String activationCode) {
        TypedQuery<UserActivation> query =
                entityManager.createQuery(
                        "SELECT a FROM UserActivation a WHERE activationCode = :activationCode",
                        UserActivation.class
                );
        query.setParameter("activationCode", activationCode);

        return query.getSingleResult();
    }
}
