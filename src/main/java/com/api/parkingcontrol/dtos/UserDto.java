package com.api.parkingcontrol.dtos;

import javax.validation.constraints.NotBlank;

public class UserDto { // Seria o email e password que recebo pelo postam

    @NotBlank
    private String email;
    @NotBlank
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
