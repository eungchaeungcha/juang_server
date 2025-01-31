package com.eungchaeungcha.juang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record AuthenticationResponseDTO(
        String token
) {
}
