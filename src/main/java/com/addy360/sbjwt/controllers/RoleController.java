package com.addy360.sbjwt.controllers;


import com.addy360.sbjwt.models.RoleEntity;
import com.addy360.sbjwt.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleEntity> index() {

        //ResponseEntity<RoleEntity>
        return roleService.index();
    }

    @GetMapping("/{id}")
    public RoleEntity show(@PathVariable Long id) {
        return roleService.show(id);
    }

    @PostMapping
    public RoleEntity store(@RequestBody RoleEntity userEntity) {
        return roleService.store(userEntity);
    }

    @PutMapping("/{id}")
    public RoleEntity update(@RequestBody RoleEntity data, @PathVariable Long id) {
        return roleService.update(data);
    }
}
