package com.example.petstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public User (User newUser){
        this.username = newUser.getUsername();
        this.firstName = newUser.getFirstName();
        this.lastName = newUser.getLastName();
        this.email = newUser.getEmail();
        this.password = newUser.getPassword();
        this.phone = newUser.getPhone();
    }
}
