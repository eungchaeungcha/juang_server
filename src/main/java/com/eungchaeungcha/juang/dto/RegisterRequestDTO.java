package com.eungchaeungcha.juang.dto;

import lombok.Builder;


@Builder
public record RegisterRequestDTO(
        String username,
        String password
) {
}
