package ru.akimov.springcorse.dao;

import ru.akimov.springcorse.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void save(User user);

    void update(User user);

    void delete(int id);

    User findById(int id);
}