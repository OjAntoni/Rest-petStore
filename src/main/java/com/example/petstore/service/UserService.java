package com.example.petstore.service;

import com.example.petstore.entity.User;
import com.example.petstore.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }

    public void update(User newUser){
        userRepository.save(new User(newUser));
    }

    public Optional<User> get(String username){
        return userRepository.getByUsername(username);
    }
}
