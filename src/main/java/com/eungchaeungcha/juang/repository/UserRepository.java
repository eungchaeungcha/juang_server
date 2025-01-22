package com.eungchaeungcha.juang.repository;

import com.eungchaeungcha.juang.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
