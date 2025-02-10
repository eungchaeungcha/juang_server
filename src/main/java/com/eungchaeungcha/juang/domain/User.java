package com.eungchaeungcha.juang.domain;

import lombok.Builder;

@Builder
public record User(Long id, String username, String password, String nickName, Long familyId, Long characterId,
                   Role role) {
    public User changeCharacter(Long newCharacterId) {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .nickName(this.nickName)
                .familyId(this.familyId)
                .characterId(newCharacterId)
                .role(this.role)
                .build();
    }
}
