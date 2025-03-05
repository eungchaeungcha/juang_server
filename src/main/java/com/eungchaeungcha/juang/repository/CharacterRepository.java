package com.eungchaeungcha.juang.repository;

import com.eungchaeungcha.juang.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    Optional<CharacterEntity> findOneById(Long id);
    Optional<CharacterEntity> findOneByNameAndColor(String name, String color);
}
