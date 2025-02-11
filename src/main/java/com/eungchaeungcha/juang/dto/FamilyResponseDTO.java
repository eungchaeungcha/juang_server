package com.eungchaeungcha.juang.dto;

import com.eungchaeungcha.juang.entity.FamilyEntity;
import lombok.Builder;

@Builder
public record FamilyResponseDTO(
        Long id, String code, String name
) {
    public static FamilyResponseDTO from(FamilyEntity family) {
        return FamilyResponseDTO.builder()
                .id(family.getId())
                .code(family.getCode())
                .name(family.getName())
                .build();
    }
}
