package com.example.petstore.entity.constants;

public enum UserStatus {
    UNREGISTERED("unregistered",0),
    REGISTERED("registered", 1),
    AUTHORIZED("authorized", 2),
    LOGGED_OUT("logged-out",3);

    UserStatus(String status, int intStatus) {
    }
}
