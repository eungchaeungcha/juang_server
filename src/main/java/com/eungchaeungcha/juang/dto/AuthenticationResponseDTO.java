package com.eungchaeungcha.juang.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponseDTO(
        String token
) {
}
