package com.eungchaeungcha.juang.dto;

import com.eungchaeungcha.juang.domain.FamilyQuestion;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FamilyQuestionResponseDTO(
        Long familyQuestionId,
        Long familyId,
        Long questionId,
        String questionContent,
        LocalDate openedAt
) {
    public static FamilyQuestionResponseDTO from(FamilyQuestion familyQuestion){
        return FamilyQuestionResponseDTO.builder()
                .familyQuestionId(familyQuestion.getId())
                .familyId(familyQuestion.getFamilyId())
                .questionId(familyQuestion.getQuestionId())
                .questionContent(familyQuestion.getQuestionContent())
                .openedAt(familyQuestion.getOpenedAt())
                .build();
    }
}
