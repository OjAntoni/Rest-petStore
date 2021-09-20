package com.example.petstore.dao;

import com.example.petstore.entity.User;

public interface UserDao {
    void save(User user);
    void setStatus(long userId, int status);
    void delete(long id);
    void update(long id, User user);
    User getByUsername(String username);
}
