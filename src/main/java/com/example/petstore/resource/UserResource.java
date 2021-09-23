package com.example.petstore.resource;

import com.example.petstore.entity.Token;
import com.example.petstore.entity.User;
import com.example.petstore.entity.constants.UserStatus;
import com.example.petstore.repository.TokenRepository;
import com.example.petstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;
    private final TokenRepository tokenRepository;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @PostMapping({"/createWithArray", "/createWithList"})
    public List<User> createUsers(@RequestBody ArrayList<User> users){
        List<User> savedUsers = new ArrayList<>();
        for (User user : users) {
            User save = userService.save(user);
            savedUsers.add(save);
        }
        return savedUsers;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username){
        Optional<User> user = userService.get(username);
        if(username==null || username.isBlank() || username.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if(user.isEmpty()){
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody User newUser){
        Optional<User> user = userService.get(username);
        if (username.isEmpty() || username.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.update(newUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/login")
    public ResponseEntity<User> loginUser(String username, String password){
        Optional<User> user = userService.get(username);
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            User u = user.get();
            if(!u.getPassword().equals(password)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            u.setUserStatus(UserStatus.AUTHORIZED);
            Token token = new Token();
            token.setUser(u);
            token.setUuid(UUID.randomUUID());
            Token savedToken = tokenRepository.save(token);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("X-Token", savedToken.getUuid().toString());
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<User> logoutUser(String username, @RequestHeader("X-Token") UUID uuid){
        Optional<User> user = userService.get(username);
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            User u = user.get();
            u.setUserStatus(UserStatus.LOGGED_OUT);
            userService.save(u);
            Token token = tokenRepository.getByUuid(uuid).get();
            tokenRepository.delete(token);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username){
        Optional<User> user = userService.get(username);
        if(username.isBlank() || username.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            User u = user.get();
            userService.delete(u.getId());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }


}
