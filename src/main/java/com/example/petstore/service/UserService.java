package com.example.petstore.service;

import com.example.petstore.dao.UserDao;
import com.example.petstore.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user){
        if(user==null) return;
        userDao.save(user);
    }

    public void setStatus(long id, int status){
        userDao.setStatus(id, status);
    }

    public void delete(long id){
        userDao.delete(id);
    }

    public void update(long id, User newUser){
        userDao.update(id, new User(newUser));
    }

    public Optional<User> get(String username){
        return Optional.ofNullable(userDao.getByUsername(username));
    }
}
