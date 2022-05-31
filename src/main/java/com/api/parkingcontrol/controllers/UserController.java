package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.UserDto;
import com.api.parkingcontrol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

       @PostMapping("/cadastro")                // userDto = {name= victor.almada..., senha = 123456}
        public ResponseEntity<String> cadastroUsuario(@RequestBody UserDto userDto) { //recebi um Dto

           userService.cadastrarUsuario(userDto); //userDto = {name= victor.almada..., senha = 123456}

           return ResponseEntity.ok().body("CADASTRADO COM SUCESSO!");
    }
}
