package com.sample.users.entities.request;

public class LoginRequestEntity {
    private LoginItemRequestEntity user;

    public LoginItemRequestEntity getUser() {
        return user;
    }

    public void setUser(LoginItemRequestEntity user) {
        this.user = user;
    }
}
