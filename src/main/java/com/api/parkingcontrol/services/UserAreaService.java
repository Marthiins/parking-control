package com.api.parkingcontrol.services;

import com.api.parkingcontrol.Form.NewPasswordForm;
import com.api.parkingcontrol.models.User;
import com.api.parkingcontrol.repositories.UserRepository;
import com.api.parkingcontrol.services.BusinessRule.changePassword.ChangePasswordCheck;
import com.api.parkingcontrol.services.BusinessRule.changePassword.SamePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Service
public class UserAreaService {

    @Autowired
    private UserRepository userRepository;

    public String changePassword(Principal principal, NewPasswordForm newPasswordForm) {

        List<ChangePasswordCheck> validations = Arrays.asList(new SamePassword());

        User user = returnUser(principal);
        String oldPassword = user.getPassword();
        String newPassword = newPasswordForm.getPassword();

        validations.forEach(v->v.validation(oldPassword,newPassword)); // Vai LANÇAR A EXCEPTION SE SENHA FOR IGUAL

        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));

        userRepository.save(user);

        return "Success! You just changed your password!";
    }

    private User returnUser(Principal principal) {
        return userRepository.findByEmail(principal.getName()).get();
    }
}
