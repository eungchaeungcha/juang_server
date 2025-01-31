package com.eungchaeungcha.juang.repository;

import com.eungchaeungcha.juang.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    UserEntity findOneById(Long id);
}
