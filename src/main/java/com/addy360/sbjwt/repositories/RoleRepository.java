package com.addy360.sbjwt.repositories;

import com.addy360.sbjwt.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityByRoleName(String role);
}
