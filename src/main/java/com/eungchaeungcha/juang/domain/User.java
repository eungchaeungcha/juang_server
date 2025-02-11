package com.eungchaeungcha.juang.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickName;
    private Long familyId;
    private Long characterId;
    private Role role;

    public void changeCharacter(Long characterId) {
        this.characterId = characterId;
    }

    public void changeFamily(Long familyId) {
        this.familyId =familyId;
    }

    public void changeNickName(String nickName) {
        this.nickName = nickName;
    }
}
