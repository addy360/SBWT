package com.addy360.sbjwt.services;

import com.addy360.sbjwt.models.UserEntity;
import com.addy360.sbjwt.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements CrudService<UserEntity>, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> index() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity show(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserEntity store(UserEntity data) {
        log.info("Saving user : {}", data.toString());
        return userRepository.save(data);
    }

    @Override
    public UserEntity update(UserEntity data) {
        log.info("Updating user : {}", data.toString());
        return userRepository.save(data);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByEmailOrUsername(username,username);
        if(userEntity == null){
            log.info("User {} not found",username);
            throw new UsernameNotFoundException( String.format("User %s not found",username));
        }
        return new User(username,userEntity.getPassword(), userEntity.getRoles().stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRoleName())).collect(Collectors.toSet()));
    }
}
