package com.eungchaeungcha.juang.repository;

import com.eungchaeungcha.juang.entity.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<FamilyEntity, Long> {
    Optional<FamilyEntity> findOneByCode(String code);
    Boolean existsByCode(String code);
}
