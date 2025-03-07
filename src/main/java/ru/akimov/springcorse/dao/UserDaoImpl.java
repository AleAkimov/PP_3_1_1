package ru.akimov.springcorse.dao;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import ru.akimov.springcorse.model.User;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {



    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(int id) {
        User user = findById(id);
        em.remove(user);
    }

    @Override
    public User findById(int id) {
        User user = em.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("User not found for ID: " + id);
        }
        return user;
    }
}