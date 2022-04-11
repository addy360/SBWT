package com.addy360.sbjwt.controllers;

import com.addy360.sbjwt.models.UserEntity;
import com.addy360.sbjwt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController{
    private final UserService userService;

    @GetMapping
    public List<UserEntity> index() {
        return userService.index();
    }

    @GetMapping("/{id}")
    public UserEntity show(@PathVariable Long id) {
        return userService.show(id);
    }

    @PostMapping
    public UserEntity store(@RequestBody UserEntity userEntity) {
        return userService.store(userEntity);
    }

    @PutMapping("/{id}")
    public UserEntity update(UserEntity data, @PathVariable Long id) {
        return userService.update(data);
    }
}
