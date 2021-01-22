package com.englishapp.demoen.exceptions;

public class UsernameAlreadyExistResponse {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsernameAlreadyExistResponse(String username) {
        this.username = username;
    }
}
