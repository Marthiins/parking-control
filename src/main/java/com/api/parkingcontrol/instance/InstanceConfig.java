package com.api.parkingcontrol.instance;

import com.api.parkingcontrol.models.Role;
import com.api.parkingcontrol.models.User;
import com.api.parkingcontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceConfig implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {

        String passwordEncoded = "$2a$10$KT5rbfQTU8103kP6uEmkkO3W8XTc4MFH2peGPuL3sQ3X5ne.kz2oK";

       User user = new User("sergio@gmail.com",passwordEncoded);
       repository.save(user);

    }
}
