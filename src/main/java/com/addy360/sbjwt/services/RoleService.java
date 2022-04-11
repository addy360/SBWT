package com.addy360.sbjwt.services;

import com.addy360.sbjwt.models.RoleEntity;
import com.addy360.sbjwt.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class RoleService implements CrudService<RoleEntity>{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> index() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity show(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public RoleEntity store(RoleEntity data) {
        log.info("Saving role : {}", data.toString());
        return roleRepository.save(data);
    }

    @Override
    public RoleEntity update(RoleEntity data) {
        log.info("updating role : {}", data.toString());
        return  roleRepository.save(data);
    }
}
