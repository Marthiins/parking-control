package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.Form.NewPasswordForm;
import com.api.parkingcontrol.services.UserAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(value = "/userarea")
public class UserAreaController {

    @Autowired
    private UserAreaService userAreaService;

    @PutMapping(value = "/changepassword")
    public ResponseEntity<String> changePassword(Principal user, @RequestBody @Valid NewPasswordForm newPasswordForm) {

        String message = userAreaService.changePassword(user,newPasswordForm);

        return ResponseEntity.ok().body(message);
    }


}
