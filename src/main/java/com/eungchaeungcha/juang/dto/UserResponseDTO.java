package com.eungchaeungcha.juang.dto;


import com.eungchaeungcha.juang.domain.User;
import lombok.Builder;

@Builder
public record UserResponseDTO(Long id, String username, String nickName, Long characterId, Long familyId) {
    public static UserResponseDTO from(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickName(user.getNickName())
                .characterId(user.getCharacterId())
                .familyId(user.getFamilyId())
                .build();
    }
}