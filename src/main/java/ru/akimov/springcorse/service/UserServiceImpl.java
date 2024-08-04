package ru.akimov.springcorse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akimov.springcorse.dao.UserDao;
import ru.akimov.springcorse.model.User;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return userDao.getAllUsers();
    }
}