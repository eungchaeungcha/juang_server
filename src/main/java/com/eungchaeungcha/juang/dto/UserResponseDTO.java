package com.eungchaeungcha.juang.dto;


import com.eungchaeungcha.juang.domain.User;

public record UserResponseDTO(Long id, String username, String nickName, Long characterId) {
    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(
                user.id(),
                user.username(),
                user.nickName(),
                user.characterId()
        );
    }
}