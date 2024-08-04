package ru.akimov.springcorse.dao;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import ru.akimov.springcorse.model.User;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

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
        if (user == null) {
            throw new EntityNotFoundException("User not found for ID: " + id);
        }
        em.remove(findById(id));
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }
}