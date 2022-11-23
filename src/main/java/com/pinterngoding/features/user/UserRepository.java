package com.pinterngoding.features.user;

import com.pinterngoding.entity.User;
import com.pinterngoding.features.user.interfaces.IUserRepository;
import com.pinterngoding.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserRepository extends BaseRepository<User> implements IUserRepository {


    public UserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);

        return query.getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE email = :email", User.class);
        query.setParameter("email", email);

        return query.getSingleResult();
    }
}
