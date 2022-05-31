package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.UserDto;
import com.api.parkingcontrol.models.User;
import com.api.parkingcontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //Como vai ser feito o login
        Optional<User> user = repository.findByEmail(username);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Dados invalidos");

    }

    public void cadastrarUsuario(UserDto userDto) { // userDto = {name= victor.almada..., senha = 123456}
        User user = converterDeDtoParaUser(userDto); // userDto = {name= victor.almada..., senha = 123456
          repository.save(user);
    }


    // User - email- victor.almada@hotmail.com
    //User pass - 123456


    private User converterDeDtoParaUser(UserDto userDto) {// // userDto = {name= victor.almada..., senha = 123456}
        User user = new User(); //criando um novo usuario ( o BANCO SABE OQ USER SIGNIFICA, MAS N SABE O DTO)
        user.setEmail(userDto.getEmail());
        String senhaCodificada = new BCryptPasswordEncoder().encode(userDto.getPassword());
        user.setPassword(senhaCodificada);
        return user;
    }
}
