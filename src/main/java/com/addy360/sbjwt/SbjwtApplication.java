package com.addy360.sbjwt;

import com.addy360.sbjwt.models.RoleEntity;
import com.addy360.sbjwt.models.UserEntity;
import com.addy360.sbjwt.repositories.UserRepository;
import com.addy360.sbjwt.services.RoleService;
import com.addy360.sbjwt.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class SbjwtApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SbjwtApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleService roleService, UserService userService) {
        return args -> {
            List.of("USER", "ADMIN", "MANAGER", "SUPER_ADMIN").stream().forEach(s -> {
                RoleEntity role = new RoleEntity(null, s);
                roleService.store(role);
            });

            userService.store(new UserEntity(null, "Jane", "Doe", "jdoe", "jdoe@email.com", BCrypt.hashpw("password", BCrypt.gensalt()), List.of(roleService.show(1L), roleService.show(2L))));

            userService.store(new UserEntity(null, "John", "Doe", "jd", "john.doe@email.com", BCrypt.hashpw("password", BCrypt.gensalt()), List.of(roleService.show(3L), roleService.show(4L))));


        };
    }

    @Bean
    BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService getUserDetailsService() {
        return username -> {
            UserEntity userEntity = userRepository.findUserEntityByEmailOrUsername(username, username);
            if (userEntity == null) {
                log.info("User {} not found", username);
                throw new UsernameNotFoundException(String.format("User %s not found", username));
            }
            return new User(username, userEntity.getPassword(), userEntity.getRoles().stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRoleName())).collect(Collectors.toSet()));
        };
    }


}
