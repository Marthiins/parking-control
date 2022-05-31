package com.api.parkingcontrol.Form;


import javax.validation.constraints.NotNull;


public class NewPasswordForm {

    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
