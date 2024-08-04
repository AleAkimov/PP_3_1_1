package ru.akimov.springcorse.service;

import ru.akimov.springcorse.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    void update(User user);

    void delete(int id);

    User findById(int id);

    List<User> getAllUser();
}