package com.eungchaeungcha.juang.repository;

import com.eungchaeungcha.juang.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    CharacterEntity findOneById(Long id);
    CharacterEntity findOneByNameAndColor(String name, String color);
}
