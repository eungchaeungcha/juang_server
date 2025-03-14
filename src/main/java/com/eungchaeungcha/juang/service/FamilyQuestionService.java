package com.eungchaeungcha.juang.service;

import com.eungchaeungcha.juang.common.BusinessException;
import com.eungchaeungcha.juang.common.CommonErrorCode;
import com.eungchaeungcha.juang.dto.FamilyQuestionResponseDTO;
import com.eungchaeungcha.juang.entity.FamilyQuestionEntity;
import com.eungchaeungcha.juang.repository.FamilyQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FamilyQuestionService {
    private final FamilyQuestionRepository familyQuestionRepository;
    private final FamilyService familyService;


    public List<FamilyQuestionResponseDTO> find(Long familyId) {
        familyService.exist(familyId);

        List<FamilyQuestionEntity> list = familyQuestionRepository.findAllByFamilyIdOrderByOpenedAtDesc(familyId);

        if (list.isEmpty()) {
            throw new BusinessException(CommonErrorCode.FAMILY_QUESTION_NOT_FOUND);
        }

        return list.stream().map(familyQuestionEntity -> FamilyQuestionResponseDTO.from(familyQuestionEntity.toDomain()))
                .collect(Collectors.toList());
    }

    public FamilyQuestionResponseDTO find(Long familyId, Long questionId) {
        familyService.exist(familyId);

        FamilyQuestionEntity entity = familyQuestionRepository.findOneByFamilyIdAndQuestionId(familyId, questionId)
                .orElseThrow(() -> new BusinessException(CommonErrorCode.FAMILY_QUESTION_NOT_FOUND));

        return FamilyQuestionResponseDTO.from(entity.toDomain());
    }

    public FamilyQuestionResponseDTO findToday(Long familyId) {
        familyService.exist(familyId);

        FamilyQuestionEntity entity = familyQuestionRepository.findFirstByFamilyIdOrderByOpenedAtDesc(familyId)
                .orElseThrow(() -> new BusinessException(CommonErrorCode.FAMILY_QUESTION_NOT_FOUND));

        return FamilyQuestionResponseDTO.from(entity.toDomain());
    }
}
