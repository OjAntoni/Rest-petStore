package com.example.petstore.entity;

import com.example.petstore.entity.constants.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private UserStatus userStatus;

    public User (User newUser){
        if(newUser.getUsername()!=null)
            this.username = newUser.getUsername();
        if(newUser.getFirstName()!=null)
            this.firstName = newUser.getFirstName();
        if(newUser.getLastName()!=null)
            this.lastName = newUser.getLastName();
        if(newUser.getEmail()!=null)
            this.email = newUser.getEmail();
        if(newUser.getPassword()!=null)
            this.password = newUser.getPassword();
        if(newUser.getPhone()!=null)
            this.phone = newUser.getPhone();
    }
}
