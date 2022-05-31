package com.api.parkingcontrol.services.BusinessRule.changePassword;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SamePassword implements  ChangePasswordCheck{
    @Override
    public void validation(String oldPassword, String newPassowrd) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean check = passwordEncoder.matches(newPassowrd,oldPassword);

        if(check) {
            throw new RuntimeException("SENHA NÃO PODE SER IGUAL A ANTERIOR");
        }

    }
}
