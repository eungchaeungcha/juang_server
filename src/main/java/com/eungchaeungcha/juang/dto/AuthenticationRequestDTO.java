package com.eungchaeungcha.juang.dto;

import lombok.Builder;

@Builder
public record AuthenticationRequestDTO(
        String username,
        String password
) {
}
