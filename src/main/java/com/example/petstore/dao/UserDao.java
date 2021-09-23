package com.example.petstore.dao;

import com.example.petstore.entity.User;
import com.example.petstore.entity.constants.UserStatus;

public interface UserDao {
    void save(User user);
    void setStatus(long userId, UserStatus status);
    void delete(long id);
    void update(long id, User user);
    User getByUsername(String username);
}
