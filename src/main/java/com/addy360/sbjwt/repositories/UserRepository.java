package com.addy360.sbjwt.repositories;

import com.addy360.sbjwt.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findUserEntityByEmailOrUsername(String username, String email);
}
