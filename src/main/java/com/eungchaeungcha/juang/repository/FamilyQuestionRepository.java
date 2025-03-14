package com.eungchaeungcha.juang.repository;

import com.eungchaeungcha.juang.entity.FamilyQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FamilyQuestionRepository extends JpaRepository<FamilyQuestionEntity, Long> {
    List<FamilyQuestionEntity> findAllByFamilyIdOrderByOpenedAtDesc(Long familyId);
    Optional<FamilyQuestionEntity> findOneByFamilyIdAndQuestionId(Long familyId, Long questionId);
    Optional<FamilyQuestionEntity> findFirstByFamilyIdOrderByOpenedAtDesc(Long familyId);
}
