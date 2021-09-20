package com.example.petstore.resource;

import com.example.petstore.entity.User;
import com.example.petstore.entity.constants.UserStatus;
import com.example.petstore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        System.out.println(user);
        userService.save(user);
    }

    @PostMapping({"/createWithArray", "/createWithList"})
    public void createUsers(@RequestBody ArrayList<User> users){
        for (User user : users) {
            userService.save(user);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username){
        Optional<User> user = userService.get(username);
        if(username==null || username.isBlank() || username.isEmpty()){
            return ResponseEntity.status(400).build();
        } else if(user.isEmpty()){
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(user.get());
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody User newUser){
        Optional<User> user = userService.get(username);
        if (username.isEmpty() || username.isBlank()) {
            return ResponseEntity.status(400).build();
        } else if(user.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        userService.update(user.get().getId(), newUser);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/login")
    public ResponseEntity<User> loginUser(String username, String password){
        Optional<User> user = userService.get(username);
        if (user.isEmpty()){
            return ResponseEntity.status(400).build();
        } else {
            User u = user.get();
            if(!u.getPassword().equals(password)){
                return ResponseEntity.status(400).build();
            }
            u.setUserStatus(UserStatus.AUTHORIZED);
            return ResponseEntity.status(200).build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<User> logoutUser(String username){
        Optional<User> user = userService.get(username);
        if (user.isEmpty()){
            return ResponseEntity.status(400).build();
        } else {
            User u = user.get();
            u.setUserStatus(UserStatus.LOGGED_OUT);
            return ResponseEntity.status(200).build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username){
        Optional<User> user = userService.get(username);
        if(username.isBlank() || username.isEmpty()){
            return ResponseEntity.status(400).build();
        }
        if (user.isEmpty()){
            return ResponseEntity.status(404).build();
        } else {
            User u = user.get();
            userService.delete(u.getId());
            return ResponseEntity.status(200).build();
        }
    }


}
