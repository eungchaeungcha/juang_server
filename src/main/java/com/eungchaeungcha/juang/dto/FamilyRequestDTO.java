package com.eungchaeungcha.juang.dto;

import jakarta.validation.constraints.Size;

public record FamilyRequestDTO(
        @Size(
                max = 11,
                message = "감나무 이름은 11자 이하로 작성해야 합니다."
        )
        String name
) {
}
